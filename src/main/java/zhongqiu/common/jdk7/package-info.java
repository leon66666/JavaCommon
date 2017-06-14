/**
 *
 */
/**
 * @author zhongqiu
 * 参考资料：http://www.cnblogs.com/nayitian/p/3406749.html
 * jdk1.7特性
 * （1）switch中可以使用字串了
 * （2）类型自动推断
 * （3）数值可加下划线
 * （4）支持二进制文字
 * （5）【传输队列】transferqueue（tryTransfer，transfer，tryTransfer(E e, long timeout, TimeUnit unit)）
 * （6）ForkJoinTask
 * （5）在try catch异常扑捉中，一个catch可以写多个异常类型，用"|"隔开
 * （6）自动资源管理。
 *     jdk7之前，你必须用try{}finally{}在try内使用资源，在finally中关闭资源，不管try中的代码是否正常退出或者异常退出。
 *     jdk7之后，你可以不必要写finally语句来关闭资源，只要你在try()的括号内部定义要使用的资源。
 * （7）可变参数非具体化时提示警告
 * （8）新增一些取环境信息的工具方法
 */
package zhongqiu.common.jdk7;