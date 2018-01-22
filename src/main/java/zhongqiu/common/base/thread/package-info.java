/*
 * @author zhongqiu
 * 并发，线程相关
 * （1）同步Synchronized
 * （2）thread。implements Runnable。
 *             持有对象Runnable target，ThreadLocal.ThreadLocalMap threadLocals,threadStatus
 *             start启动，多次调用会怎样，初始化方式(runable,thread)，runable和thread的区别
 *             线程的五大状态：新建、就绪、运行、阻塞、死亡。
 *             wait和notify的使用；isAlive的使用
 *             Thread.sleep()与Object.wait()区别
 *             经典面试题（三个线程交替打印10次ABC）
 *             多线程异步计算获取计算结果
 *  (3)ThreadLocal。ThreadLocalMap，Entry(ThreadLocal k, Object v)。thread持有ThreadLocalMap对象
 * （4）Volatile关键字。
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