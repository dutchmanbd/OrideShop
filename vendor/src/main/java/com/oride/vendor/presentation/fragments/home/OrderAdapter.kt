package com.oride.vendor.presentation.fragments.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.vendor.R
import com.oride.vendor.data.Order
import com.oride.vendor.databinding.LayoutOrderItemBinding
import com.oride.vendor.presentation.base.BaseAdapter
import com.oride.vendor.presentation.fragments.orders.OrderFoodDetailItemAdapter
import javax.inject.Inject

class OrderAdapter @Inject constructor(
) : BaseAdapter<Order, LayoutOrderItemBinding>() {

    @Inject
    lateinit var foodItemAdapter: OrderFoodItemAdapter

    @Inject
    lateinit var foodDetailItemAdapter: OrderFoodDetailItemAdapter

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
            tvOrderId.text = order.orderId
            tvOrderTime.text = order.orderTime
            tvProfileName.text = order.userName
            order.userPhoto?.let { ivProfilePicture.setImageResource(it) }
            tvOrderPrice.text = "$%.2f".format(order.totalPrice)

            // Set recycler view adapter
            rvOrderItem.adapter = foodItemAdapter
            rvOrderDetail.adapter = foodDetailItemAdapter
            foodItemAdapter.differ.submitList(order.orderItems)
            foodDetailItemAdapter.differ.submitList(order.orderItems)

            rvOrderItem.suppressLayout(true)
            rvOrderDetail.suppressLayout(true)

            tvDetails.setOnClickListener {
                if (detailGroup.visibility == View.GONE) {
                    tvDetails.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_baseline_arrow_up, 0);
                    detailGroup.visibility = View.VISIBLE
                } else {
                    tvDetails.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_baseline_arrow_down, 0);
                    detailGroup.visibility = View.GONE
                }
            }

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