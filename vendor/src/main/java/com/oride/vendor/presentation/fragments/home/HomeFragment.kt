package com.oride.vendor.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oride.utilities.Resource
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentHomeBinding
import com.oride.vendor.presentation.base.BaseFragment
import com.oride.vendor.presentation.fragments.orders.OrderFoodDetailItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    @Inject
    lateinit var orderAdapter: OrderAdapter

    @Inject
    lateinit var foodDetailItemAdapter: OrderFoodDetailItemAdapter

    override val viewModel by viewModels<HomeViewModel>()

    override fun initializeViewBinding(view: View) = FragmentHomeBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        subscribeObservers()

        showNewOrderRequest()
    }

    private fun setRecyclerView() {
        binding.rvCurrOrders.adapter = orderAdapter
        orderAdapter.setOnItemClickListener { view, item ->
            findNavController().navigate(R.id.orderDetailsFragment)
        }
    }

    private fun subscribeObservers() {
        viewModel.fetchCurrentOrders().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    orderAdapter.differ.submitList(resource.data)
                }
            }
        }

        viewModel.fetchOrderFoods().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    foodDetailItemAdapter.differ.submitList(resource.data)
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
        }
    }
}