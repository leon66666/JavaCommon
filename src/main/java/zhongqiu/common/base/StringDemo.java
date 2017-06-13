package zhongqiu.common.base;

//String相关
public class StringDemo {
	/*
	 * String 类型和 StringBuffer 类型的主要性能区别其实在于 String 是不可变的对象, 因此在每次对 String
	 * 类型进行改变的时候其实都等同于生成了一个新的 String 对象，然后将指针指向新的 String 对象，所以经常改变内容的字符串最好不要用
	 * String ，因为每次生成对象都会对系统性能产生影响，特别当内存中无引用对象多了以后， JVM 的 GC 就会开始工作，那速度是一定会相当慢的。
	 */

	// StringBuffer 字符串变量（线程安全）
	// StringBuilder 字符串变量（非线程安全）
	// 大部分情况下 StringBuilder>StringBuffer>String

	public static void main(String[] args) {
		stringBuilderDemo();
	}

	// StringBuilder
	public static void stringBuilderDemo() {
		// 创建实例化对象
		StringBuilder sb = new StringBuilder("Hello World!");

		// apend用法
		sb.append("aaaa");
		System.out.println(sb);

		// insert用法.在序号4的前面插入
		sb.insert(4, "23456");
		System.out.println(sb);

		// StringBuffer delete(start,end):删除缓冲区中的数据，包含Start,不包含end
		// StringBuffer deleteCharAt(index)；删除缓冲区指定位置的数据
		sb.delete(1, 3);
		System.out.println(sb.toString());
		sb.deleteCharAt(0);
		System.out.println(sb.toString());
		// sb.delete(0,sb.length()):清空缓冲区

		// 获取
		char c = sb.charAt(2);
		System.out.println(c);
		int index = sb.indexOf("a");
		System.out.println(index);

		// 修改，也可以理解为替换
		sb.replace(1, 3, "java");
		System.out.println(sb.toString());
		sb.setCharAt(2, 'm');
		System.out.println(sb.toString());
	}

}
