package com.oride.vendor.presentation.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.vendor.data.Food
import com.oride.vendor.databinding.LayoutOrderFoodItemBinding
import com.oride.vendor.presentation.base.BaseAdapter
import javax.inject.Inject

class OrderFoodItemAdapter @Inject constructor(
) : BaseAdapter<Food, LayoutOrderFoodItemBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutOrderFoodItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutOrderFoodItemBinding>, position: Int) {
        val food = differ.currentList[position]
        holder.binding.apply {
            tvName.text = food.name
            tvQuantity.text = "x${food.quantity}"

        }
    }
}