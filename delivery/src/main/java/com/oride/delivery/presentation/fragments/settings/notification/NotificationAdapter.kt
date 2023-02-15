package com.oride.delivery.presentation.fragments.settings.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.base.BaseAdapter
import com.oride.delivery.data.Notification
import com.oride.delivery.databinding.LayoutNotificationItemBinding
import javax.inject.Inject

class NotificationAdapter @Inject constructor(
) : BaseAdapter<Notification, LayoutNotificationItemBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutNotificationItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutNotificationItemBinding>, position: Int) {
        val notification = differ.currentList[position]
        holder.binding.apply {
            tvTitle.text =notification.title
            tvDesc.text =notification.description
            tvReadStatus.text =notification.action
        }
    }
}