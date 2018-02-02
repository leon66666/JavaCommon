/*
 * @author zhongqiu
 * jdk5，并发新特性。JUC  http://ifeve.com/doug-lea/
 * （2）ConcurrentHashMap.持有对象：Segment<K,V>[] segments;segmentMask;segmentShift
 *      【Segment extends ReentrantLock】HashEntry<K,V>[] table;modCount;threshold;count;loadFactor
 *      【HashEntry】final hash；final key；value；HashEntry<K,V> next;
 * （4）
 * （5）
 * （6）
 * （7）
 * （8）
 *  (9)

 *                     
 */
package zhongqiu.common.jdk5.concurrent;

