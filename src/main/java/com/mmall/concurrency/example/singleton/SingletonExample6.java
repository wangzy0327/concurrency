package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 静态块初始化
 */
@ThreadSafe
public class SingletonExample6 {

    //私有构造函数
    private SingletonExample6(){

    }

    //再写静态域和静态代码块时一定要注意顺序，顺序不同，执行结果不同

    //单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }


    //静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }


    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }

}
