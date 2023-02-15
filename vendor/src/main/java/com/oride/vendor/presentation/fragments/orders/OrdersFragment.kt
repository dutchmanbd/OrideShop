package com.oride.vendor.presentation.fragments.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oride.utilities.Resource
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentOrdersBinding
import com.oride.vendor.presentation.base.BaseFragment
import com.oride.vendor.presentation.fragments.home.OrderAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrdersFragment : BaseFragment<OrdersViewModel, FragmentOrdersBinding>(R.layout.fragment_orders) {

    @Inject
    lateinit var orderAdapter: OrderAdapter

    override val viewModel by viewModels<OrdersViewModel>()

    override fun initializeViewBinding(view: View) = FragmentOrdersBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        subscribeObservers()
    }

    private fun setRecyclerView() {
        binding.rvCurrOrders.adapter = orderAdapter
        orderAdapter.setOnItemClickListener { view, item ->
            findNavController().navigate(R.id.orderDetailsFragment)
        }
    }

    private fun subscribeObservers() {
        viewModel.fetchOrders().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    orderAdapter.differ.submitList(resource.data)
                }
            }
        }
    }
}