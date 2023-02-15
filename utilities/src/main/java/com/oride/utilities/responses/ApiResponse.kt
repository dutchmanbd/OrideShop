package com.oride.utilities.responses

import android.util.Log
import com.oride.utilities.BuildConfig
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

const val UNKNOWN_ERROR = "Something went wrong, Please try again later"
const val DATA_NOT_FOUND = "Data not found"
const val NETWORK_ERROR = "Please check your internet connection"
const val JSON_PARSE_ERROR = "Data loading failed, please try again later"
const val UNKNOWN_CODE = 4345
const val DATA_NOT_FOUND_CODE = 2004

sealed class ApiResponse<T> {

    companion object {
        fun <T> create(error: String?, code: Int = 4444): ApiErrorResponse<T> {
            return ApiErrorResponse(error ?: UNKNOWN_ERROR, code)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (BuildConfig.DEBUG) {
                    Log.e("ApiResponse", "create: $body")
                }
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse(DATA_NOT_FOUND, response.code())
                } else {
                    ApiSuccessResponse(
                        body = body
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                val code = response.code()
                if (BuildConfig.DEBUG) {
                    Log.e("ApiResponse", "create: $msg")
                }
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    val sb = StringBuilder()
                    try {
                        val errorObject = JSONObject(msg)
                        when {
                            errorObject.has("message") -> {
                                sb.append(errorObject.getString("message"))
                            }
                            else -> {
                                errorObject.keys().forEach {
                                    val errorArrays = errorObject.getJSONArray(it)
                                    for (index in 0 until errorArrays.length()) {
                                        sb.append(errorArrays.getString(index))
                                        if (index < errorArrays.length() - 1) {
                                            sb.append(",")
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: JSONException) {
                        sb.append(JSON_PARSE_ERROR)
                    }
                    sb.toString()
                }
                ApiErrorResponse(errorMsg, code)
            }
        }
    }

}


class ApiEmptyResponse<T>(val errorMessage: String, val code: Int) : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String, val code: Int) : ApiResponse<T>()