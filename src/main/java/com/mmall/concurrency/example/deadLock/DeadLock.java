package com.mmall.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个简单的死锁类
 * 当DeadLock类的对象flag==1时（td1），先锁定o1，睡眠500毫秒
 * 而td1在睡眠的时候另一个flag==0的对象（td2）线程启动，先锁定o2，睡眠500毫秒
 * td1睡眠结束后需要锁定o2才能继续执行，而此时o2已经被td2锁定；
 * td2睡眠结束后需要锁定o1才能继续执行，而此时o1已经被td1锁定；
 * td1、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */


/**
 * 死锁四个必要条件:
 * 1、互斥条件：一个资源只能被一个进程占用
 * 2、保持等待：一个进程请求资源而阻塞时，对已获得资源保持不放
 * 3、不剥夺条件：进程已获得资源，在未使用完之前，不能强行剥夺
 * 4、环路等待条件：若干进程之间形成一种头尾相接的循环等待资源关系
 *
 *
 */

@Slf4j
public class DeadLock implements Runnable{

    public int flag = 1;

    //静态对象时类的所有对象共享的
    private static Object o1 = new Object(),o2 = new Object();


    @Override
    public void run() {
        log.info("flag:{}",flag);

        if(flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    log.error("exception",e);
                }
                synchronized (o2){
                    log.info("1");
                }
            }
        }
        if(flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    log.error("exception",e);
                }
                synchronized (o1){
                    log.info("0");
                }
            }
        }

    }

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag = 1;
        td2.flag = 0;

        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。
        //td2的run()可能在td1的run()之前运行
        new Thread(td1).start();
        new Thread(td2).start();
    }
}
