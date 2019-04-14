package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicIntegerFieldUpdater<AtomicExample4> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample4.class,"count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample4 example4 = new AtomicExample4();

        if(updater.compareAndSet(example4,100,120)){
            log.info("update success 1,{}",example4.getCount());
        }

        if(updater.compareAndSet(example4,100,120)){
            log.info("update success 2,{}",example4.getCount());
        }else{
            log.info("update failed,{}",example4.getCount());
        }

    }

}
