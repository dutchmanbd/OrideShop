package com.oride.delivery.presentation.fragments.settings.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.utilities.Resource
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationFragment : BaseFragment<NotificationViewModel, FragmentNotificationBinding>(
    R.layout.fragment_notification
) {

    @Inject lateinit var notificationAdapter: NotificationAdapter

    override val viewModel by viewModels<NotificationViewModel>()

    override fun initializeViewBinding(view: View) = FragmentNotificationBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setRecyclerView()
        subscribeObservers()
    }

    private fun setupListeners() {

    }

    private fun setRecyclerView() {
        binding.rvNotification.adapter = notificationAdapter
    }

    private fun subscribeObservers() {
        viewModel.fetchNotification().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {}
                is Resource.Success -> {
                    notificationAdapter.differ.submitList(resource.data)
                }
            }
        }
    }
}