package com.zgs.zgslibrarydemo.designmode.chain

/**
 * 作者　: zgs
 * 时间　: 7/21/21
 * 描述　:
 */
//client
fun main(args: Array<String>) {
    var zhangSan = ZhangSan() //张三主管
    var  lisi = Lisi()//李四经理
    zhangSan.nextHandler = lisi //主管到经理层层审批
    //员工小张请假10天
    var result = zhangSan.process(LeaveInfo("小张",10))

    println(result)//结果
}
//抽象处理,由具体处理者实现处理
abstract class Handler {
    var nextHandler : Handler? = null
    fun setNexHandler(nextHandler : Handler){
        this.nextHandler = nextHandler
    }
    abstract fun process(leaveInfo: LeaveInfo):Boolean //
}
//具体处理
class ZhangSan : Handler() {
    override fun process(leaveInfo: LeaveInfo) : Boolean{
        when(leaveInfo.day){
            in 0..3-> return true
            else -> return nextHandler!!.process(leaveInfo)
        }

    }
}
//具体处理
class Lisi : Handler() {
    override fun process(leaveInfo: LeaveInfo): Boolean {
        return true
    }
}
