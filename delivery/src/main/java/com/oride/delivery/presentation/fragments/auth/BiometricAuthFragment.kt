package com.oride.delivery.presentation.fragments.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentBiometricAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BiometricAuthFragment : BaseFragment<AuthViewModel, FragmentBiometricAuthBinding>(R.layout.fragment_biometric_auth) {

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun initializeViewBinding(view: View) = FragmentBiometricAuthBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.facialRecBtn.setOnClickListener {
            findNavController().navigate(R.id.action_biometricAuthFragment_to_facialCameraFragment)
        }
    }
}