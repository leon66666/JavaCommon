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
	public static class MyRunnable implements Runnable {
		private volatile boolean active;

		public void run() {
			active = true;
			while (active) // 第一行
			{
				// 代码
				System.out.println("active=" + active);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void stop() {
			System.out.println("active=" + false);
			active = false; // 第二行
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MyRunnable myRunnable = new MyRunnable();

		new Thread(new Runnable() {
			public void run() {
				myRunnable.run();
			}
		}).start();

		Thread.sleep(5000);

		new Thread(new Runnable() {
			public void run() {
				myRunnable.stop();
			}
		}).start();
	}
}
