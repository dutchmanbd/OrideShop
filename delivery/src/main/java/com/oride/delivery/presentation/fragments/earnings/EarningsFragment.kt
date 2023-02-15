package com.oride.delivery.presentation.fragments.earnings

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentEarningsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarningsFragment : BaseFragment<EarningsViewModel, FragmentEarningsBinding>(R.layout.fragment_earnings) {

    override val viewModel by viewModels<EarningsViewModel>()

    override fun initializeViewBinding(view: View) = FragmentEarningsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Time adapter
        val timeAdapter = ArrayAdapter(requireActivity(), R.layout.layout_simple_dropdown_item, listOf("Daily", "Weekly"))
        binding.timeSpinner.adapter = timeAdapter

    }
}