package com.zgs.baselibrary.core

import android.content.Context
import com.tencent.mmkv.MMKV
import com.zgs.baselibrary.AndroidKTX

/**
 * Description: SharedPreferences相关
 * Create by dance, at 2018/12/5
 */

//fun Any.sp(name: String = AndroidKTX.sharedPrefName) = AndroidKTX.context.getSharedPreferences(name, Context.MODE_PRIVATE)
fun Any.sp(name: String = AndroidKTX.sharedPrefName) = MMKV.mmkvWithID(name, Context.MODE_PRIVATE)

/**
 * 批处理
 */
fun MMKV.edit(action:() -> Unit) {
    edit().apply { action() }.apply()
}

/**
 * 对象操作
 */
fun MMKV.putObject(key: String, obj: Any?) {
    putString(key, obj?.toJson()?:"")
}

inline fun <reified T> MMKV.getObject(key: String): T? {
    val string = getString(key, null)
    if(string==null || string.isEmpty())return null
    return getString(key, null)?.toBean<T>()
}


/**
 *  put系列
 */
fun MMKV.putString(key: String, value: String) {
    edit { putString(key, value) }
}

fun MMKV.putInt(key: String, value: Int) {
    edit { putInt(key, value) }
}

fun MMKV.putBoolean(key: String, value: Boolean) {
    edit { putBoolean(key, value) }
}

fun MMKV.putFloat(key: String, value: Float) {
    edit { putFloat(key, value) }
}

fun MMKV.putLong(key: String, value: Long) {
    edit { putLong(key, value) }
}

fun MMKV.putStringSet(key: String, value: Set<String>) {
    edit { putStringSet(key, value) }
}

fun MMKV.removeKey(key: String) {
    edit { removeValueForKey(key) }
}
fun MMKV.clear() {
    edit { clear() }
}
