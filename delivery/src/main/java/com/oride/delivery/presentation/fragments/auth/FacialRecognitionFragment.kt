package com.oride.delivery.presentation.fragments.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentFacialRecognitionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FacialRecognitionFragment : BaseFragment<AuthViewModel, FragmentFacialRecognitionBinding>(R.layout.fragment_facial_recognition) {

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun initializeViewBinding(view: View) = FragmentFacialRecognitionBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.letsRide.setOnClickListener {
            findNavController().navigate(R.id.action_facialRecognitionFragment_to_nav_home)
        }
    }
}