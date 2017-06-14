package zhongqiu.common.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//反射就是在运行状态把 Java  类中的各种成分映射成相应相应的 Java  类，可以动态得获取所有的属性以及动态调用任意一个方法。
//1).一段java代码在程序的运行期间会经历三个阶段：source-->class-->runtime
//2).Class对象
//       在java中用一个Class对象来表示一个java类的class阶段
//       Class对象封装了一个java类定义的成员变量、成员方法、构造方法、包名、类名等。
public class Reflection {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		// 通过类名来构造一个类的实例
		Class cls_str = Class.forName("java.lang.String");
		// 上面这句很眼熟，因为使用过 JDBC 访问数据库的人都用过 J
		Object str = cls_str.newInstance();
		// 相当于 String str = new String();

		// 通过方法名来调用一个方法
		String methodName = "length";
		Method m = cls_str.getMethod(methodName, null);
		System.out.println("length is " + m.invoke(str, null));
		// 相当于 System.out.println(str.length());
	}
}
