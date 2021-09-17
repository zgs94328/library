package com.zgs.zgslibrarydemo.designmode.singleton;

/**
 * 作者　: zgs
 * 时间　: 7/21/21
 * 描述　:
 */
//饿汉
//public class SingletonDemo {
//    public static SingletonDemo singletonDemo = new SingletonDemo();
//    public static SingletonDemo getInstance(){
//        return singletonDemo;
//    }
//}
//懒汉
//public class SingletonDemo {
//    public static SingletonDemo singletonDemo = null;
//    public static SingletonDemo getInstance(){
//        if(singletonDemo==null){
//            singletonDemo = new SingletonDemo();
//        }
//        return singletonDemo;
//    }
//}
 //双重锁
public class SingletonDemo {
    public static SingletonDemo singletonDemo = null;
    public static SingletonDemo getInstance(){
        if(singletonDemo==null){
            synchronized (SingletonDemo.class){
                if (singletonDemo==null){
                    singletonDemo = new SingletonDemo();
                }
            }

        }
        return singletonDemo;
    }
}