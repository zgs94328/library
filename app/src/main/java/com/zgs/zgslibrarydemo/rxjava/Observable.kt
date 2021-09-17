package com.zgs.zgslibrarydemo.rxjava

/**
 * 作者　: zgs
 * 时间　: 7/25/21
 * 描述　:
 */
abstract class Observable {
    companion object{
        fun create(subscribe: ObservableOnSubscribe):Observable{
            return ObservableCreate(subscribe)
        }
    }
}