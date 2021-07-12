package com.zgs.httplibrary

import android.app.Application
import android.content.Context

/**
 * 作者　: zgs
 * 时间　: 6/15/21
 * 描述　:
 */
object Http {
    lateinit var context: Context
    var isDebug = true
    var defaultLogTag = "okhttp"
    /**
     * 初始化配置信息，必须调用
     * @param isDebug 是否是debug模式，默认为true
     */
    fun init(context: Context,
             isDebug: Boolean = true,
             defaultLogTag: String = Http.defaultLogTag

    ) {
        Http.context = context
        Http.isDebug = isDebug
        Http.defaultLogTag = defaultLogTag


    }
}