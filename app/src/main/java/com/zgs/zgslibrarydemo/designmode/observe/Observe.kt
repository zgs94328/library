package com.zgs.zgslibrarydemo.designmode.observe

import java.util.*


/**
 * 作者　: zgs
 * 时间　: 7/21/21
 * 描述　:
 */
//client
fun main(args: Array<String>) {
    var observe = ConcreteObserve()
    var observable = ConcreteObservable()
    observable.addObserve(observe)
    observable.notifyObserve()
}
interface Observable{
    fun addObserve(o:Observe)
    fun removeObserve(o:Observe)
    fun notifyObserve()
}
class ConcreteObservable:Observable{
    //存放观察者列表的集合
    var vector = Vector<Observe>()
    override fun addObserve(o: Observe) {
        vector.add(o)
    }

    override fun removeObserve(o: Observe) {
        vector.remove(o)
    }

    override fun notifyObserve() {
        for(o in vector){
            o.doSomething()
        }
    }

}
interface Observe{
    fun doSomething()
}
class ConcreteObserve:Observe{
    override fun doSomething() {
        println("hehe")
    }

}