package com.zgs.zgslibrarydemo.designmode.build

import com.zgs.zgslibrarydemo.designmode.factory.CircleFactory

/**
 * 作者　: zgs
 * 时间　: 7/23/21
 * 描述　:
 */
//普通工厂
fun main(args: Array<String>) {
    var shape = CircleFactory()
    shape.getShape().draw()
}
//
//class User( age:Int,name:String,sex: String) {
//    val age: Int = 0
//    val name: String? = null
//    val sex: String = "男"
//
//    class  Builder{
//        var age: Int = 0
//        var name: String? = null
//        var sex: String = "男"
//
//        fun age(age:Int){
//            this.age = age
//        }
//        fun name(name:String){
//            this.name = name
//        }
//        fun sex(sex:String){
//            this.sex = sex
//        }
//        fun builder(){
//            User(Builder())
//        }
//    }
//}