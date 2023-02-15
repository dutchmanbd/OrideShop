package com.oride.utilities

sealed class ValidationEvent {
    object Success : ValidationEvent()
    data class Failure(val text: String, val code: Int): ValidationEvent()
}