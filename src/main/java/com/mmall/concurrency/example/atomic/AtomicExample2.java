package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
/**
 *
 * LongAdder减少冲突的方法以及在求和场景下比AtomicLong更高效
 * 原因:
 * 首先和AtomicLong一样，都会先采用cas方式更新值
 * 在初次cas方式失败的情况下(通常证明多个线程同时想更新这个值)，
 * 尝试将这个值分隔成多个cell（sum的时候求和就好），
 * 让这些竞争的线程只管更新自己所属的cell
 * （因为在rehash之前，每个线程中存储的hashcode不会变，所以每次都应该会找到同一个cell），
 * 这样就将竞争压力分散了
 *
 *
 */
public class AtomicExample2 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0;i < clientTotal;i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add(){
        count.increment();
//        count.getAndIncrement();
    }
}
