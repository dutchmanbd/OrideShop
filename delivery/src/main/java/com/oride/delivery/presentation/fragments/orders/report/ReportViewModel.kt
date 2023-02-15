package com.oride.delivery.presentation.fragments.orders.report

import androidx.lifecycle.liveData
import com.oride.delivery.data.ReportQuestion
import com.oride.delivery.presentation.base.BaseViewModel
import com.oride.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
) : BaseViewModel() {

    fun fetchReportQuestion() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    ReportQuestion(
                        id = 1,
                        question = "User didn't received my call",
                        desc = "",
                        isOther = false,
                    ),
                    ReportQuestion(
                        id = 2,
                        question = "User's behavior made me feel unsafe",
                        desc = "",
                        isOther = false,
                    ),
                    ReportQuestion(
                        id = 4,
                        question = "Other",
                        desc = "",
                        isOther = true,
                    )
                )
            )
        )
    }
}