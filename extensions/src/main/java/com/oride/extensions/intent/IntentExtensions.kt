package com.oride.extensions.intent

import android.content.Intent


fun Intent.singleTop(): Intent {
    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    return this
}

fun Intent.newTask(): Intent {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK
    return this
}

fun Intent.clearTask(): Intent {
    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    return this
}