package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {

    /**
     * 修饰代码块
     * 作用代码是大括号内的
     * 作用对象是类的所有对象
     */
    public static void test1(int j){
        synchronized (SynchronizedExample2.class){
            for(int i = 0;i<10;i++){
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    /**
     * 修饰一个方法
     * 作用对象是类的所有对象
     * 同一个类同一时刻只能有一个线程执行
     * 子类调用不含有synchronized
     */
    public synchronized static void test2(int j){
        synchronized (SynchronizedExample2.class){
            for(int i = 0;i<10;i++){
                log.info("test2 {} - {}",j,i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(()->{
            example1.test1(1);
        });

        service.execute(()->{
            example2.test1(2);
        });

    }

}
