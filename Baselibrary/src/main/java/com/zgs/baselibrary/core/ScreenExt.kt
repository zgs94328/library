package com.zgs.baselibrary.core

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import com.zgs.baselibrary.util.Utils


/**
 * 作者　: zgs
 * 时间　: 2020/12/28
 * 描述　:
 */

/**
 * 获取屏幕宽度
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * 获取屏幕高度
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

/**
 * dp值转换为px
 */
fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * px值转换成dp
 */
fun Context.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * dp值转换为px
 */
fun View.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * px值转换成dp
 */
fun View.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * sp值转换成px
 */
fun View.sp2px(sp: Int): Int {
    val scale = resources.displayMetrics.scaledDensity
    return (sp*scale+0.5f).toInt()
}



/**
 * Value of pt to value of px.
 *
 * @param ptValue The value of pt.
 * @return value of px
 */
fun Context.pt2Px(ptValue: Float): Int {
    val metrics: DisplayMetrics = Utils.getApp().getResources().getDisplayMetrics()
    return (ptValue * metrics.xdpi / 72f + 0.5).toInt()
}
