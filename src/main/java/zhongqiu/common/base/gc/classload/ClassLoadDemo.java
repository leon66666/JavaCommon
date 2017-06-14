package zhongqiu.common.base.gc.classload;

public class ClassLoadDemo {
	static {
		System.out.println("ClassLoadDemo静态初始化块执行了！");
	}

	public static void main(String[] args) throws ClassNotFoundException {
		// 一、类加载过程：
		// 1、寻找jre目录，寻找jvm.dll，并初始化JVM；
		// 2、产生一个Bootstrap Loader（启动类加载器）；
		// 3、Bootstrap Loader自动加载Extended Loader（标准扩展类加载器），并将其父Loader设为Bootstrap
		// Loader。
		// 4、Bootstrap Loader自动加载AppClass Loader（系统类加载器），并将其父Loader设为Extended
		// Loader。
		// 5、最后由AppClass Loader加载HelloWorld类。
		ClassLoader loader1 = ClassLoadDemo.class.getClassLoader();
		System.out.println(loader1);
		System.out.println(loader1.getParent());
		System.out.println(loader1.getParent().getParent());

		// 二、类加载有三种方式：
		// 1、命令行启动应用时候由JVM初始化加载
		// 2、通过Class.forName()方法动态加载
		// 3、通过ClassLoader.loadClass()方法动态加载
		ClassLoader loader2 = ClassLoadDemo.class.getClassLoader();
		System.out.println(loader2);
		// 使用ClassLoader.loadClass()来加载类，不会执行初始化块
		// loader2.loadClass("zhongqiu.test.Test");
		// 使用Class.forName()来加载类，默认会执行初始化块
		// Class.forName("zhongqiu.test.Test");
		// 使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
		Class.forName("zhongqiu.test.Test", false, loader2);
	}
}