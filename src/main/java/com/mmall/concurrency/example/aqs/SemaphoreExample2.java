package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for(int i = 0;i < threadCount;i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    /**
                     * 总共3个许可，一次性获取3个许可才执行一个线程，其他线程获取不到许可
                     * 只能等待，所以会看到一秒执行一个线程
                     */
                    semaphore.acquire(3);   //获取多个许可
                    test(threadNum);
                    semaphore.release(3);   //释放多个许可
                } catch (InterruptedException e) {
                    log.error("exception:",e);
                }finally {
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}",threadNum);
    }

}
