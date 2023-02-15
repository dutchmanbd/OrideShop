package com.oride.delivery.data

import androidx.annotation.DrawableRes

data class Order(
    val id: Int,
    val orderId: String,
    val orderTime: String,
    val userName: String,
    @DrawableRes val userPhoto: Int?,
    val orderItems: List<Food>,
    val isPickedUp: Boolean,
    val isCompleted: Boolean,
    val vat: Double,
    val totalPrice: Double
)
