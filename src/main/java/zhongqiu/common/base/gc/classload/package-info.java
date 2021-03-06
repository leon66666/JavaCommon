/**
 * @author Administrator
 * http://www.importnew.com/23742.html
(1)什么是类加载。
类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的方法区内， 然后在堆区创建一个java.lang.Class对象，用来封装类在方法区内的数据结构。
(2)类的生命周期
加载，查找并加载类的二进制数据，在Java堆中也创建一个java.lang.Class类的对象
连接，连接又包含三块内容：验证、准备、解析。
1）验证，文件格式、元数据、字节码、符号引用验证；2）准备，为类的静态变量分配内存，并将其初始化为默认值；3）解析，把类中的符号引用转换为直接引用
初始化，为类的静态变量赋予正确的初始值
使用，new出对象程序中使用
卸载，执行垃圾回收
(3)JVM初始化步骤
假如这个类还没有被加载和连接，则程序先加载并连接该类
假如该类的直接父类还没有被初始化，则先初始化其直接父类
假如类中有初始化语句，则系统依次执行这些初始化语句
(4)类初始化时机：只有当对类的主动使用的时候才会导致类的初始化，类的主动使用包括以下六种：
(a)创建类的实例，也就是new的方式(b)访问某个类或接口的静态变量，或者对该静态变量赋值(c)调用类的静态方法
(d)反射（如Class.forName(“com.shengsiyuan.Test”)）(e)初始化某个类的子类，则其父类也会被初始化
(f)Java虚拟机启动时被标明为启动类的类（Java Test），直接使用java.exe命令来运行某个主类
(5)结束生命周期
执行了System.exit()方法;程序正常执行结束;程序在执行过程中遇到了异常或错误而异常终止;由于操作系统出现错误而导致Java虚拟机进程终止
(6)类加载器
启动类加载器：Bootstrap ClassLoader，负责加载存放在JDK\jre\lib(JDK代表JDK的安装目录).启动类加载器是无法被Java程序直接引用的
扩展类加载器：Extension ClassLoader.开发者可以直接使用扩展类加载器
应用程序类加载器：Application ClassLoader
自定义的类加载器
(7)JVM类加载机制
(a)全盘负责，当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该类加载器负责载入，除非显示使用另外一个类加载器来载入
(b)父类委托，先让父类加载器试图加载该类，只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类
(c)缓存机制，缓存机制将会保证所有加载过的Class都会被缓存，当程序中需要使用某个Class时，类加载器先从缓存区寻找该Class，只有缓存区不存在，
系统才会读取该类对应的二进制数据，并将其转换成Class对象，存入缓存区。这就是为什么修改了Class后，必须重启JVM，程序的修改才会生效
(8)类加载的三种方式
 */
package zhongqiu.common.base.gc.classload;