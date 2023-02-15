package com.oride.delivery.presentation.fragments.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentSupportBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupportFragment : BaseFragment<SettingsViewModel, FragmentSupportBinding>(R.layout.fragment_support) {

    override val viewModel by viewModels<SettingsViewModel>()

    override fun initializeViewBinding(view: View) = FragmentSupportBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        val onClickListener = View.OnClickListener {
            when (it) {
                binding.cvCallCS -> {}
                binding.cvEmailUs -> {}
            }
        }

        binding.cvCallCS.setOnClickListener(onClickListener)
        binding.cvEmailUs.setOnClickListener(onClickListener)
    }
}