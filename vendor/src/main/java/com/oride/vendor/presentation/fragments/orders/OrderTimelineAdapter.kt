package com.oride.vendor.presentation.fragments.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.vendor.R
import com.oride.vendor.data.OrderTimeline
import com.oride.vendor.databinding.LayoutOrderTimelineItemBinding
import com.oride.vendor.presentation.base.BaseAdapter
import javax.inject.Inject

class OrderTimelineAdapter @Inject constructor(
) : BaseAdapter<OrderTimeline, LayoutOrderTimelineItemBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutOrderTimelineItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<OrderTimeline>() {
        override fun areItemsTheSame(oldItem: OrderTimeline, newItem: OrderTimeline): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: OrderTimeline, newItem: OrderTimeline): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutOrderTimelineItemBinding>, position: Int) {
        val timeline = differ.currentList[position]
        holder.binding.apply {
            tvTime.text = timeline.time
            tvStatusMsg.text = timeline.message
            if (position == differ.currentList.size - 2) {
                ivPoint.setImageResource(R.drawable.ic_ellipse_green)
            }
            if (position == differ.currentList.size - 1) {
                ivPoint.setImageResource(R.drawable.ic_check_circle_s)
                line.visibility = View.GONE
            }
        }
    }
}