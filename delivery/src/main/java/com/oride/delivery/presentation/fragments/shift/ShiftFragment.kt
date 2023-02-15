package com.oride.delivery.presentation.fragments.shift

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentShiftBinding
import com.oride.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShiftFragment : BaseFragment<ShiftViewModel, FragmentShiftBinding>(R.layout.fragment_shift) {

    @Inject
    lateinit var shiftTodayTimeAdapter: ShiftTimeAdapter

    @Inject
    lateinit var shiftTomorrowTimeAdapter: ShiftTimeAdapter

    override val viewModel by viewModels<ShiftViewModel>()

    override fun initializeViewBinding(view: View) = FragmentShiftBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRecyclerViews()
        fetchShiftTime()
    }

    private fun setupListeners() {}

    private fun setupRecyclerViews() {
        binding.rvTodayTime.adapter = shiftTodayTimeAdapter
        binding.rvTomorrowTime.adapter = shiftTomorrowTimeAdapter
    }

    private fun fetchShiftTime() {
        viewModel.fetchShiftTodayTime().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Failure -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    shiftTodayTimeAdapter.differ.submitList(resource.data)
                }
            }

        }
        viewModel.fetchShiftTomorrowTime().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Failure -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    shiftTomorrowTimeAdapter.differ.submitList(resource.data)
                }
            }

        }
    }
}