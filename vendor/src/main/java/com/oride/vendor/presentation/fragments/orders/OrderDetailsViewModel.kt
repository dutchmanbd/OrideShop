package com.oride.vendor.presentation.fragments.orders

import androidx.lifecycle.liveData
import com.oride.utilities.Resource
import com.oride.vendor.R
import com.oride.vendor.data.Food
import com.oride.vendor.data.OrderTimeline
import com.oride.vendor.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
) : BaseViewModel() {

    fun fetchOrderTimeline() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    OrderTimeline(
                        id = 1,
                        time = "7:40 PM",
                        message = "Order placed",
                    ),
                    OrderTimeline(
                        id = 1,
                        time = "7:40 PM",
                        message = "Order processing",
                    ),
                    OrderTimeline(
                        id = 3,
                        time = "7:44 PM",
                        message = "Picked up by delivery person",
                    ),
                    OrderTimeline(
                        id = 4,
                        time = "7:45 PM",
                        message = "Order completed",
                    ),
                )
            )
        )
    }

    fun fetchOrderFoods() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    Food(
                        id = 1,
                        quantity = 1,
                        name = "Chicken Pizza Large",
                        location = "Queens",
                        brand = "Pizza Hut",
                        rating = 4.8,
                        totalRating = 124,
                        price = 150.00,
                        image = R.drawable.pizza
                    ),
                    Food(
                        id = 2,
                        quantity = 1,
                        name = "Hamburger Large",
                        location = "Manhattan",
                        brand = "Burger King",
                        rating = 4.8,
                        totalRating = 124,
                        price = 47.00,
                        image = R.drawable.pizza
                    ),
                    Food(
                        id = 3,
                        quantity = 2,
                        name = "Cocacola",
                        location = "Manhattan",
                        brand = "Burger King",
                        rating = 4.8,
                        totalRating = 124,
                        price = 47.00,
                        image = R.drawable.pizza
                    )
                )
            )
        )
    }
}