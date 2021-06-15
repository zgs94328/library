package com.zgs.baselibrary

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.luck.picture.lib.app.IApp
import com.luck.picture.lib.app.PictureAppMaster
import com.luck.picture.lib.engine.PictureSelectorEngine
import com.tencent.mmkv.MMKV
import com.zgs.baselibrary.util.Utils
import com.zgs.baselibrary.widget.grid.PictureSelectorEngineImp



/**
 * Description: 统一配置扩展方法中的变量
 * Create by lxj, at 2018/12/4
 */
@SuppressLint("StaticFieldLeak")
object AndroidKTX:IApp{
    lateinit var context: Context
    var isDebug = true
    var defaultLogTag = "androidktx"
    var sharedPrefName = "androidktx"
    var sharedPrefName222 = "androidktx"

    /**
     * 初始化配置信息，必须调用
     * @param isDebug 是否是debug模式，默认为true
     */
    fun init(context: Context,
             isDebug: Boolean = true,
             defaultLogTag: String = AndroidKTX.defaultLogTag,
             sharedPrefName: String = AndroidKTX.sharedPrefName
    ) {
        AndroidKTX.context = context
        AndroidKTX.isDebug = isDebug
        AndroidKTX.defaultLogTag = defaultLogTag
        AndroidKTX.sharedPrefName = sharedPrefName
        if(context is Application){
            Utils.init(context)
        }
        MMKV.initialize(context);
        PictureAppMaster.getInstance().app = this
        XLog.init(if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE)
    }


    override fun getAppContext(): Context {
        return context
    }

    override fun getPictureSelectorEngine(): PictureSelectorEngine {
        return PictureSelectorEngineImp()
    }


}