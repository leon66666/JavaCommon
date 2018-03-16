/*
 * @author zhongqiu
 * 参考资料：http://blog.csdn.net/fyang2007/article/details/51517662
 * （1）集合中的List：arrayList，linkedList,vector的区别和使用场景。linkedlist双向循环链表，vector同步实现原理syn锁
       【ArrayList implements List,RandomAccess】【List extends Collection】【Collection extends Iterable】
       【RandomAccess接口，是一种标记接口，当要实现某些算法时，会判断当前类是否实现了RandomAccess接口会根据结果选择不同的算法】
       【集合类是RandomAccess的实现，则尽量用for(int i = 0; i < size; i++) 来遍历而不要用Iterator迭代器来遍历】
       【核心变量】Object[] elementData【存放数据】，size【已经存放的数据数量】，modCount【用于快速失败】
       【核心方法】 Arrays.copyOf，System.arraycopy(Object src,  int  srcPos,Object dest, int destPos, int length)
                  add：modCount++。grow方法扩容,Arrays.copyOf
                  addAll,ensureCapacityInternal(size + numNew)
                  addAll(int index, Collection<? extends E> c),
                  remove(int index),remove(Object o),fastRemove(index)，System.arraycopy,clear
                  iterator,class Itr implements Iterator,cursor,lastRet,expectedModCount,
                           next(),remove(int index),checkForComodification()
       【LinkedList implements List,Deque】【Deque extends Queue】【Queue extends Collection】
        size,first,last,modCount,
       【list核心方法】toArray,linkFirst(E e)，linkLast(E e)，linkBefore(E e, Node<E> succ)
                      addAll(int index, Collection<? extends E> c),node(int index)
       【Deque核心方法】indexOf(Object o)，lastIndexOf(Object o),remove(Object o)，unlink(Node<E> x)
       【iterator】内部类class ListItr implements ListIterator<E>，lastReturned,next,nextIndex,expectedModCount
                   next,previous,remove,set,add
       【Vector implements List】【elements方法内创建匿名类，返回Enumeration对象用来遍历集合】
       【Collections 提供了很多对list的操作：排序，混排，替换，复制，最大最小值，移动list中元素位置】
 * （2）集合中的Stack：push，pop，peek，search。【Stack extends Vector】
 * （3）集合中的Array：数组的创建，数组作为参数，作为返回值
 * （4）集合中的Arrays类，对数组的各种操作。binarySearch，sort，fill，equals，asList，tostring，hashcode，copyof
 *  (5) Collections.synchronizedList。装饰器模式。遍历器遍历的时候，需要手动加锁
 * （6）集合中的Set：hashset,LinkedHashSet，treeset，二叉树排序，自定义排序
 * （7）集合中的Map：hashmap,treemap
       【应用】统计字符串中每个字符出现的次数。统计字符串中的大写，小写，数字，其他字符个数。统计字符串中子字符串出现的次数
       【内部实现】数组+链表；hash算法 ；初始容量(桶)和加载因子；rehash操作(扩充容量2倍)；链地址法解决冲突；红黑树，put和delete
       【HashMap】【HashMap implements Map】
       【核心方法】:Entry<K,V>[] table，threshold,modCount，loadFactor，size,hashSeed
                   put，inflateTable,roundUpToPowerOf2，initHashSeedAsNeeded,
                        putForNullKey,遍历table[0]是否已存在null的key,不存在addEntry,
                        hash,indexFor，遍历table[i]是否已存在key，不存在addEntry,
                        addEntry(int hash, K key, V value, int bucketIndex)，
                        resize(2 * table.length)，transfer，
                        hash，indexFor，createEntry(hash, key, value, bucketIndex);
                   get,getForNullKey,getEntry，
                   containsKey,getEntry,
                   remove(Object key)，removeEntryForKey，hash,indexFor,遍历table[i]，
                   clear，Arrays.fill(table, null);
                   entrySet，keySet，values,abstract class HashIterator implements Iterator,
                   Entry<K,V> next;Entry<K,V> current;int index;expectedModCount,
       【TreeMap】【TreeMap implements NavigableMap】【NavigableMap extends SortedMap】【SortedMap extends Map】
       【Hashtable】【Hashtable extends Dictionary implements Map】【synchronized内置锁保证线程安全】
                    【Hashtable和hashmap的差别：线程安全，key和value不能为null，获取index实现不一样】
 */
package zhongqiu.common.base.collections;