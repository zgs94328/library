package com.zgs.zgslibrarydemo

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 作者　: zgs
 * 时间　: 8/12/21
 * 描述　:
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }
}