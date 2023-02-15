package com.oride.extensions.string

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.text.isDigitsOnly
import java.util.*

private const val KG_TO_POUND = 2.205

fun String.highlight(
    query: String?,
    @ColorInt colorInt: Int = Color.BLACK
): SpannableString {
    val spannable = SpannableString(this)
    query?.let {
        val startIndex =
            this.lowercase(Locale.getDefault()).indexOf(it.lowercase(Locale.getDefault()))
        val endIndex = startIndex + it.length
        if (startIndex != -1) {
            spannable.setSpan(
                ForegroundColorSpan(colorInt),
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }
    return spannable
}

fun String.clickableSpan(
    query: String?,
    @ColorInt colorInt: Int = Color.BLACK,
    action: (() -> Unit)? = null
): SpannableString {
    val spannable = SpannableString(this)
    query?.let {
        val startIndex =
            this.lowercase(Locale.getDefault()).indexOf(it.lowercase(Locale.getDefault()))
        val endIndex = startIndex + it.length
        if (startIndex != -1) {
            val clickSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    action?.invoke()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
//                    ds.color = colorInt
                    ds.isUnderlineText = false
                }
            }
//            spannable.setSpan(
//                ForegroundColorSpan(colorInt),
//                startIndex,
//                endIndex,
//                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
//            )
            spannable.setSpan(clickSpan, startIndex, endIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }
    return spannable
}


fun String.capitalize(): String {
    return replaceFirstChar {
        it.uppercase(Locale.getDefault())
    }
}


fun String.replace(startIndex: Int, endIndex: Int, replaceChar: Char): String {
    val text = this
    val sb = StringBuilder()
    for (index in startIndex until endIndex) {
        sb.append(replaceChar)
    }
    return "${text.substring(0, startIndex)}$sb"
}

fun String.convertToStringArray(): List<String> = this.split("-")

fun String.containLetters(): Boolean {
    return this.any { it.isLetter() }
}

fun String.isPhoneNumber(): Boolean {
    return !containLetters()
}





