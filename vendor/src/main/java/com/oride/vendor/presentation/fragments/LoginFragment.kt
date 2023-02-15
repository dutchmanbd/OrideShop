package com.oride.vendor.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentLoginBinding
import com.oride.vendor.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding>(R.layout.fragment_login) {

    override val viewModel by viewModels<AuthViewModel>()

    override fun initializeViewBinding(view: View) = FragmentLoginBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_nav_home)
        }
    }
}