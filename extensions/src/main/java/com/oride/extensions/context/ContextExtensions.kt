package com.oride.extensions.context

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.LocaleList
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.oride.extensions.intent.clearTask
import com.oride.extensions.intent.newTask
import java.util.*


fun Context.showAlert(
    title: String,
    message: String?,
    cancelable: Boolean = false,
    onOk: (() -> Unit)? = null
) {
    AlertDialog.Builder(this)
        .setCancelable(cancelable)
        .setTitle(title)
        .setMessage(message ?: "")
        .setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
            onOk?.invoke()
        }
        .create()
        .show()
}


fun Context.getResStringLanguage(@StringRes id: Int, lang: String): String {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        //Get default locale to back it
        val res: Resources = resources
        val conf: Configuration = res.configuration
        val savedLocale: Locale = conf.locale
        //Retrieve resources from desired locale
        val confAr: Configuration = resources.configuration
        confAr.locale = Locale(lang)
        val metrics = DisplayMetrics()
        val resources = Resources(assets, metrics, confAr)
        //Get string which you want
        val string: String = resources.getString(id)
        //Restore default locale
        conf.locale = savedLocale
        res.updateConfiguration(conf, null)
        //return the string that you want
        return string
    } else {
        val resources = resources
        val configuration = Configuration(resources.configuration)
        val locale = Locale(lang)
        val localeList = LocaleList(locale)
        configuration.setLocales(localeList)
        return createConfigurationContext(configuration).getString(id)
    }
}


/**
 * @param message
 * @param length
 * @param position
 * default length is LENGTH_SHORT
 *   @see Toast.LENGTH_SHORT
 *   @see Toast.LENGTH_SHORT
 * default position BOTTOM
 *   @see Gravity.TOP
 *   @see Gravity.BOTTOM
 *   @see Gravity.LEFT
 *   @see Gravity.RIGHT
 *   @see Gravity.CENTER
 *   @see Gravity.CENTER_HORIZONTAL
 *   @see Gravity.CENTER_VERTICAL
 *   @see Gravity.FILL_VERTICAL
 *   @see Gravity.FILL_HORIZONTAL
 *   @see Gravity.FILL
 *   @see Gravity.CLIP_VERTICAL
 *   @see Gravity.CLIP_HORIZONTAL
 *
 */

fun Context.toast(
    message: String,
    length: Int = Toast.LENGTH_SHORT,
    position: Int = Gravity.BOTTOM
) {
    val toast = Toast.makeText(this, message, length)
    toast.setGravity(position, 0, 0)
    toast.show()
}


fun Context.toast(
    @StringRes stringRes: Int,
    length: Int = Toast.LENGTH_SHORT,
    position: Int = Gravity.BOTTOM
) {
    val toast = Toast.makeText(this, stringRes, length)
    toast.setGravity(position, 0, 0)
    toast.show()
}


/**
 * @return true if device is tablet otherwise false
 */
fun Context.isTablet(): Boolean {
    val xlarge = resources
        .configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == 4
    val large = resources
        .configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
    return xlarge || large
}


fun Context.toBitmapDescriptor(
    @DrawableRes drawableResId: Int
): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, drawableResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap =
        Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}


fun Context.toBitmap(
    @DrawableRes drawableResId: Int,
    @ColorRes tintColor: Int? = null
): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    // add the tint if it exists
    tintColor?.let {
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, it))
    }
    val canvas = Canvas(bm)
    drawable.draw(canvas)
    return bm
}


fun Context.isDarkMode(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
}


fun Context.getColor(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun Context.getColorStateList(@ColorRes colorId: Int): ColorStateList? {
    return ContextCompat.getColorStateList(this, colorId)
}


/**
 * @param layoutId
 * find navigation controller using param layoutId
 * @return this host's navigation controller
 *
 */
fun AppCompatActivity.findNavController(
    @IdRes layoutId: Int
) = (supportFragmentManager.findFragmentById(layoutId) as NavHostFragment).navController


fun AppCompatActivity.start(intent: Intent) {
    startActivity(intent)
}

fun AppCompatActivity.startAndFinish(intent: Intent) {
    startActivity(
        intent
            .newTask()
            .clearTask()
    )
    finish()
}


fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            else -> false
        }
    }
    return connectivityManager.activeNetworkInfo?.isAvailable ?: false
}


fun Context.dpToPx(dp: Float): Float {
    return (dp * resources.displayMetrics.density)
}

fun Context.getHeight(): Int {
    return resources.displayMetrics.heightPixels
}

fun Context.getWidth(): Int {
    return resources.displayMetrics.widthPixels
}

fun Context.getDensityDpi(): Int {
    return resources.displayMetrics.densityDpi
}

fun Context.getScaledDensity(): Float {
    return resources.displayMetrics.scaledDensity
}

fun Context.getDensity(): Float {
    return resources.displayMetrics.density
}












