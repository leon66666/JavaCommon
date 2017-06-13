package zhongqiu.common.base.thread;

//Volatile修饰符

//http://www.cnblogs.com/wangzhongqiu/p/6475357.html
/*
 * Volatile 修饰的成员变量在每次被线程访问时，都强制从共享内存中重新读取该成员变量的值。而且，当成员变量发生变化时，会强制线程将变化值回写到共享内存。
 * 这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
 * 一个 volatile 对象引用可能是 null。
*/

public class VolatileDemo {
	/*
	 * 常情况下，在一个线程调用 run() 方法（在 Runnable 开启的线程），在另一个线程调用 stop() 方法。 如果 第一行 中缓冲区的
	 * active 值被使用，那么在 第二行 的 active 值为 false 时循环不会停止。 但是以上代码中我们使用了 volatile 修饰
	 * active，所以该循环会停止。
	 */
	int a = 1;
	int b = 2;

	public void change() {
		a = 3;
		b = a;
	}

	public void print() {
		System.out.println("b=" + b + ";a=" + a);
	}

	public static void test1() {

		for (int i = 0; i < 50; i++) {
			final VolatileDemo test = new VolatileDemo();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					test.change();
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					test.print();
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		test1();
	}
}
