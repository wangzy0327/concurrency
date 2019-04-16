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
#### [8. J.U.C - (AbstractQueueSynchronizer)AQS ][10]
```bash
AQS同步组件：CountDownLatch、Semaphore、CyclicBarrier
锁：ReentrantLock Synchronized StampedLock ReentrantReadWriteLock
Condition
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
[9]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/lock