# concurrency
#### 1.[原子性 Atomic包][1]
> AtomicXXX：CAS、Unsafe.CompareAndSwapInt 
> AtomicLong、LongAdder 
> AtomicReference、AtomicReferenceFieldUpdater
> AtomicStampReference：CAS的ABA问题 
> AtomicBoolean：CompareAndSet（多线程只执行一次）  

  [1]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/atomic