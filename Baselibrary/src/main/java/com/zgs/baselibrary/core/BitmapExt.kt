package com.zgs.baselibrary.core

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.View
import androidx.fragment.app.Fragment
import com.zgs.baselibrary.AndroidKTX
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * 作者　: zgs
 * 时间　: 2021/3/17
 * 描述　:
 */

/** 动态创建Drawable **/
fun Context.createDrawable(color: Int = Color.TRANSPARENT, radius: Float = 0f,
                           strokeColor: Int = Color.TRANSPARENT, strokeWidth: Int = 0,
                           enableRipple: Boolean = true,
                           rippleColor: Int = Color.parseColor("#88999999"),
                           gradientStartColor: Int = 0, gradientEndColor : Int = 0, gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT): Drawable {
    val content = GradientDrawable().apply {
        cornerRadius = radius
        setStroke(strokeWidth, strokeColor)
        gradientType = GradientDrawable.LINEAR_GRADIENT
        if(gradientStartColor!=0 || gradientEndColor!=0){
            orientation = gradientOrientation
            colors = intArrayOf(gradientStartColor, gradientEndColor)
        }else{
            setColor(color)
        }
    }
    if (Build.VERSION.SDK_INT >= 21 && enableRipple) {
        return RippleDrawable(ColorStateList.valueOf(rippleColor), content, null)
    }
    return content
}

fun Fragment.createDrawable(color: Int = Color.TRANSPARENT, radius: Float = 0f,
                            strokeColor: Int = Color.TRANSPARENT, strokeWidth: Int = 0,
                            enableRipple: Boolean = true,
                            rippleColor: Int = Color.parseColor("#88999999"),
                            gradientStartColor: Int = 0, gradientEndColor : Int = 0, gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT): Drawable {
    return context!!.createDrawable(color, radius, strokeColor, strokeWidth, enableRipple, rippleColor, gradientStartColor = gradientStartColor,
            gradientEndColor = gradientEndColor, gradientOrientation = gradientOrientation)
}

fun View.createDrawable(color: Int = Color.TRANSPARENT, radius: Float = 0f,
                        strokeColor: Int = Color.TRANSPARENT, strokeWidth: Int = 0,
                        enableRipple: Boolean = true,
                        rippleColor: Int = Color.parseColor("#88999999"),
                        gradientStartColor: Int = 0, gradientEndColor : Int = 0, gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT): Drawable {
    return context!!.createDrawable(color, radius, strokeColor, strokeWidth, enableRipple, rippleColor, gradientStartColor = gradientStartColor,
            gradientEndColor = gradientEndColor, gradientOrientation = gradientOrientation)
}
/**
 * 将Bitmap保存到相册
 */
fun Bitmap.saveToAlbum(format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100, filename: String = "", callback: ((path: String?, uri: Uri?) -> Unit)? = null) {
    GlobalScope.launch {
        try {
            //1. create path
            val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/" + Environment.DIRECTORY_PICTURES
            val dirFile = File(dirPath)
            if (!dirFile.exists()) dirFile.mkdirs()
            val ext = when (format) {
                Bitmap.CompressFormat.PNG -> ".png"
                Bitmap.CompressFormat.JPEG -> ".jpg"
                Bitmap.CompressFormat.WEBP -> ".webp"
                else -> ".png"
            }
            val target = File(dirPath, (if (filename.isEmpty()) System.currentTimeMillis().toString() else filename) + ext)
            if (target.exists()) target.delete()
            target.createNewFile()
            //2. save
            compress(format, quality, FileOutputStream(target))
            //3. notify
            MediaScannerConnection.scanFile(
                AndroidKTX.context, arrayOf(target.absolutePath),
                    arrayOf("image/$ext")) { path, uri ->
                runOnUIThread {
                    callback?.invoke(path, uri)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            runOnUIThread { callback?.invoke(null, null) }
        }
    }
}
