package com.oride.delivery.presentation.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.base.BaseAdapter
import com.oride.delivery.R
import com.oride.delivery.data.DeliveryDestination
import com.oride.delivery.databinding.LayoutDeliveryDestinationItemBinding
import javax.inject.Inject

class DeliveryDestinationAdapter @Inject constructor(
) : BaseAdapter<DeliveryDestination, LayoutDeliveryDestinationItemBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutDeliveryDestinationItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<DeliveryDestination>() {
        override fun areItemsTheSame(oldItem: DeliveryDestination, newItem: DeliveryDestination): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: DeliveryDestination, newItem: DeliveryDestination): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutDeliveryDestinationItemBinding>, position: Int) {
        val destination = differ.currentList[position]
        holder.binding.apply {
            tvPlaceName.text = destination.addressTitle
            tvPlaceDetail.text = destination.address
            if (position == differ.currentList.size - 1) {
                ivPoint.setImageResource(R.drawable.ic_ellipse_blue_light)
                line.visibility = View.GONE
            }
        }
    }
}