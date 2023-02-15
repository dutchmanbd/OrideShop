package com.oride.utilities

import java.util.regex.Pattern

object OtpParser {

    private const val REGEX = "(|^)\\d{4}"

    fun parseOtpCode(message: String?): String? {
        return if (message != null) {
            try {
                val pattern = Pattern.compile(REGEX)
                val matcher = pattern.matcher(message)
                if (matcher.find()) {
                    matcher.group(0)
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

}