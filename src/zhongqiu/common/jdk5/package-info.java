/*
 * @author zhongqiu
 * jdk5特性
 * 主要特性： 
 * （1）自动装箱与拆箱
 * （2）枚举：枚举的switch，自定义枚举，枚举实现接口
 * （3）静态导入:import static java.lang.System.out;静态导入的是静态方法
 * （4）可变参数（Varargs）: int ... args
 * （5）内省（Introspector）
 * （6）泛型：泛型类；泛型作为参数和可变参数；泛型作为返回值；泛型方法；类型通配符和extend和super用法；
 * （7）增强型for循环For-Each
 * （8）Scanner类。用来获取用户的输入
 * （9）队列：【阻塞队列】blockingqueue（add、put和take，offer和poll，contains，remove，drainTo）
 *          ArrayBlockingQueue,LinkedBlockingQueue,,PriorityBlockingQueue
 *          DelayQueue：重写getDelay和compareTo方法
 *          SynchronousQueue：每个插入操作必须等待另一个线程的对应移除操作 ，反之亦然。同步队列没有任何内部容量，甚至连一个队列的容量都没有。
 * （10）CountDownLatch。同步辅助类（await，countDown，getCount）。
 *      应用场景：假如有Thread1、Thread2、Thread3、Thread4四条线程分别统计C、D、E、F四个盘的大小，所有线程都统计完毕交给Thread5线程去做汇总
 * （11）ConcurrentHashMap。应用场景,实现原理,锁分离技术，volatile，Segment，和hashtable的区别，弱一致迭代器，读不需要锁
 * （12）ReentrantLock。在资源竞争不是很激烈的情况下，Synchronized的性能要优于ReetrantLock，
 *                    但是在资源竞争很激烈的情况下，Synchronized的性能会下降几十倍
 *                    公平锁（new RenentrantLock(boolean fair)），synchronized默认公平锁，ReentrantLock默认不公平锁
 *                    定时锁等候(tryLock)和可中断锁等候. lock 必须在 finally 块中释放
 * （13）ReentrantReadWriteLock。读写锁。和synchronized的区别。实现原理的不同，
 * （14）Atomic。原子性操作。addAndGet和getAndAdd和decrementAndGet() ，compareAndSet(int expect, int update)
 * （15）线程池Executors。newCachedThreadPool()；newFixedThreadPool(3)；newSingleThreadExecutor();newScheduledThreadPool(5)
 *                     
 */
package zhongqiu.common.jdk5;