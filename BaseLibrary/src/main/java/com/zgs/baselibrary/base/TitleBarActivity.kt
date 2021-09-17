package com.zgs.baselibrary.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.zgs.baselibrary.R
import com.zgs.baselibrary.util.BarUtils

import com.zgs.baselibrary.core.click
import com.zgs.baselibrary.core.gone
import com.zgs.baselibrary.core.height
import com.zgs.baselibrary.core.visible
import kotlinx.android.synthetic.main._ktx_activity_titlebar.*

/**
 *  @author 张国胜
 *  @time 2020/7/5
 *  @desc: 如果不显示标题栏 showTitle 传false
 */
abstract class TitleBarActivity<T: ViewDataBinding>(private var showTitle:Boolean = true) : AdaptActivity<T>(){
    override fun getLayoutId(): Int {
        return R.layout._ktx_activity_titlebar
    }

    override fun initView(savedInstanceState: Bundle?) {
        flBody.addView(View.inflate(this, getBodyLayout(), null))
        if(!showTitle){
            hideTitleBar()
            return
        }
        showTitleBar()
        fakeTitleBar.height(BarUtils.getStatusBarHeight())
        titleBar.leftImageView().click { finish() }
    }

    fun setStatusBarColor(color: Int) = fakeTitleBar.setBackgroundColor(color)

    fun titleBar() = titleBar

    fun hideTitleDivider() = titleDivider.gone()

    abstract fun getBodyLayout(): Int

    fun hideTitleBar(){
        titleBar().gone()
        fakeTitleBar.gone()
        titleDivider.gone()
    }
    fun showTitleBar(){
        titleBar().visible()
        fakeTitleBar.visible()
        titleDivider.visible()
    }
}