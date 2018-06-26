1:最佳实践

a：使用本地变量
b：使用不可变变量
c：最小化锁的作用域范围：  s = 1/(1 - a + a / n);
d:使用线程池executor，
e：宁可使用同步也不使用  wait() and notify()
f:使用 blockingQueue  实现生产--消费模式
g：使用并发集合 而不是使用锁的同步集合
h:宁可使用semaphore 创建有界的访问
i:宁可使用同步代码块，而不使用同步的方法（synchronize  关键字）
o：避免使用静态变量



