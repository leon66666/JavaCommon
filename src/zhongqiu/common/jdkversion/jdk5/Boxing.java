package zhongqiu.common.jdkversion.jdk5;

public class Boxing {
	public static void main(String[] args) {
		integerBoxing();
	}

	public static void autoBoxing() {
		/*
		 * 一般我们要创建一个类的对象实例的时候，我们会这样：
		 * 
		 * Class a = new Class(parameter);
		 * 
		 * 当我们创建一个Integer对象时，却可以这样：
		 * 
		 * Integer i = 100; (注意：不是 int i = 100; )
		 * 
		 * 实际上，执行上面那句代码的时候，系统为我们执行了：Integer i = Integer.valueOf(100);
		 * 
		 * 此即基本数据类型的自动装箱功能。
		 */
		Integer i = 100;
	}

	public static void unBoxing() {
		// 自动拆箱(unboxing)，也就是将对象中的基本数据从对象中自动取出。如下可实现自动拆箱：
		Integer i = 10; // 装箱
		int t = i; // 拆箱，实际上执行了 int t = i.intValue();
	}

	public static void integerBoxing() {
		// integer源码
		// public static Integer valueOf(int i) {
		// if(i >= -128 && i <= IntegerCache.high) // 没有设置的话，IngegerCache.high
		// 默认是127
		// return IntegerCache.cache[i + 128];
		// else
		// return new Integer(i);
		// }

		// 在-128~127 之外的数
		Integer i1 = 200;
		Integer i2 = 200;
		System.out.println("i1==i2: " + (i1 == i2));// 显示false
		// 在-128~127 之内的数
		Integer i3 = 100;
		Integer i4 = 100;
		System.out.println("i3==i4: " + (i3 == i4));// 显示true

		Integer i5 = new Integer(100);
		Integer i6 = new Integer(100);
		System.out.println("i5==i6: " + (i5 == i6));// 显示false
	}
}
