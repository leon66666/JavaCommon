package zhongqiu.common.base;

//内部类
//http://www.cnblogs.com/wangzhongqiu/p/6574019.html
public class InnerClassDemo {
	public static void main(String[] args) {
		// 常规内部类
		// 内部类中的变量访问形式
		Out1.In in = new Out1().new In();
		in.print();

		// 静态内部类
		Out2.In in2 = new Out2.In();

		// 私有内部类
		Out3 out3 = new Out3();
		out3.outPrint();

		// 局部内部类
		Out4 out4 = new Out4();
		out4.Print(5);
		
		// 匿名内部类
		out5 out5=new out5();
		out5.print();
	}
}

// 常规内部类
// 内部类中的变量访问形式
class Out1 {
	private int age = 12;

	// 内部类
	class In {
		private int age = 13;

		public void print() {
			int age = 14;
			System.out.println("局部变量：" + age);
			System.out.println("内部类变量：" + this.age);
			System.out.println("外部类变量：" + Out1.this.age);
		}
	}
}

// 静态内部类
class Out2 {
	private static int age = 12;
	private int normalage = 13;

	static class In {
		public void print() {
			System.out.println(age);
			// 静态内部类不能访问外部类的非静态成员变量
			// System.out.println(normalage);
		}
	}
}

// 私有内部类
class Out3 {
	private int age = 12;

	private class In {
		public void print() {
			System.out.println(age);
		}
	}

	public void outPrint() {
		new In().print();
	}
}

// 局部内部类
class Out4 {
	private int age = 12;

	public void Print(final int x) {
		class In {
			public void inPrint() {
				System.out.println(x);
				System.out.println(age);
			}
		}
		new In().inPrint();
	}
}

// 匿名内部类
class out5 {
	public void print() {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 5; i++) {
					System.out.print(i + " ");
				}
			}
		}).start();
	}
}