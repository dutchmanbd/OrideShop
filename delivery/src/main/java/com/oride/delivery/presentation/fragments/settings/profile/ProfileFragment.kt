package com.oride.delivery.presentation.fragments.settings.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentProfileBinding
import com.oride.utilities.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(R.layout.fragment_profile) {

    override val viewModel by viewModels<ProfileViewModel>()

    override fun initializeViewBinding(view: View) = FragmentProfileBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        val onClickListener = View.OnClickListener {
            when (it) {
                binding.tvProfileName -> {
                    navigateToUpdateNameFragment()
                }
                binding.llPhoneNumber -> {
                    navigateToPhoneVerificationFragment(Constants.NavToOTPScreen.FROM_UPDATE_NUMBER)
                }
                binding.tvEmail -> {
                    navigateToUpdateEmailFragment()
                }
            }
        }

        binding.tvProfileName.setOnClickListener(onClickListener)
        binding.llPhoneNumber.setOnClickListener(onClickListener)
        binding.tvEmail.setOnClickListener(onClickListener)
    }

    private fun navigateToUpdateNameFragment() {
//        findNavController().navigate(R.id.updateNameFragment)
    }

    private fun navigateToUpdateEmailFragment() {
//        findNavController().navigate(R.id.updateEmailFragment)
    }

    private fun navigateToPhoneVerificationFragment(navigateTo: Constants.NavToOTPScreen) {
//        findNavController().navigate(R.id.phoneVerificationFragment,
//            bundleOf(Constants.navToOTPScreen to navigateTo.name)
//        )
    }
}