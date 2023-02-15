package com.oride.vendor.presentation.fragments.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.utilities.Constants
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentWebViewBinding
import com.oride.vendor.presentation.base.BaseFragment

class WebViewFragment : BaseFragment<SettingsViewModel, FragmentWebViewBinding>(R.layout.fragment_web_view) {

    private var contentOf: String? = null

    override val viewModel by viewModels<SettingsViewModel>()

    override fun initializeViewBinding(view: View) = FragmentWebViewBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contentOf = arguments?.getString(Constants.helpAndLegal)
        // Get page title
        contentOf?.let {
            val titleText = when (it) {
                Constants.HelpAndLegal.FAQ.name -> {
                    getString(R.string.faqs)
                }
                else -> ""
            }
            binding.tvTitle.apply {
                text = titleText
            }
        }
    }

    private fun setupListeners() {

    }
}