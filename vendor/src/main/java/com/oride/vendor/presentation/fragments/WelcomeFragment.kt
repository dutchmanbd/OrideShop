package com.oride.vendor.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentWelcomeBinding
import com.oride.vendor.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<AuthViewModel, FragmentWelcomeBinding>(R.layout.fragment_welcome) {

    override val viewModel by viewModels<AuthViewModel>()

    override fun initializeViewBinding(view: View) = FragmentWelcomeBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }
}