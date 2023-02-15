package com.oride.vendor.presentation.fragments.dish

import androidx.lifecycle.liveData
import com.oride.utilities.Resource
import com.oride.vendor.R
import com.oride.vendor.data.Dish
import com.oride.vendor.data.Size
import com.oride.vendor.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DishViewModel @Inject constructor(
) : BaseViewModel() {

    fun fetchDishes() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    Dish(
                        id = 1,
                        name = "BBQ Pizza",
                        ingredients = "Canned black beans, pizza crust, skinless chicken breasts and more",
                        image = R.drawable.pizza,
                        desc = "Double tap if you could live on meat alone. For a limited time, get a Large Meat Lover’s Pizza, a Pizza Hut favorite",
                        sizes = listOf(
                            Size(
                                id = 1,
                                size = "S"
                            ),
                            Size(
                                id = 2,
                                size = "M"
                            ),
                            Size(
                                id = 3,
                                size = "L"
                            ),
                            Size(
                                id = 4,
                                size = "EL"
                            )
                        ),
                        price = 15.00,
                        isAvailable = true
                    ),
                    Dish(
                        id = 2,
                        name = "Hamburger",
                        ingredients = "Canned black beans, pizza crust, skinless chicken breasts and more",
                        image = R.drawable.pizza,
                        desc = "Double tap if you could live on meat alone. For a limited time, get a Large Meat Lover’s Pizza, a Pizza Hut favorite",
                        sizes = listOf(
                            Size(
                                id = 1,
                                size = "S"
                            ),
                            Size(
                                id = 2,
                                size = "M"
                            ),
                            Size(
                                id = 3,
                                size = "L"
                            ),
                            Size(
                                id = 4,
                                size = "EL"
                            )
                        ),
                        price = 7.00,
                        isAvailable = true
                    ),
                    Dish(
                        id = 3,
                        name = "Broccoli Pasta",
                        ingredients = "Canned black beans, pizza crust, skinless chicken breasts and more",
                        image = R.drawable.pizza,
                        desc = "Double tap if you could live on meat alone. For a limited time, get a Large Meat Lover’s Pizza, a Pizza Hut favorite",
                        sizes = listOf(
                            Size(
                                id = 1,
                                size = "S"
                            ),
                            Size(
                                id = 2,
                                size = "M"
                            ),
                            Size(
                                id = 3,
                                size = "L"
                            ),
                            Size(
                                id = 4,
                                size = "EL"
                            )
                        ),
                        price = 12.00,
                        isAvailable = false
                    ),
                    Dish(
                        id = 4,
                        name = "BBQ Pizza",
                        ingredients = "Canned black beans, pizza crust, skinless chicken breasts and more",
                        image = R.drawable.pizza,
                        desc = "Double tap if you could live on meat alone. For a limited time, get a Large Meat Lover’s Pizza, a Pizza Hut favorite",
                        sizes = listOf(
                            Size(
                                id = 1,
                                size = "S"
                            ),
                            Size(
                                id = 2,
                                size = "M"
                            ),
                            Size(
                                id = 3,
                                size = "L"
                            ),
                            Size(
                                id = 4,
                                size = "EL"
                            )
                        ),
                        price = 15.00,
                        isAvailable = true
                    ),
                )
            )
        )
    }
}