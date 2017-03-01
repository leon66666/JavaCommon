/**
 * 
 */
/**
 * @author zhongqiu
 * 参考资料：http://www.runoob.com/java/java-stack-class.html
 * 实例：  http://www.runoob.com/java/java-examples.html
 * java基础：
 * （1）基本数据类型：8个基本类型；值类型和引用类型，常量池，intern()方法，stringbuilder
 * （2）运算符操作：a++，++a,三元运算符，instanceof运算符，位运算符
 * （3）异常：错误和异常，异常分为运行时异常和非运行时异常。Throwable，Error，Exception，RuntimeException
 * （4）String,StringBuffer,StringBuilder相关:stringbuilder的方法使用
 * （5）Number类型相关：对应基本类型的封装类。是否实现了常量池技术，哪些实现了，哪些没有实现，有哪些限制
 * （6）随机数。Math.random():生成随机的六位字符串
 * （7）反射机制 
 * （7）关键字：final，volatile，native，instance of，static，transient  
 * （8）类加载：类加载过程，原理(双亲委托模式)，三种类加载器，类加载的三种方式，自定义classloader
 * （9）JVM内存管理：两个子系统(Class loader子系统和Execution engine(执行引擎))，
 *     两个组件分别是Runtime data area (运行时数据区域)组件和Native interface(本地接口)组件
 *     Runtime Data Area组件：1、Heap (堆)2、Method Area(方法区域)3、Java Stack(java的栈）
 *                          4、Program Counter(程序计数器)5、Native method stack(本地方法栈)：保存native方法进入区域的地址
 *     JVM GC原理：对象分为年青代(Young)、年老代(Tenured)、持久代(Perm)，对不同生命周期的对象使用不同的算法
 *     年轻代和年老代都是指的JVM的Heap空间，而持久代则是之前提到的Method Area，不属于Heap。
 * （10）object类相关方法。equals四个特性：自反，对称，一致，传递；hashcode：三个协定，需要重写的情景；getclass，toString；notify；wait
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 * （1）集合中的List：arrayList，linkedList,vector的区别和使用场景。linkedlist双向循环链表，vector同步实现原理syn锁
 * （2）集合中的Stack：push，pop，peek，search。继承自vector
 * （3）集合中的Array：数组的创建，数组作为参数，作为返回值
 * （4）集合中的Arrays类，对数组的各种操作。binarySearch，sort，fill，equals，asList，tostring，hashcode，copyof
 * （5）集合中的Set：hashset,LinkedHashSet，treeset，二叉树排序，自定义排序
 * （6）集合中的Map：hashmap,treemap,遍历。数组+链表；hash算法 ；初始容量(桶)和加载因子；rehash操作(扩充容量2倍)；链地址法解决冲突；红黑树，put和delete
 * （7）集合中的Collections类：对集合的各种操作。三种遍历方式，升序排序，降序排序，混排，自定义排序
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 * （1）多线程 
 * （2）Nio
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 * （1）Date相关
 * （2）正则相关
 * （3）流(Stream)、文件(File)和IO
 * （4）序列化 Serialize
 * （5）网络编程 socket,tcp/ip,http
 * （6）发邮件 send mail
 */
package zhongqiu.common.base;