/**
 * 
 */
/**
 * @author wanghzongqiu
 * （1）类加载.详情见classload包
 * （2）JVM内存结构：两个子系统(Class loader子系统和Execution engine(执行引擎))，
 *     两个组件分别是Runtime data area (运行时数据区域)组件和Native interface(本地接口)组件
 *     Runtime Data Area组件：1、Heap (堆)2、Method Area(方法区域)3、Java Stack(java的栈）
 *                          4、Program Counter(程序计数器)5、Native method stack(本地方法栈)：保存native方法进入区域的地址
 *     方法区（Method Area）,与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
 *     年轻代和年老代都是指的JVM的Heap空间，而持久代则是之前提到的Method Area，不属于Heap。
 *     控制参数
		-Xms设置堆的最小空间大小。
		-Xmx设置堆的最大空间大小。
		-XX:NewSize设置新生代最小空间大小。
		-XX:MaxNewSize设置新生代最大空间大小。
		-XX:PermSize设置永久代最小空间大小。
		-XX:MaxPermSize设置永久代最大空间大小。
		-Xss设置每个线程的堆栈大小。
 * （3） GC算法 垃圾回收：对象分为年青代(Young)、年老代(Tenured)、持久代(Perm)，对不同生命周期的对象使用不同的算法.
 *     年老代使用标记-压缩算法或者标记-删除算法，年轻代使用复制算法
                    垃圾收集器的选择：GCCollector
 *     内存泄露：手动将生成的无用对象，中间对象置为null；对象池；弱引用
 * （4）GC分析 命令调优
 *     内存检测工具：jconsole；JProfiler
 */
package zhongqiu.common.base.gc;