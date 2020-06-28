package com.wreckingball.design.utils

import android.content.Context
import android.util.TypedValue

object ScreenUtils {
    fun dpToPixels(context: Context, dp: Int) : Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    fun pixelsToDp(context: Context, px: Int) : Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px.toFloat(), context.resources.displayMetrics).toInt()
    }
}