1:  reentrantlock独有的功能
--> 可指定是公平锁还是非公平锁
--> 提供了一个condition类，可分组唤醒需要唤醒的线程
--> 提供了中断机制，lock.lockinterruptibly();

2:
Lock接口的 线程请求锁的 几个方法：
lock(), 拿不到lock就不罢休，不然线程就一直block。 比较无赖的做法。
tryLock()，马上返回，拿到lock就返回true，不然返回false。 比较潇洒的做法。
带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false。比较聪明的做法。

下面的lockInterruptibly()就稍微难理解一些。
先说说线程的打扰机制，每个线程都有一个 打扰 标志。这里分两种情况，
1. 线程在sleep或wait,join， 此时如果别的进程调用此进程的 interrupt（）方法，此线程会被唤醒并被要求处理
InterruptedException；(thread在做IO操作时也可能有类似行为，见java thread api)
2. 此线程在运行中， 则不会收到提醒。但是 此线程的 “打扰标志”会被设置， 可以通过isInterrupted()查看并 作出处理。

lockInterruptibly()和上面的第一种情况是一样的， 线程在请求lock并被阻塞时，如果被interrupt，则“此线程会被唤醒
并被要求处理InterruptedException”。

3: