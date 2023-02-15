package com.oride.utilities.responses

import com.google.gson.annotations.SerializedName

data class ResponseDTO<E>(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val dto: E?
)