package com.zgs.baselibrary.base

import android.graphics.Color
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.zgs.baselibrary.util.BarUtils


/**
 * Description:
 * Create by dance, at 2019/5/16
 */
abstract class AdaptActivity<T: ViewDataBinding> : BaseDBActivity<T>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarLightMode(this,isLightMode())//显示黑字体
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT).setBackgroundResource(getStatusBarColor())
        super.onCreate(savedInstanceState)
    }
    open fun isLightMode() = true

    open fun getStatusBarColor() = android.R.color.transparent


}