package com.oride.utilities.middleware

interface TokenWrapper {
    fun getToken(): String
//    fun getRefreshToken(): String
    fun saveToken(token: String)
//    fun saveRefreshToken(refreshToken: String)
}