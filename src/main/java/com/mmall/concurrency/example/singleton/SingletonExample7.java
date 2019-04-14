package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.Recommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式： 单例模式
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    //私有构造方法
    private SingletonExample7(){

    }

    public SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只执行一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }

}
