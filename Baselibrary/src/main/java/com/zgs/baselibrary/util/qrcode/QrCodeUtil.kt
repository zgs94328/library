package com.zgs.baselibrary.util.qrcode

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

import com.qw.soul.permission.SoulPermission
import com.qw.soul.permission.bean.Permission
import com.qw.soul.permission.bean.Permissions
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener
import com.zgs.baselibrary.core.toast


/**
 * 二维码工具类
 */
object QrCodeUtil {
//    var requestCode = 1
//    fun start(source: Any, reqCode: Int = 1){
//        requestCode = reqCode
//        SoulPermission.getInstance().checkAndRequestPermissions(
//                Permissions.build(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),  //if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
//                object : CheckRequestPermissionsListener {
//                    override fun onAllPermissionOk(allPermissions: Array<Permission?>) {
//                        if(source is Activity){
//                            source.startActivityForResult(Intent(source, CaptureActivity::class.java), reqCode)
//                        }else if(source is Fragment){
//                            source.startActivityForResult(Intent(source.context, CaptureActivity::class.java), reqCode)
//                        }
//                    }
//
//                    override fun onPermissionDenied(refusedPermissions: Array<Permission>) {
//                        toast("权限拒绝，无法使用扫描功能")
//                    }
//                })
//
//    }
//
//    /**
//     * 获取扫描结果
//     */
//    fun fetchResult(reqCode: Int, data: Intent?) : String{
//        if (data != null && requestCode ==reqCode) {
//            return data.getStringExtra(Intents.Scan.RESULT)
//        }
//        return ""
//    }
}