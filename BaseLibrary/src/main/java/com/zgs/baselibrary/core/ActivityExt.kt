package com.zgs.baselibrary.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zgs.baselibrary.util.handler.LifecycleHandler


/**
 * Description: Activity相关
 * Create by lxj, at 2018/12/7
 */

inline fun <reified T> Fragment.start(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null) {
    val intent = Intent(activity, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        }
        if (bundle != null) putExtras(bundle.toBundle()!!)
    }
    startActivity(intent)
}

inline fun <reified T> Fragment.startForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    val intent = Intent(activity, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        }
        if (bundle != null) putExtras(bundle.toBundle()!!)
    }
    startActivityForResult(intent, requestCode)
}

inline fun <reified T> Context.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null) {
    val intent = Intent(this, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        }
        if (this !is Activity) {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (bundle != null) putExtras(bundle.toBundle()!!)
    }
    startActivity(intent)
}

inline fun <reified T> View.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null) {
    context.startActivity<T>(flag, bundle)
}

inline fun <reified T> View.startForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    (context as Activity).startForResult<T>(flag, bundle, requestCode)
}

inline fun <reified T> Activity.startForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    val intent = Intent(this, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        }
        if (bundle != null) putExtras(bundle.toBundle()!!)
    }
    startActivityForResult(intent, requestCode)
}

fun FragmentActivity.finishDelay(delay: Long = 1) {
    LifecycleHandler(this).postDelayed({ finish() }, delay)
}

//post, postDelay
fun FragmentActivity.post(action: ()->Unit){
    LifecycleHandler(this).post { action() }
}

fun FragmentActivity.postDelay(delay:Long = 0, action: ()->Unit){
    LifecycleHandler(this).postDelayed({ action() }, delay)
}




inline val FragmentActivity.handler
    get() = LifecycleHandler(this, Looper.getMainLooper())

inline val Fragment.handler
    get() = LifecycleHandler(this, Looper.getMainLooper())