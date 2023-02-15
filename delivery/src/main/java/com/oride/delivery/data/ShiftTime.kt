package com.oride.delivery.data

data class ShiftTime(
    val id: Int,
    val time: String,
    val isAvailable: Boolean,
    val isBooked: Boolean,
)