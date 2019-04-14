# concurrency
#### [1. 原子性 Atomic包][1]
```bash
AtomicXXX：CAS、Unsafe.CompareAndSwapInt
AtomicLong、LongAdder
AtomicReference、AtomicReferenceFieldUpdater
AtomicStampReference：CAS的ABA问题 
AtomicBoolean：CompareAndSet（多线程只执行一次）  
```

#### [2. 线程安全性 ][2]
```bash
原子性：Atomic包、CAS算法、synchronized、Lock
可见性：synchronized、volatile
有序性：happens-before原则 
```


[1]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/atomic
[2]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/sync