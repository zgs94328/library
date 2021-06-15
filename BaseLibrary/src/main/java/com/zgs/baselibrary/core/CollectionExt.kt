package com.zgs.baselibrary.core

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * 把集合分成数量固定的几组
 */
fun <T> ArrayList<T>.groupByCount(count: Int = 1): List<List<T>>{
    if(count<1) throw IllegalArgumentException("count不能小于1")
    val list = arrayListOf<ArrayList<T>>()
    var subList = arrayListOf<T>()
    forEach { t->
        subList.add(t)
        if(subList.size==count){
            list.add(subList)
            subList = arrayListOf<T>()
        }
    }
    //遍历结束，subList可能不满count
    if(subList.isNotEmpty()) list.add(subList)
    return list
}

/**
 * 数组转bundle
 */
fun Array<out Pair<String, Any?>>.toBundle(): Bundle? {
    return Bundle().apply {
        forEach { it ->
            val value = it.second
            when (value) {
                null -> putSerializable(it.first, null as Serializable?)
                is Int -> putInt(it.first, value)
                is Long -> putLong(it.first, value)
                is CharSequence -> putCharSequence(it.first, value)
                is String -> putString(it.first, value)
                is Float -> putFloat(it.first, value)
                is Double -> putDouble(it.first, value)
                is Char -> putChar(it.first, value)
                is Short -> putShort(it.first, value)
                is Boolean -> putBoolean(it.first, value)
                is Serializable -> putSerializable(it.first, value)
                is Parcelable -> putParcelable(it.first, value)

                is IntArray -> putIntArray(it.first, value)
                is LongArray -> putLongArray(it.first, value)
                is FloatArray -> putFloatArray(it.first, value)
                is DoubleArray -> putDoubleArray(it.first, value)
                is CharArray -> putCharArray(it.first, value)
                is ShortArray -> putShortArray(it.first, value)
                is BooleanArray -> putBooleanArray(it.first, value)

                is Array<*> -> when {
                    value.isArrayOf<CharSequence>() -> putCharSequenceArray(it.first, value as Array<CharSequence>)
                    value.isArrayOf<String>() -> putStringArray(it.first, value as Array<String>)
                    value.isArrayOf<Parcelable>() -> putParcelableArray(it.first, value as Array<Parcelable>)
                }
            }
        }
    }

}
