/*
 * @author zhongqiu
 * jdk5，并发新特性。JUC  http://ifeve.com/doug-lea/
 * （1）locks：LockSupport，ReentrantLock，ReentrantReadWriteLock
 * （2）atomic：原子更新基本类型(AtomicBoolean,AtomicInteger,AtomicLong)、
 *             原子更新数组(AtomicIntegerArray,AtomicLongArray,AtomicReferenceArray)、
 *             原子更新引用(AtomicReference)、
 *             原子更新属性(AtomicIntegerFieldUpdater,AtomicLongFieldUpdater,AtomicReferenceFieldUpdater)
 *                 AtomicReferenceFieldUpdater的使用有以下限制：
 *                 字段必须是volatile类型的，在线程之间共享变量时保证立即可见
 *                 只能是实例变量，不能是类变量，也就是说不能加static关键字
 *                 只能是可修改变量，不能使final变量，因为final的语义就是不可修改。
 *                 对于AtomicIntegerFieldUpdater和AtomicLongFieldUpdater只能修改int/long类型的字段，不能修改其包装类型（Integer/Long）。
 *                 如果要修改包装类型就需要使用AtomicReferenceFieldUpdater。
 *             解决ABA问题(AtomicMarkableReference,AtomicStampedReference)
 * （3）ConcurrentHashMap.持有对象：Segment<K,V>[] segments;segmentMask;segmentShift
 *      【Segment extends ReentrantLock】HashEntry<K,V>[] table;modCount;threshold;count;loadFactor
 *      【HashEntry】final hash；final key；value；HashEntry<K,V> next;
 * （7）
 * （8）
 *  (9)

 *                     
 */
package zhongqiu.common.jdk5.concurrent;

