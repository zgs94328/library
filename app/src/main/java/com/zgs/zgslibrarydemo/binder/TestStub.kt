package com.zgs.zgslibrarydemo.binder

import android.os.Binder
import android.os.Parcel

/**
 * 作者　: zgs
 * 时间　: 8/25/21
 * 描述　:
 */
class TestStub : Binder() {
    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        return super.onTransact(code, data, reply, flags)
    }
}