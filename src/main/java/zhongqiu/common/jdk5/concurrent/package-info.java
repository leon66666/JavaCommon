/*
 * @author zhongqiu
 * jdk5，并发新特性
 * （0）unsafe.compareAndSwapInt(this, stateOffset, expect, update)。
 *      【说明】CAS原语可以用来实现无锁的数据结构。是CPU指令级的操作，只有一步原子操作
 *      【步骤】有一些状态，创建它的副本，修改它，执行CAS，如果失败，重复尝试
 *      【存在问题】如ABA问题、指令重排序等。
 * （1）ConcurrentHashMap.持有对象：Segment<K,V>[] segments;segmentMask;segmentShift
 *      【Segment extends ReentrantLock】HashEntry<K,V>[] table;modCount;threshold;count;loadFactor
 *      【HashEntry】final hash；final key；value；HashEntry<K,V> next;
 * （2）可重入锁。ReentrantLock implements Lock。成员变量：Sync sync;
 *      【Sync extends AbstractQueuedSynchronizer】
 *      【NonfairSync extends Sync】非公平锁：当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁。
 *      【FairSync extends Sync】公平锁：当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待。
 *      【FairSync】lock方法，if compareAndSetState(0, 1);unsafe.compareAndSwapInt(this, stateOffset, expect, update);
 *                           setExclusiveOwnerThread(Thread.currentThread());else acquire(1);
 * （3）
 * （4）
 * （5）
 * （6）
 * （7）
 * （8）
 *  (9)

 *                     
 */
package zhongqiu.common.jdk5.concurrent;

