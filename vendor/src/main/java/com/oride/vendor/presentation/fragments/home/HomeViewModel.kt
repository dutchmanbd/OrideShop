package com.oride.vendor.presentation.fragments.home

import androidx.lifecycle.liveData
import com.oride.utilities.Resource
import com.oride.vendor.R
import com.oride.vendor.data.Food
import com.oride.vendor.data.Order
import com.oride.vendor.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel() {

    fun fetchCurrentOrders() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    Order(
                        id = 1,
                        orderId = "#1234",
                        orderTime = "02:25 PM",
                        userName = "Peter Parkar",
                        userPhoto = R.drawable.user_image,
                        orderItems = listOf(
                            Food(
                                id = 1,
                                quantity = 1,
                                name = "BBQ Pizza",
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
                                quantity = 1,
                                name = "Broccoli Pasta",
                                location = "Queens",
                                brand = "Blue Fin",
                                rating = 4.7,
                                totalRating = 124,
                                price = 150.00,
                                image = R.drawable.pizza
                            )
                        ),
                        isPickedUp = true,
                        isCompleted = false,
                        vat = 5.0,
                        totalPrice = 45.00,
                    ),
                    Order(
                        id = 2,
                        orderId = "#1234",
                        orderTime = "02:25 PM",
                        userName = "Peter Parkar",
                        userPhoto = R.drawable.user_image,
                        orderItems = listOf(
                            Food(
                                id = 1,
                                quantity = 1,
                                name = "BBQ Pizza",
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
                            )
                        ),
                        isPickedUp = false,
                        isCompleted = false,
                        vat = 5.0,
                        totalPrice = 45.00,
                    ),
                    Order(
                        id = 3,
                        orderId = "#1234",
                        orderTime = "02:25 PM",
                        userName = "Peter Parkar",
                        userPhoto = R.drawable.user_image,
                        orderItems = listOf(
                            Food(
                                id = 1,
                                quantity = 1,
                                name = "BBQ Pizza",
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
                                quantity = 1,
                                name = "Broccoli Pasta",
                                location = "Queens",
                                brand = "Blue Fin",
                                rating = 4.7,
                                totalRating = 124,
                                price = 150.00,
                                image = R.drawable.pizza
                            )
                        ),
                        isPickedUp = false,
                        isCompleted = false,
                        vat = 5.0,
                        totalPrice = 45.00,
                    ),
                    Order(
                        id = 4,
                        orderId = "#1234",
                        orderTime = "02:25 PM",
                        userName = "Peter Parkar",
                        userPhoto = R.drawable.user_image,
                        orderItems = listOf(
                            Food(
                                id = 1,
                                quantity = 1,
                                name = "BBQ Pizza",
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
                                quantity = 1,
                                name = "Broccoli Pasta",
                                location = "Queens",
                                brand = "Blue Fin",
                                rating = 4.7,
                                totalRating = 124,
                                price = 150.00,
                                image = R.drawable.pizza
                            ),
                            Food(
                                id = 4,
                                quantity = 1,
                                name = "Shrimp with Chease",
                                location = "Time Square",
                                brand = "Holiday Inn",
                                rating = 4.6,
                                totalRating = 124,
                                price = 47.00,
                                image = R.drawable.pizza
                            )
                        ),
                        isPickedUp = false,
                        isCompleted = false,
                        vat = 5.0,
                        totalPrice = 45.00,
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