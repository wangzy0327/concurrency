# concurrency
## java并发
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

#### [3. 安全发布对象 ][3]
**[利用单例模式举例安全发布对象][4]**
```bash
在静态初始化函数中初始化一个对象引用
将对象的引用保存到volatile类型域或者AtomicReference对象中
将对象的引用保存到某个正确构造对象的final类型域中
将对象的引用保存到一个由锁保护的域中
```

#### [4. 不可变对象 ][5]
**final关键字：类、方法、变量**
```bash
修饰类：不能被继承
修饰方法：1、锁定方法不能被继承类修改；2、效率
修饰变量：基本数据类型变量、引用类型变量
```
**不可变集合类**
```bash
Collections.unmodifiableXXX:Collection、List、Set、Map...
Guava.ImmutableXXX:Collection、List、Set、Map...
```

#### [5. 线程封闭 ][6]
```bash
Ad-hoc线程封闭：程序控制实现，最糟糕，忽略
堆栈封闭：局部变量，无并发问题（普遍）
ThreadLocal线程封闭：特别好的封闭方法
```

#### [6. 线程不安全类及写法 ][7]
```bash
StringBuilder->StringBuffer
SimpleDateFormat -> JodaTime
ArrayList,HashSet,HashMap等Collections 线程不安全
先检查再执行：if(condition(a)){handle(a);} 线程不安全（并发不能保证原子性）
```

#### [7. 线程安全 - 同步容器 ][8]
```bash
ArrayList -> Vector，Stack
HashMap -> HashTable(key、value不能为null)
Collections.synchronizedXXX(List、Set、Map)
```

#### [8. 线程安全 - 并发容器 J.U.C ][9]
```bash
ArrayList -> CopyOnWriteArrayList
HashSet、TreeSet -> CopyOnWriteArraySet、ConcurrentSkipListSet(key、value不能为null)
HashMap、TreeMap -> ConcurrentHashMap、ConcurrentSkipListMap(List、Set、Map)
```
**安全共享对象策略-总结**
```bash
线程限制：一个被线程限制的对象，由线程独占，并且只能被占有它的线程修改
共享只读：一个共享只读的对象，在没有额外同步的情况下，可以被多个线程并发访问，但是任何线程都不能修改它
线程安全对象：一个线程安全的对象或者容器，在内部通过同步机制来保证线程安全，所以其他线程无需额外的同步就可以通过公共接口随意访问它
被守护对象：被守护对象只能通过获取特定的锁来访问
```
#### [9. J.U.C - (AbstractQueueSynchronizer)AQS ][10]
**[锁：ReentrantLock Synchronized StampedLock ReentrantReadWriteLock Condition][11]<br>**
**[J.U.C 拓展：FutureTask Fork/Join BlockingQueue][12]**
```bash
AQS同步组件：CountDownLatch、Semaphore、CyclicBarrier
```

#### [10. 线程池 - ThreadPoolExecutor ][13]
```bash
参数：
corePoolSize:核心线程数量
maximumPoolSize:线程最大线程数
workQueue：阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生很大影响
keepAliveTime:线程没有任务执行时最多保持多久时间终止
unit:keepAliveTime的时间单位
threadFactory:线程工厂，用来创建线程
rejectHandler:当拒绝处理任务时的策略
方法：
execute():提交任务，交给线程池执行
submit():提交任务，能够返回执行结果 execute+Future
shutdown():关闭线程池，等待任务都执行完
shutdownNow():关闭线程池，不等待任务都执行完
getTaskCount():线程池已执行和未执行的任务总数
getCompletedTaskCount():已完成的任务数量
getPoolSize():线程池当前的线程数量
getActiveCount():当前线程池中正在执行任务的线程数量
```
**线程池 - Executor框架接口**
```bash
Executors.newCachedThreadPool
Executors.newFixedThreadPool
Executors.newScheduledThreadPool
Executors.newSingleThreadExecutor
```
**[死锁][14]**
```bash
 死锁四个必要条件:
 1、互斥条件：一个资源只能被一个进程占用
 2、保持等待：一个进程请求资源而阻塞时，对已获得资源保持不放
 3、不剥夺条件：进程已获得资源，在未使用完之前，不能强行剥夺
 4、环路等待条件：若干进程之间形成一种头尾相接的循环等待资源关系
```


[1]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/atomic
[2]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/sync
[3]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/publish
[4]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/singleton
[5]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/immutable
[6]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/threadLocal
[7]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/commonUnsafe
[8]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/syncContainer
[9]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/concurrent
[10]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/aqs
[11]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/lock
[12]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/aqs
[13]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/threadPool
[14]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/deadLock