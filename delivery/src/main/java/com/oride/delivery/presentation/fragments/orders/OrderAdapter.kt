package com.oride.delivery.presentation.fragments.orders

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.base.BaseAdapter
import com.oride.delivery.data.Order
import com.oride.delivery.databinding.LayoutOrderItemBinding
import javax.inject.Inject

class OrderAdapter @Inject constructor(
) : BaseAdapter<Order, LayoutOrderItemBinding>() {

    @Inject
    lateinit var foodItemAdapter: OrderFoodItemAdapter

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutOrderItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutOrderItemBinding>, position: Int) {
        val order = differ.currentList[position]
        holder.binding.apply {
            tvProfileName.text = order.userName
            order.userPhoto?.let { ivProfilePicture.setImageResource(it) }
            tvOrderPrice.text = "$%.2f".format(order.totalPrice)

            // Set recycler view adapter
            rvOrderItem.adapter = foodItemAdapter
            foodItemAdapter.differ.submitList(order.orderItems)

            rvOrderItem.suppressLayout(true)

            when {
                order.isCompleted -> {
                    btnPickedUp.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#65DC41"))
                    btnPickedUp.isEnabled = false
                }
                order.isPickedUp -> {
                    btnPickedUp.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#8DA4C9"))
                    btnPickedUp.isEnabled = false
                }
                else -> {
                    btnPickedUp.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D0115F"))
                    btnPickedUp.isEnabled = true
                }
            }
        }

        holder.itemView.setOnClickListener {
            listener?.invoke(it, order)
        }
    }
}