package com.oride.delivery.presentation.fragments.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.base.BaseAdapter
import com.oride.delivery.data.Food
import com.oride.delivery.databinding.LayoutOrderFoodDetailItemBinding
import javax.inject.Inject

class OrderFoodDetailItemAdapter @Inject constructor(
) : BaseAdapter<Food, LayoutOrderFoodDetailItemBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutOrderFoodDetailItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutOrderFoodDetailItemBinding>, position: Int) {
        val food = differ.currentList[position]
        holder.binding.apply {
            tvName.text = food.name
            tvQuantity.text = "x${food.quantity}"
            tvPrice.text = "$${food.price}"
            tvTotalPrice.text = "$${food.quantity * food.price}"
        }
    }
}