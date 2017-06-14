package zhongqiu.common.base;

//Number类相关
public class NumberDemo {
	/*
	 * 在实际开发过程中，我们经常会遇到需要使用对象，而不是内置数据类型的情形。为了解决这个问题，Java 语言为每一个内置数据类型提供了对应的包装类。
	 * 所有的包装类（Integer、Long、Byte、Double、Float、Short）都是抽象类 Number 的子类。
	 */

	/*
	 * Number方法 compareTo() equals() 等等
	 */
	public static void main(String[] args) {
		Integer aInteger = 1;
		Integer bInteger = 1;
		Integer aInteger2 = new Integer(1);
		Integer bInteger2 = new Integer(1);

		System.out.println(aInteger == bInteger);
		System.out.println(aInteger == aInteger2);
		System.out.println(aInteger2 == bInteger2);
	}
}
