package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class SynchronizedExample1 {

    /**
     * 修饰代码块
     * 作用代码是大括号内的
     * 作用对象是调用方法的对象
     */
    public void test1(int j){
        synchronized (this){
            for(int i = 0;i<10;i++){
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    /**
     * 修饰一个方法
     * 作用对象是调用方法的对象
     * 子类调用不含有synchronized
     */
    public synchronized void test2(int j){
        synchronized (this){
            for(int i = 0;i<10;i++){
                log.info("test2 {} - {}",j,i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(()->{
            example1.test2(1);
        });

        service.execute(()->{
            example2.test2(2);
        });

    }

}
