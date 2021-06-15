package com.zgs.baselibrary.core

import com.elvishew.xlog.XLog
import com.zgs.baselibrary.AndroidKTX

/**
 * 作者　: zgs
 * 时间　: 4/1/21
 * 描述　:
 */
private enum class LEVEL {
    V, D, I, W, E
}

fun String.logv(tag: String = AndroidKTX.defaultLogTag) =
        XLog.tag(tag).v(this)
fun String.logd(tag: String = AndroidKTX.defaultLogTag) =
        XLog.tag(tag).d(this)
fun String.logi(tag: String = AndroidKTX.defaultLogTag) =
        XLog.tag(tag).i(this)
fun String.logw(tag: String = AndroidKTX.defaultLogTag) =
        XLog.tag(tag).w(this)
fun String.loge(tag: String = AndroidKTX.defaultLogTag) =
        XLog.tag(tag).e(this)
