package com.zgs.baselibrary.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.zgs.baselibrary.base.BaseDBFragment
import com.zgs.baselibrary.core.postDelay


/**
 * 自带StateLayout的Fragment基类
 */
abstract class BaseStateFragment<DB : ViewDataBinding> : BaseDBFragment<DB>() {
    protected var cacheView: View? = null
    protected var isInit = false

    override fun initView(savedInstanceState: Bundle?) {
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