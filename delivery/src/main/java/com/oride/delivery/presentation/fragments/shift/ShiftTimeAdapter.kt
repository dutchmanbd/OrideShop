package com.oride.delivery.presentation.fragments.shift

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.oride.base.BaseAdapter
import com.oride.delivery.R
import com.oride.delivery.data.ShiftTime
import com.oride.delivery.databinding.LayoutShiftTimeBinding
import javax.inject.Inject

class ShiftTimeAdapter @Inject constructor(
) : BaseAdapter<ShiftTime, LayoutShiftTimeBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutShiftTimeBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<ShiftTime>() {
        override fun areItemsTheSame(oldItem: ShiftTime, newItem: ShiftTime): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ShiftTime, newItem: ShiftTime): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutShiftTimeBinding>, position: Int) {
        val time = differ.currentList[position]
        holder.binding.apply {
            tvTime.text = time.time
            when {
                time.isBooked -> {
                    tvTime.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.checked_online_bg)
                    tvTime.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
                }
                !time.isAvailable -> {
                    tvTime.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_un_av_time)
                    tvTime.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
                }
                else -> {
                    tvTime.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_av_time)
                    tvTime.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.textColorLight))
                }
            }
        }

        holder.itemView.setOnClickListener {
            listener?.invoke(it, time)
        }
    }
}