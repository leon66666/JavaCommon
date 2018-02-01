/*
 * @author zhongqiu
 * 并发，线程相关
 * （1）同步Synchronized。非公平锁，每个线程都先要竞争锁，不管排队先后。
 *         等待锁的实现方式：自旋锁，挂起等待，混合锁
 * （2）thread。implements Runnable。持有对象Runnable target，ThreadLocal.ThreadLocalMap threadLocals,threadStatus
 *        初始化方式(runable,thread)，runable和thread的区别
 *        线程的状态：New、Runnable(包括操作系统的Ready和Running)、Blocked、Waiting、Timed waiting、Terminated
 *        new Thread(),变为New状态
 *        start()  ,变为Runnable状态，创建新的线程在栈空间中开辟新的空间.if (threadStatus != 0) 抛出异常
 *        run()    ,不创建新的线程，直接在当前线程执行 thread的run方法
 *        field()  ,使线程变成ready状态，从新和其他ready状态的线程参与cpu的竞争
 *        isAlive(),如果线程已经启动且尚未终止，则为活动状态。介于Runnable和Terminated中间，都属于活动状态
 *        join()   ,B线程执行A.join(). B获取A的锁; 循环判断a.isAlive(),是则调用a.wait(0)，进入Waiting状态
 *        sleep()  ,当前线程进入TIMED_WAITING状态，不释放监视器锁
 *        interrupt(),不会中断一个正在运行的线程。如果线程被Object.wait, Thread.join和Thread.sleep三种方法之一阻塞，
 *                    它将接收到一个中断信号，提早地终结被阻塞状态，变成Runnable状态，抛出异常InterruptedException，继续往下执行
 *                    如果线程没有被阻塞，这时调用interrupt()将不起作用;
 *        多线程异步计算获取计算结果
 *  (2)  Object
 *         wait()     ,使持有该对象的线程把该对象的控制权交出去，然后处于等待这个对象的控制权的状态。当前线程进入Waiting或者Timed waiting
 *         notify()   ,通知某个正在等待这个对象的控制权的线程可以继续运行。
 *         notifyall(),通知所有等待这个对象控制权的线程继续运行。
 *         经典面试题（三个线程交替打印10次ABC）
 *  (3)ThreadLocal。ThreadLocalMap，Entry(ThreadLocal k, Object v)。thread持有ThreadLocalMap对象
 *     get: Thread t = Thread.currentThread();ThreadLocalMap map = getMap(t);
 *          ThreadLocalMap.Entry e = map.getEntry(this); return (T)e.value;
 *  (4)SimpleDateFormat。SimpleDateFormat继承了DateFormat,在DateFormat中定义了一个protected属性的 Calendar类的对象：calendar。
 *     calendar是一个有状态的变量，format方法中执行了calendar.setTime(date)这条语句改变了calendar，
 *     稍后，calendar还会用到（在subFormat方法里）
 *     解决办法：需要的时候创建新实例，使用同步锁synchronized(sdf)，threadlocal
 * （5）Volatile关键字。
 *     缓存一致性：内存，cpu高速缓存，总线锁机制，缓存一致性协议（Intel 的MESI协议），共享变量。
 *     内存模型：次序规则，锁定规则，volatile变量规则，线程启动规则，线程终止规则，对象终结规则
 *     并发编程的三个概念：原子性问题，可见性问题，有序性问题
 *     Java内存模型：1、在java内存模型中，也会存在缓存一致性问题和指令重排序的问题。
 *                2、所有的变量都是存在主存当中（类似于前面说的物理内存），每个线程都有自己的工作内存（类似于前面的高速缓存）。
 *                  线程对变量的所有操作都必须在工作内存中进行，而不能直接对主存进行操作。并且每个线程不能访问其他线程的工作内存。
 *                3、原子性：java中，对基本数据类型的变量的读取和赋值操作是原子性操作 。通过synchronized和Lock实现更大范围的原子性
 *                4、可见性：通过volatile实现
 *                5、有序性：happens-before原则（先行发生原则）
 *     Volatile说明：volatile关键字能禁止指令重排序，所以volatile能在一定程度上保证有序性。
 *                 volatile保证原子性吗？不能！！！
 *
 *
 */
package zhongqiu.common.base.thread;