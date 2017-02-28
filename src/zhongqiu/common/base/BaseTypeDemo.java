package zhongqiu.common.base;

//基本数据类型
//http://www.runoob.com/java/java-basic-datatypes.html
/*
第一类：整型 byte short int long 
第二类：浮点型 float double
第三类：逻辑型 boolean(它只有两个值可取true false)
第四类：字符型 char

在栈中可以直接分配内存的数据是基本数据类型。
引用数据类型：是数据的引用在栈中，但是他的对象在堆中。

字节：
boolean 布尔型              1/8 
byte 字节类型                1
char 字符型                  2  一个字符能存储一个中文汉字
short 短整型                 2
int 整数类型                 4
long 长整形                  8
float 浮点类型（单精度）     4
double 双精度类型（双精度）  8
一个字节等于8位，一个字节等于256个数，就是-128到127一共256
*/

/*常量池概念
java中基本类型的包装类的大部分都实现了常量池技术，这些类是 Byte,Short,Integer,Long,Character,Boolean,
另外两种浮点数类型的包装类则没有实现。另外 Byte,Short,Integer,Long,Character这5种整型的包装类也只是在对应值>= -128 &&<= 127时才可使用对象池，
也即对象不负责创建和管理大于127的这些类的对象*/
public class BaseTypeDemo {
	public static void main(String[] args) {
		// ValueTypeDemo.test();
		ReferenceTypeDemo.test();
		// CharDemo.test();
		// StringDemo.test();
		// StringDemo.test1();
		// StringDemo.test2();
	}

	// 值类型Demo
	public static class ValueTypeDemo {
		public static void test() {
			int i = 1;
			process(i);
			System.out.println(i);
			process(i, 2);
			System.out.println(i);
			StringBuffer str = new StringBuffer("a");
			process(str);
			System.out.println(str);
			// 结果： 1 1 ab
		}

		public static void process(int i) {
			i = 2;
		}

		public static void process(int i, int j) {
			i = i + j;
		}

		public static void process(StringBuffer str) {
			str.append("b");
		}
	}

	// 基本类型char声明、比较
	public static class CharDemo {
		public static void test() {
			char a = 'a';
			char b = 'b';
			char c = 'a';
			System.out.println(a == b);
			System.out.println(a == c);

			// 结果：false true
		}
	}

	// 引用类型String声明，比较
	public static class StringDemo {
		public static void test() {
			String aString = "ab";
			System.out.println(aString == "ab");
			System.out.println("ab" == "ab");
			System.out.println("ab" == new String("ab"));
			System.out.println("ab" == String.valueOf("ab"));

			// 而”kv”和”ill”也都是字符
			// 串常量，当一个字符串由多个字符串常量连接而成时，它自己肯定也是字符串常量，所以s7也同样在编译期就被解析为一个字符串常量，所以s7也是常量池中
			// ”kvill”的一个引用。
			String s6 = "kvill";
			String s7 = "kv" + "ill";
			System.out.println(s6 == s7);
			System.out.println(new String("ab").equals(new String("ab")));
		}

		public static void test1() {
			// 存在于.class文件中的常量池，在运行期被JVM装载，并且可以扩充。String的intern()方法就是扩充常量池的一个
			// 方法；当一个String实例str调用intern()方法时，Java查找常量池中是否有相同Unicode的字符串常量，如果有，则返回其的引用，
			// 如果没有，则在常量池中增加一个Unicode等于str的字符串并返回它的引用
			String s0 = "kvill"; // 声明了一个常量，在常量池中增加字符串kvill
			String s1 = new String("kvill");// new 方法不会在常量池中增加字符串
			String s2 = new String("kvill");
			System.out.println(s0 == s1);
			System.out.println("**********");
			s1.intern();
			s2 = s2.intern(); // 把常量池中“kvill”的引用赋给s2
			System.out.println(s0 == s1);
			System.out.println(s0 == s1.intern());
			System.out.println(s0 == s2);
			// 结果为：
			//
			// false
			// **********
			// false //虽然执行了s1.intern(),但它的返回值没有赋给s1
			// true //说明s1.intern()返回的是常量池中”kvill”的引用
			// true
		}

		public static void test2() {
			String s1 = new String("kvill");
			String s2 = s1.intern();
			System.out.println(s1 == s1.intern());
			System.out.println(s1 + " " + s2);
			System.out.println(s2 == s1.intern());
			// 结果：
			//
			// false
			// kvill kvill
			// true
			// 在这个类中我们没有声名一个”kvill”常量，所以常量池中一开始是没有”kvill”的，当我们调用s1.intern()后就在常量池中新添加
			// 了一个”kvill”常量，原来的不在常量池中的”kvill”仍然存在，也就不是“将自己的地址注册到常量池中”了。
			//
			// s1==s1.intern()为false说明原来的“kvill”仍然存在；
			//
			// s2现在为常量池中“kvill”的地址，所以有s2==s1.intern()为true。
		}
	}

	// 引用类型Demo
	// 出去基本类型，其他的都是引用类型
	public static class ReferenceTypeDemo {
		/*
		 * 方法中的参数时局部变量来的，拿最后一个来说吧， 方法public static void process(String str,
		 * StringBuffer sb) 中参数str和sb都是局部变量，
		 * 调用process(str,sb)，是让局部变量跟传入的参数指向同一个对象，str = new String("A");
		 * 只是将局部变量str指向另一个对象引用，跟外部的str没有关系，所以str仍为a,而sb.append("A");
		 * 是将局部变量sb指向的对象追加"A"，而由于局部变量指向的对象跟外部的sb指向同一个对象，所以外部的sb也就变成aA了。
		 */
		public static void test() {
			String str = "a";
			StringBuffer sb = new StringBuffer("a");
			process(str);
			System.out.println(str);
			process(sb);
			System.out.println(sb);
			process(str, sb);
			System.out.println(str);
			System.out.println(sb);
		}

		public static void process(String str) {
			str = "A";
		}

		public static void process(StringBuffer sb) {
			sb = new StringBuffer();
			sb.append("A");
		}

		public static void process(String str, StringBuffer sb) {
			str = new String("A");
			sb.append("A");
		}
	}

}
