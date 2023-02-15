package com.oride.delivery.presentation.fragments.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentFacialCameraBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FacialCameraFragment : BaseFragment<AuthViewModel, FragmentFacialCameraBinding>(R.layout.fragment_facial_camera) {

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun initializeViewBinding(view: View) = FragmentFacialCameraBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.letsRide.setOnClickListener {
            findNavController().navigate(R.id.action_facialCameraFragment_to_facialRecognitionFragment)
        }
    }
}