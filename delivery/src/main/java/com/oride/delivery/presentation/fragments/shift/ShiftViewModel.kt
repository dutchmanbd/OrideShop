package com.oride.delivery.presentation.fragments.shift

import androidx.lifecycle.liveData
import com.oride.delivery.data.ShiftTime
import com.oride.delivery.presentation.base.BaseViewModel
import com.oride.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShiftViewModel @Inject constructor() : BaseViewModel() {

    fun fetchShiftTodayTime() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    ShiftTime(
                        id = 1,
                        time = "4:00 PM to 8:00 PM",
                        isAvailable = false,
                        isBooked = false,
                    ),
                    ShiftTime(
                        id = 2,
                        time = "8:00 PM to 12:00 AM",
                        isAvailable = true,
                        isBooked = false,
                    )
                )
            )
        )
    }

    fun fetchShiftTomorrowTime() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    ShiftTime(
                        id = 1,
                        time = "12:00 AM to 4:00 AM",
                        isAvailable = true,
                        isBooked = false,
                    ),
                    ShiftTime(
                        id = 2,
                        time = "4:00 AM to 8:00 AM",
                        isAvailable = true,
                        isBooked = false,
                    ),
                    ShiftTime(
                        id = 3,
                        time = "8:00 AM to 12:00 PM",
                        isAvailable = true,
                        isBooked = false,
                    ),
                    ShiftTime(
                        id = 4,
                        time = "12:00 PM to 4:00 PM",
                        isAvailable = false,
                        isBooked = true,
                    ),
                    ShiftTime(
                        id = 5,
                        time = "4:00 PM to 8:00 PM",
                        isAvailable = false,
                        isBooked = true,
                    ),
                    ShiftTime(
                        id = 6,
                        time = "8:00 PM to 12:00 AM",
                        isAvailable = true,
                        isBooked = false,
                    ),
                )
            )
        )
    }
}