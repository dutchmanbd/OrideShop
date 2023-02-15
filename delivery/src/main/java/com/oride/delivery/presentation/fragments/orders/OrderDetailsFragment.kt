package com.oride.delivery.presentation.fragments.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentOrderDetailsBinding
import com.oride.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrderDetailsFragment : BaseFragment<OrderDetailsViewModel, FragmentOrderDetailsBinding>(R.layout.fragment_order_details) {

    @Inject
    lateinit var timelineAdapter: OrderTimelineAdapter

    @Inject
    lateinit var orderFoodItemAdapter: OrderFoodDetailItemAdapter

    override val viewModel by viewModels<OrderDetailsViewModel>()

    override fun initializeViewBinding(view: View) = FragmentOrderDetailsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        subscribeObservers()

        binding.btnReportIssue.setOnClickListener {
            navigateToReportFragment()
        }
    }

    private fun setRecyclerView() {
        binding.rvTimeline.adapter = timelineAdapter
        binding.rvOrderItem.adapter = orderFoodItemAdapter
    }

    private fun subscribeObservers() {
        viewModel.fetchOrderTimeline().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    timelineAdapter.differ.submitList(resource.data)
                }
            }
        }

        viewModel.fetchOrderFoods().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    orderFoodItemAdapter.differ.submitList(resource.data)
                }
            }
        }
    }

    private fun navigateToReportFragment() {
        findNavController().navigate(R.id.reportFragment)
    }
}