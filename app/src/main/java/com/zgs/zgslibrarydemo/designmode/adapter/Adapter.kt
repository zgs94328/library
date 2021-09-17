package com.zgs.zgslibrarydemo.designmode.adapter



/**
 * 作者　: zgs
 * 时间　: 7/22/21
 * 描述　:
 */
//220伏转5伏
fun main(args: Array<String>) {
    var phone = Phone()
    phone.recharge(Volt220ToVolt5Adapter())
}
//220伏特
open class Volt220{
    fun getVolt220():Int{
        return 220
    }
}
interface Volt5{
    fun getVolt5():Int
}
class Volt220ToVolt5Adapter : Volt220(),Volt5{
    override fun getVolt5(): Int {
       return getVolt220()-215
    }

}
class Phone{
    fun recharge( volt5: Volt5){
        if(volt5.getVolt5()==5){
            println("开始充电啦")
        }else{
            println("功率不符合小心爆炸")
        }
    }
}