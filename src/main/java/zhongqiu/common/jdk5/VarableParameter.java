package zhongqiu.common.jdk5;

//可变参数
/*可变参数的特点：
1、只能出现在参数列表的最后；
2、...位于变量类型和变量名之间，前后有无空格都可以；
3、调用可变参数的方法时，编译器为该可变参数隐含创建一个数组，在方法体中一数组的形式访问可变参数。*/
public class VarableParameter {
	public static void main(String[] args) {
		System.out.println(add(2));
		System.out.println(add(2, 3));
		System.out.println(add(2, 3, 5));
	}

	public static int add(int x, int... args) {
		int sum = x;
		/*
		 * 【for循环方式】 for(int i=0;i<args.length;i++){ sum += args[i]; }
		 */

		// foreach循环方式
		for (int arg : args) {
			sum += arg;
		}
		return sum;
	}
}
