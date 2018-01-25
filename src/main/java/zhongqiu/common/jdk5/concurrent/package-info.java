/*
 * @author zhongqiu
 * jdk5特性
 * （1）ConcurrentHashMap.持有对象：Segment<K,V>[] segments;segmentMask;segmentShift
 *        【Segment extends ReentrantLock】HashEntry<K,V>[] table;modCount;threshold;count;loadFactor
 *        【HashEntry】final hash；final key；value；HashEntry<K,V> next;
 * （2）ReentrantLock implements Lock.成员变量：Sync sync
 *         非公平锁：当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁。
 *         公平锁：当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待。
 *        【Sync extends AbstractQueuedSynchronizer】【】
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

