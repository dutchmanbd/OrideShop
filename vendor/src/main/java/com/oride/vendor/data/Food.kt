package com.oride.vendor.data

import androidx.annotation.DrawableRes

data class Food(
    val id: Int,
    val quantity: Int,
    val name: String,
    val location: String,
    val brand: String,
    val rating: Double,
    val totalRating: Int,
    val price: Double,
    @DrawableRes val image: Int
)
