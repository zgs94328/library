package com.zgs.zgslibrarydemo.designmode.factory

import com.zgs.zgslibrarydemo.designmode.chain.LeaveInfo
import com.zgs.zgslibrarydemo.designmode.chain.Lisi
import com.zgs.zgslibrarydemo.designmode.chain.ZhangSan

/**
 * 作者　: zgs
 * 时间　: 7/21/21
 * 描述　:
 */
//普通工厂
fun main(args: Array<String>) {
    var shape = CircleFactory()
    shape.getShape().draw()

}

interface Shape {
    fun draw()
}

class Circle : Shape {
    override fun draw() {
        println("画圆形")
    }

}

class Rectangle : Shape {
    override fun draw() {
        println("画长方形")
    }

}
interface ShapeFactory {
    fun getShape():Shape
}
class CircleFactory : ShapeFactory{
    override fun getShape(): Shape {
        return Circle()
    }

}
class RectangleFactory : ShapeFactory{
    override fun getShape(): Shape {
        return Rectangle()
    }

}