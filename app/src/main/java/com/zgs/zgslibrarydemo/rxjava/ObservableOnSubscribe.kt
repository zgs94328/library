package com.zgs.zgslibrarydemo.rxjava

/**
 * 作者　: zgs
 * 时间　: 7/25/21
 * 描述　:
 */
interface ObservableOnSubscribe {
    fun onSubscribe(emitter:ObservableEmitter)
}