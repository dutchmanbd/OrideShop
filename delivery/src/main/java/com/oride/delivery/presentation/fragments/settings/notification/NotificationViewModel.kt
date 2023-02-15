package com.oride.delivery.presentation.fragments.settings.notification

import androidx.lifecycle.liveData
import com.oride.utilities.Resource
import com.oride.delivery.data.Notification
import com.oride.delivery.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
) : BaseViewModel() {

    fun fetchNotification() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    Notification(id = 1, title = "You have an order", description = "You get a order from chris patt", action = "Accept"),
                    Notification(id = 2, title = "Order completed", description = "Your order id #1235 mark as completed", action = "Marked as reed"),
                    Notification(id = 3, title = "Order picked up", description = "Your order id #1234 has picked up by Peter ", action = "Marked as reed")
                )
            )
        )
    }
}