package com.oride.extensions.bitmap

import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import coil.load
import coil.request.CachePolicy
import coil.transform.Transformation
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.imageview.ShapeableImageView
import com.oride.extensions.R


fun ImageView.load(url: String?, transformation: Transformation) {
    if (this is ShapeableImageView) {
        this.setBackgroundColor(
            ContextCompat.getColor(this.context, R.color.grey)
        )
    }
    this.load(url) {
        diskCachePolicy(CachePolicy.DISABLED)
        memoryCachePolicy(CachePolicy.DISABLED)
        allowHardware(false)
        bitmapConfig(Bitmap.Config.ARGB_8888)
        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setBaseAlpha(0.7f)
            .setHighlightAlpha(0.6f)
            .build()
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        placeholder(shimmerDrawable)
        transformations(transformation)
    }
}

fun ImageView.load(
    @DrawableRes drawableId: Int,
    transformation: Transformation
) {
    this.load(drawableId) {
        allowHardware(false)
        bitmapConfig(Bitmap.Config.ARGB_8888)
        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setBaseAlpha(0.7f)
            .setHighlightAlpha(0.6f)
            .build()
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        placeholder(shimmerDrawable)
        transformations(transformation)
    }
}

fun ImageView.load(url: String?) {
    if (this is ShapeableImageView) {
        this.setBackgroundColor(
            ContextCompat.getColor(this.context, R.color.grey)
        )
    }
    this.load(url) {
        diskCachePolicy(CachePolicy.DISABLED)
        memoryCachePolicy(CachePolicy.DISABLED)
        allowHardware(false)
        bitmapConfig(Bitmap.Config.ARGB_8888)
        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setBaseAlpha(0.7f)
            .setHighlightAlpha(0.6f)
            .build()
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        placeholder(shimmerDrawable)
    }
}

fun ImageView.load(
    @DrawableRes drawableId: Int
) {
    this.load(drawableId) {
        crossfade(true)
        allowHardware(false)
        bitmapConfig(Bitmap.Config.ARGB_8888)
        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setBaseAlpha(0.7f)
            .setHighlightAlpha(0.6f)
            .build()
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        placeholder(shimmerDrawable)
    }
}

fun ImageView.load(
    bitmap: Bitmap?,
    transformation: Transformation
) {
    if (this is ShapeableImageView) {
        this.setBackgroundColor(
            ContextCompat.getColor(this.context, R.color.grey)
        )
    }

    this.load(bitmap) {
        transformations(transformation)
    }
}

fun ImageView.load(
    uri: Uri?,
    transformation: Transformation
) {
    if (this is ShapeableImageView) {
        this.setBackgroundColor(
            ContextCompat.getColor(this.context, R.color.grey)
        )
    }

    this.load(uri) {
    }
}
