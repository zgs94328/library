package com.zgs.baselibrary.base

import androidx.databinding.ViewDataBinding
import com.zgs.baselibrary.base.TitleBarActivity
import com.zgs.baselibrary.core.postDelay

abstract class StateTitleBarActivity<T: ViewDataBinding> : TitleBarActivity<T>(){
    override fun initView() {
        super.initView()
        showLoading()
        if(autoShowContent()) postDelay(400){showContent()}
    }
    /**
     * 用来对StateLayout进行各种配置
     */
    open fun onConfigStateLayout(){

    }

    abstract fun showContent()
    abstract fun showLoading()
    abstract fun showError()
    abstract fun showEmpty()

    //是否自动显示Content
    open fun autoShowContent() = false

}