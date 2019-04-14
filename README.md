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
Collections.unmodifiableXXX:Colllection、List、Set、Map...
Guava:ImmutableXXX:Collection、List、Set、Map...
```

#### [5. 线程封闭 ][6]
```bash
Ad-hoc线程封闭：程序控制实现，最糟糕，忽略
堆栈封闭：局部变量，无并发问题（普遍）
ThreadLocal线程封闭：特别好的封闭方法
```


[1]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/atomic
[2]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/sync
[3]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/publish
[4]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/singleton
[5]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/immutable
[6]:https://github.com/wangzy0327/concurrency/tree/master/src/main/java/com/mmall/concurrency/example/threadLocal