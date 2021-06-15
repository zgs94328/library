package com.zgs.baselibrary.core

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * 作者　: zgs
 * 时间　: 2021/3/17
 * 描述　:
 */

/** json相关 **/
fun Any.toJson(dateFormat: String = "yyyy-MM-dd HH:mm:ss") = GsonBuilder().setDateFormat(dateFormat).create().toJson(this)

inline fun <reified T> String.toBean(dateFormat: String = "yyyy-MM-dd HH:mm:ss") = GsonBuilder().setDateFormat(dateFormat).create()
        .fromJson<T>(this, object : TypeToken<T>() {}.type)

