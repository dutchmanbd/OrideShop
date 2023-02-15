package com.oride.delivery.presentation.fragments.settings

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.utilities.Constants
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentSettingsBinding
import com.oride.delivery.presentation.ShowLogOutDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>(R.layout.fragment_settings) {

    override val viewModel by viewModels<SettingsViewModel>()

    override fun initializeViewBinding(view: View) = FragmentSettingsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        val onClickListener = View.OnClickListener {
            when (it) {
                binding.llProfile -> navigateToProfileFragment()
                binding.llNotification -> navigateToNotificationFragment()
                binding.llFaq -> navigateToWebViewFragment(Constants.HelpAndLegal.FAQ)
                binding.llContactUs -> navigateToContactUsFragment()
                binding.llSupport -> navigateToSupportFragment()
                binding.llLogOut -> {
                    ShowLogOutDialog(requireContext(), message = null, yes = {}, close = {}).show()
                }
            }
        }

        binding.llProfile.setOnClickListener(onClickListener)
        binding.llNotification.setOnClickListener(onClickListener)
        binding.llFaq.setOnClickListener(onClickListener)
        binding.llContactUs.setOnClickListener(onClickListener)
        binding.llSupport.setOnClickListener(onClickListener)
        binding.llLogOut.setOnClickListener(onClickListener)
    }

    private fun navigateToProfileFragment() {
        findNavController().navigate(R.id.profileFragment)
    }

    private fun navigateToNotificationFragment() {
        findNavController().navigate(R.id.notificationFragment)
    }

    private fun navigateToContactUsFragment() {
        findNavController().navigate(R.id.contactFragment)
    }

    private fun navigateToSupportFragment() {
        findNavController().navigate(R.id.supportFragment)
    }

    private fun navigateToWebViewFragment(contentOf: Constants.HelpAndLegal) {
        findNavController().navigate(R.id.webViewFragment,
            bundleOf(Constants.helpAndLegal to contentOf.name)
        )
    }
}