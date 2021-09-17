package com.zgs.zgslibrarydemo.designmode.chain



/**
 * 作者　: zgs
 * 时间　: 7/21/21
 * 描述　:
 */
//client
fun main(args: Array<String>) {
    val listInterceptor = arrayListOf<MyInterceptor>(AInterceptor(), BInterceptor())
    val reallChain = ReallChain(listInterceptor, 0)
    var result = reallChain.procced()
    println(result)
}

interface MyInterceptor {
    fun intercept(chain: Chain): String
    interface Chain {
        fun procced():String
    }
}

class ReallChain : MyInterceptor.Chain {
    var mIndex = -1
    var mListInterceptor = arrayListOf<MyInterceptor>()

    constructor(listInterceptor: ArrayList<MyInterceptor>, index: Int) {
        this.mIndex = index
        this.mListInterceptor = listInterceptor
    }

    override fun procced() :String {
        if(mIndex>=mListInterceptor.size){

        }else{
            var next = ReallChain(mListInterceptor, mIndex + 1)
            var interceptor = mListInterceptor[mIndex]
            var response = interceptor.intercept(next)
            return response
        }

        return "-----"
    }

}
class AInterceptor : MyInterceptor{
    override fun intercept(chain: MyInterceptor.Chain): String {
        chain.procced()
        return "A"
    }

}
class BInterceptor : MyInterceptor{
    override fun intercept(chain: MyInterceptor.Chain): String {
        chain.procced()
        return "B"
    }

}