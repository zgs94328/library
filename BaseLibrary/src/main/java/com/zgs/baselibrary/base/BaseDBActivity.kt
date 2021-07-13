package com.zgs.baselibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Description:
 * Create by dance, at 2019/5/16
 */
abstract class BaseDBActivity<T:ViewDataBinding> : AppCompatActivity() {
    private lateinit var mDatabind: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeInitLayout(savedInstanceState)
        mDatabind = DataBindingUtil.setContentView(this, getLayoutId())
        mDatabind.lifecycleOwner = this
        initView(savedInstanceState)
        initData()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData()
    protected abstract fun observe()
    private fun beforeInitLayout(savedInstanceState: Bundle?){

    }


}