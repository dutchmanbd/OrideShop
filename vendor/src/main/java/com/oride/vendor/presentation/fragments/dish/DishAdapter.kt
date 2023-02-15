package com.oride.vendor.presentation.fragments.dish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.vendor.R
import com.oride.vendor.data.Dish
import com.oride.vendor.databinding.LayoutDishItemBinding
import com.oride.vendor.presentation.base.BaseAdapter
import javax.inject.Inject

class DishAdapter @Inject constructor(
) : BaseAdapter<Dish, LayoutDishItemBinding>() {

    @Inject
    lateinit var sizeAdapter: SizeAdapter

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int) = LayoutDishItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutDishItemBinding>, position: Int) {
        val dish = differ.currentList[position]
        holder.binding.apply {
            tvName.text = dish.name
            dish.image?.let { ivImage.setImageResource(it) }
            tvIngredients.text = dish.ingredients
            tvDesc.text = dish.desc
            tvIngredients.text = dish.ingredients
            tvPrice.text = "$%.2f".format(dish.price)

            // Set recycler view adapter
            rvSize.adapter = sizeAdapter
            sizeAdapter.differ.submitList(dish.sizes)

            when {
                dish.isAvailable -> {
                    tvAvailable.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_s, 0, 0, 0)
                    tvUnavailable.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_cancel_grey, 0, 0, 0)
                }
                else -> {
                    tvAvailable.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_s_grey, 0, 0, 0)
                    tvUnavailable.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_cancel, 0, 0, 0)
                }
            }
        }
    }
}