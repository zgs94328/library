package com.zgs.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

/**
 * Description:
 * Create by dance, at 2019/5/16
 */
abstract class BaseDBFragment<DB : ViewDataBinding>: Fragment(){
    //该类绑定的ViewDataBinding
    lateinit var mDatabind: DB
    //是否第一次加载
    var isFirst: Boolean = true
    lateinit var mActivity: AppCompatActivity
    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mDatabind = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mDatabind.lifecycleOwner = this
        return mDatabind.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        initView(savedInstanceState)
        initData()

    }



    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //等待view加载后触发懒加载
            view?.post {
                lazyLoadData()
                isFirst = false
            }
        }
    }
    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData()
}