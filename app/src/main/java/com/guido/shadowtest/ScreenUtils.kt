package com.guido.shadowtest

import android.util.DisplayMetrics

object ScreenUtils {

    fun pixelsToDp(px: Int, density: Int): Int {
        return (px / (density.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun dpToPixel(dp: Float, density: Int): Int {
        return (dp * (density.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }
}