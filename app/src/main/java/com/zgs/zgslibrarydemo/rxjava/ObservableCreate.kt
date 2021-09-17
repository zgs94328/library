package com.zgs.zgslibrarydemo.rxjava

/**
 * 作者　: zgs
 * 时间　: 7/25/21
 * 描述　:
 */
class ObservableCreate(var subscribe: ObservableOnSubscribe) : Observable() {

    init {
        source = subscribe
    }

    companion object {
        var source: ObservableOnSubscribe? = null
        fun subcribe() {
            var emitter = CreateEmitter()
            source?.onSubscribe(emitter)
        }
    }

    class CreateEmitter : ObservableEmitter, Disposble {

    }
}