package com.oride.vendor.presentation.fragments.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentContactBinding
import com.oride.vendor.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<SettingsViewModel, FragmentContactBinding>(R.layout.fragment_contact) {

    override val viewModel by viewModels<SettingsViewModel>()

    override fun initializeViewBinding(view: View) = FragmentContactBinding.bind(view)

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