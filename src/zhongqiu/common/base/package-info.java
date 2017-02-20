/**
 * 
 */
/**
 * @author zhongqiu
 * 参考资料：http://www.runoob.com/java/java-stack-class.html
 * 实例：  http://www.runoob.com/java/java-examples.html
 * java基础：
 * （1）基本数据类型：值类型和引用类型，string常量池，intern()方法，stringbuilder
 * （2）运算符操作：a++，++a,三元运算符，instanceof运算符
 * （3）异常：错误和异常，异常分为运行时异常和非运行时异常
 * （4）String,StringBuffer,StringBuilder相关:stringbuilder的方法使用
 * （5）Number类型相关：对应基本类型
 * （6）随机数。Math.random():生成随机的六位字符串
 * （7）反射机制 
 * （8）类加载：类加载过程，原理(双亲委托模式)，三种类加载器，类加载的三种方式，自定义classloader
 * （9）JVM内存管理：两个子系统(Class loader子系统和Execution engine(执行引擎))，
 *     两个组件分别是Runtime data area (运行时数据区域)组件和Native interface(本地接口)组件
 *     Runtime Data Area组件：1、Heap (堆)2、Method Area(方法区域)3、Java Stack(java的栈）
 *                          4、Program Counter(程序计数器)5、Native method stack(本地方法栈)：保存native方法进入区域的地址
 *     JVM GC原理：对象分为年青代(Young)、年老代(Tenured)、持久代(Perm)，对不同生命周期的对象使用不同的算法
 *     年轻代和年老代都是指的JVM的Heap空间，而持久代则是之前提到的Method Area，不属于Heap。
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 * （1）集合中的List：arrayList，linkedList,vector的区别和使用场景
 * （2）集合中的Stack：push，pop，peek，search
 * （3）集合中的Array：数组的创建，数组作为参数，作为返回值
 * （4）集合中的Arrays类
 * （5）集合中的Set：hashset,treeset,LinkedHashSet
 * （6）集合中的Map：hashmap,treemap,遍历
 * （7）集合中的Collections类：对集合的各种操作。三种遍历方式，升序排序，降序排序，混排，自定义排序
 * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 * （1）同步Synchronized
 * （2）多线程:1、多线程的三种实现方法；2、如何运行一个多线程执行代码；3、线程的常用方法；4、wait和notify的使用；5、经典面试题（三个线程交替打印10次ABC）
 * （3）线程池 ThreadPoolExecutor
 * （4）Volatile关键字
 * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  * 
 * （1）Date相关
 * （2）正则相关
 * （3）流(Stream)、文件(File)和IO
 * （4）序列化 Serialize
 * （5）网络编程 socket,tcp/ip,http
 * （6）发邮件 send mail
 */
package zhongqiu.common.base;