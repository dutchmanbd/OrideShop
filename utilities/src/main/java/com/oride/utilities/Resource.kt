package com.oride.utilities

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading(val nothing: Nothing? = null) : Resource<Nothing>()
    data class Failure(val code: Int, val message: String) : Resource<Nothing>()
}