package zhongqiu.common.base;

//运算符操作
public class OperatorDemo {
	public static void main(String[] args) {
		self();
		three();
		instanceOf();
	}

	// a++，a--。自增自减运算符
	// 1、前缀自增自减法(++a,--a): 先进行自增或者自减运算，再进行表达式运算。
	// 2、后缀自增自减法(a++,a--): 先进行表达式运算，再进行自增或者自减运算 实例：
	public static void self() {
		int a = 5;// 定义一个变量；
		int b = 5;
		int x = 2 * ++a;
		int y = 2 * b++;
		System.out.println("自增运算符前缀运算后a=" + a + ",x=" + x);
		System.out.println("自增运算符后缀运算后b=" + b + ",y=" + y);
	}

	// 条件运算符(三元运算符)（?:）
	public static void three() {
		int a, b;
		a = 10;
		b = (a == 1) ? 20 : 30;
		System.out.println("Value of b is : " + b);
		b = (a == 10) ? 20 : 30;
		System.out.println("Value of b is : " + b);
	}

	// instanceof运算符.
	public static void instanceOf() {
		String name = "James";
		boolean result = name instanceof String;
		System.out.println(result);
	}
}
