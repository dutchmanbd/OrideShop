package com.oride.utilities

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String? = null
)