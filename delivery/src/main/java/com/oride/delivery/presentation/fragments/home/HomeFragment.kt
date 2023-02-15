package com.oride.delivery.presentation.fragments.home

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.tasks.Task
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentHomeBinding
import com.oride.delivery.presentation.fragments.orders.OrderFoodDetailItemAdapter
import com.oride.extensions.context.checkPermissions
import com.oride.extensions.context.toast
import com.oride.extensions.location.awaitCurrentLocation
import com.oride.extensions.result.checkResultAndExecute
import com.oride.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    @Inject
    lateinit var foodDetailItemAdapter: OrderFoodDetailItemAdapter

    @Inject
    lateinit var deliveryDestinationAdapter: DeliveryDestinationAdapter

    private var mMap: GoogleMap? = null

    private val mLocationManager: LocationManager
        get() = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val fusedLocationProviderClient: FusedLocationProviderClient
        get() = LocationServices.getFusedLocationProviderClient(requireContext())


    private val locationRequest: LocationRequest by lazy {
        LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private var resolutionForResult: ActivityResultLauncher<IntentSenderRequest>? = null

    private fun hasGpsEnabled() = LocationManagerCompat.isLocationEnabled(mLocationManager)

    override val viewModel by viewModels<HomeViewModel>()

    override fun initializeViewBinding(view: View) = FragmentHomeBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync {
            mMap = it
            mapStyle()
            checkPermissions()
//            applyStyleInMaps()
//            subscribeObservers()
        }
        showNewOrderRequest()
    }

    private fun subscribeObservers() {
        viewModel.fetchOrderFoods().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    foodDetailItemAdapter.differ.submitList(resource.data)
                }
            }
        }

        viewModel.getDeliveryDestinations().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    deliveryDestinationAdapter.differ.submitList(resource.data)
                }
            }
        }
    }

    private fun showNewOrderRequest() {
        binding.bottomSheetOrderRequest.root.visibility = View.VISIBLE
        binding.bottomSheetOrderRequest.rvOrderItem.adapter = foodDetailItemAdapter
        binding.bottomSheetOrderRequest.declineBtn.setOnClickListener {
            binding.bottomSheetOrderRequest.root.visibility = View.GONE
        }

        binding.bottomSheetOrderRequest.acceptBtn.setOnClickListener {
            binding.bottomSheetOrderRequest.root.visibility = View.GONE
            showOngoingDeliverySheet()
        }
    }

    private  fun showOngoingDeliverySheet() {
        binding.bottomSheetOngoingDelivery.root.visibility = View.VISIBLE
        binding.bottomSheetOngoingDelivery.rvDestination.adapter = deliveryDestinationAdapter
        binding.bottomSheetOngoingDelivery.callNowBtn.setOnClickListener {
            showCallOptionSheet()
        }
        binding.bottomSheetOngoingDelivery.messageBtn.setOnClickListener {
            findNavController().navigate(R.id.chatFragment)
        }
    }

    private  fun showCallOptionSheet() {
        binding.bottomSheetDeliveryCallOption.root.visibility = View.VISIBLE
        binding.bottomSheetDeliveryCallOption.phoneCall.setOnClickListener {  }
        binding.bottomSheetDeliveryCallOption.dataCallBtn.setOnClickListener {  }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }


    private fun registerResolutionResult() {
        resolutionForResult =
            registerForActivityResult(
                ActivityResultContracts.StartIntentSenderForResult()
            ) { activityResult ->
                activityResult.checkResultAndExecute {
                    fetchUserCurrentLocation()
                }.onFailure {
                    requireContext().toast(
                        it.message ?: "Something went wrong"
                    )
                }
            }
    }

    private fun checkPermissions() {
        requireActivity().checkPermissions(
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            askToUserEnableGps()
        }
    }

    private fun askToUserEnableGps() {
        if (hasGpsEnabled()) {
            fetchUserCurrentLocation()
        } else {
            showGpsAlertDialog()
        }
    }

    private fun fetchUserCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        lifecycleScope.launchWhenResumed {
            val newLocation =
                fusedLocationProviderClient.awaitCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY)

            val currentLatLng = LatLng(
                newLocation?.latitude ?: 0.0,
                newLocation?.longitude ?: 0.0
            )
            mMap?.addCircle(CircleOptions().apply {
                center(currentLatLng)
                radius(5.0)
                fillColor(Color.BLACK)
            })

            mMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    currentLatLng,
                    15f
                )
            )
        }

    }

    private fun showGpsAlertDialog() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    resolutionForResult?.launch(
                        IntentSenderRequest.Builder(exception.resolution).build()
                    )
                } catch (e: IntentSender.SendIntentException) {
//                    Log.d(TAG, "showGpsAlertDialog: ${e.message}")
                }
            }
        }

    }

    private fun mapStyle() {
        // Get configuration
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                Log.e("Night Mode", "Enabled")
                try {
                    // Customise the styling of the base map using a JSON object defined
                    // in a raw resource file.
                    val success = mMap?.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.oride_map_night))
                    if (!success!!) {
                        Log.e("TAG", "Style parsing failed.")
                    }
                } catch (e: Resources.NotFoundException) {
                    Log.e("TAG", "Can't find style. Error: ", e)
                }
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                try {
                    // Customise the styling of the base map using a JSON object defined
                    // in a raw resource file.
                    val success = mMap?.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.oride_map_normal))
                    if (!success!!) {
                        Log.e("TAG", "Style parsing failed.")
                    }
                } catch (e: Resources.NotFoundException) {
                    Log.e("TAG", "Can't find style. Error: ", e)
                }
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
            }
        }
    }
}