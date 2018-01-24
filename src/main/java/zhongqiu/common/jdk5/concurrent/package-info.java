/*
 * @author zhongqiu
 * jdk5特性
 * （1）ConcurrentHashMap.持有对象：Segment<K,V>[] segments;segmentMask;segmentShift
 *        【Segment extends ReentrantLock】HashEntry<K,V>[] table;modCount;threshold;count;loadFactor
 *        【HashEntry】final hash；final key；value；HashEntry<K,V> next;
 * （2）ReentrantLock implements Lock.成员变量：Sync sync
 *        【Sync extends AbstractQueuedSynchronizer】
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

