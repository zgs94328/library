package com.zgs.baselibrary.core


import android.os.*
import com.zgs.baselibrary.util.TimeUtils
import java.util.*


/**
 * Description:  通用扩展
 * Create by dance, at 2018/12/5
 */





fun Any.runOnUIThread(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post { action() }
}


//一天只做一次
fun Any.doOnceInDay(actionName: String = "", action: () -> Unit, whenHasDone: (()->Unit)? = null) {
    val key = "once_in_day_last_check_${actionName}"
    val today = Date()
    val todayFormat = TimeUtils.date2String(today, "yyyy-MM-dd")
    val last = sp().getString(key, "")
    if (last != null && last.isNotEmpty() && last == todayFormat) {
        //说明执行过
        whenHasDone?.invoke()
        return
    }
    sp().putString(key, todayFormat)
    action()
}

//只执行一次的行为
fun Any.doOnlyOnce(actionName: String = "", action: () -> Unit, whenHasDone: (()->Unit)? = null) {
    val key = "has_done_${actionName}"
    val hasDone = sp().getBoolean(key, false)
    if (hasDone) {
        //说明执行过
        whenHasDone?.invoke()
        return
    }
    sp().putBoolean(key, true)
    action()
}

//500毫秒内只做一次
val _innerHandler = Handler(Looper.getMainLooper())
val _actionCache = arrayListOf<String>()

/**
 * 事件节流
 * @param actionName 事件的名字
 * @param time 事件的节流时间
 * @param action 事件
 */
fun Any.doOnceIn( actionName: String, time: Long = 500, action: ()->Unit){
    if(_actionCache.contains(actionName)) return
    _actionCache.add(actionName)
    action() //执行行为
    _innerHandler.postDelayed({
        if(_actionCache.contains(actionName)) _actionCache.remove(actionName)
    }, time)
}