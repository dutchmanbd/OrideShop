package com.oride.vendor.presentation.fragments.dish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oride.utilities.Resource
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentDishesBinding
import com.oride.vendor.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DishesFragment : BaseFragment<DishViewModel, FragmentDishesBinding>(R.layout.fragment_dishes) {

    @Inject
    lateinit var dishAdapter: DishAdapter

    override val viewModel by viewModels<DishViewModel>()

    override fun initializeViewBinding(view: View) = FragmentDishesBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setupListeners()
        subscribeObservers()
    }

    private fun setupListeners() {
        binding.tvAddNew.setOnClickListener {
            findNavController().navigate(R.id.addDishesFragment)
        }
    }

    private fun setRecyclerView() {
        binding.rvCurrOrders.adapter = dishAdapter
        dishAdapter.setOnItemClickListener { view, item ->
            findNavController().navigate(R.id.orderDetailsFragment)
        }
    }

    private fun subscribeObservers() {
        viewModel.fetchDishes().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    dishAdapter.differ.submitList(resource.data)
                }
            }
        }
    }
}