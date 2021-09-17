package com.zgs.baselibrary.base

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.elvishew.xlog.XLog
import com.zgs.baselibrary.R
import com.zgs.baselibrary.AndroidKTX

import com.zgs.baselibrary.core.replace
import com.zgs.baselibrary.core.toast


class FragmentWrapperActivity<T: ViewDataBinding> : TitleBarActivity<T>() {

    companion object{
        fun start(title: String, fragmentName: String, bundle: Bundle? = null){
            val intent = Intent(AndroidKTX.context, FragmentWrapperActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra("title", title)
                putExtra("fragment", fragmentName)
                putExtra("bundle", bundle)
            }
            if(fragmentName.isNullOrEmpty()) {
                toast("Fragment的名字不能为空")
                return
            }
            AndroidKTX.context.startActivity(intent)
        }
    }

    override fun getBodyLayout() = R.layout._ktx_activity_frag_wrapper

    override fun initData() {
        val bundle = intent?.getBundleExtra("bundle")
        intent.getStringExtra("title")?.let { titleBar().setup(title = it, leftImageRes = R.mipmap._ktx_ic_back) }
        replace(R.id.flWrapper, Fragment.instantiate(this,
            intent.getStringExtra("fragment").toString(),
            bundle))
        XLog.e(bundle)
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun observe() {
    }
}