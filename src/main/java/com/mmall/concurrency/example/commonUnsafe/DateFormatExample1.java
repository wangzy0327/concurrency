package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class DateFormatExample1 {

    //线程不安全类 多线程下parse出错
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0;i < clientTotal;i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void update(){
        try {
            simpleDateFormat.parse("2019-02-08");
        } catch (ParseException e) {
            log.error("parse Exception",e);
        }
    }
}
