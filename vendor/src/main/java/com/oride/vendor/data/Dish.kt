package com.oride.vendor.data

import androidx.annotation.DrawableRes

data class Dish (
    val id: Int,
    val name: String,
    @DrawableRes val image: Int?,
    val ingredients: String,
    val desc: String,
    val sizes: List<Size>,
    val isAvailable: Boolean,
    val price: Double
)