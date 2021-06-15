package com.zgs.baselibrary.core

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.qw.soul.permission.SoulPermission

/**
 * 作者　: zgs
 * 时间　: 2021/3/10
 * 描述　:
 */
fun Activity.showPermissionErrDialog(permissionStr:String){
    AlertDialog.Builder(this)
            .setTitle("提示")
            .setMessage(permissionStr + "异常，请前往设置－>权限管理，打开" + permissionStr + "。")
            .setPositiveButton("去设置") { p0, p1 -> //去设置页
                SoulPermission.getInstance().goPermissionSettings();
            }.create().show();
}