package com.oride.vendor.presentation.fragments.dish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.vendor.data.Size
import com.oride.vendor.databinding.LayoutSizeItemBinding
import com.oride.vendor.presentation.base.BaseAdapter
import javax.inject.Inject

class SizeAdapter @Inject constructor(
) : BaseAdapter<Size, LayoutSizeItemBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutSizeItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Size>() {
        override fun areItemsTheSame(oldItem: Size, newItem: Size): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Size, newItem: Size): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutSizeItemBinding>, position: Int) {
        val mSize = differ.currentList[position]
        holder.binding.apply {
            tvSize.text = mSize.size
        }
    }
}