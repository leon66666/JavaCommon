package zhongqiu.common.base.thread;

// wait和notify的demo
//如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待这个对象的控制权的状态。
//如果对象调用了notify方法就会通知某个正在等待这个对象的控制权的线程可以继续运行。
//如果对象调用了notifyAll方法就会通知所有等待这个对象控制权的线程继续运行。

//任何一个时刻，对象的控制权（monitor）只能被一个线程拥有。
//无论是执行对象的wait、notify还是notifyAll方法，必须保证当前运行的线程取得了该对象的控制权（monitor）
//如果在没有控制权的线程里执行对象的以上三种方法，就会报java.lang.IllegalMonitorStateException异常。
//JVM基于多线程，默认情况下不能保证运行时线程的时序性

public class WaitNotifyDemo {
	public static void main(String[] args) {

		// wait和notify的Demo
		System.out.println("Main Thread Run!");
		WaitNotifyDemo test = new WaitNotifyDemo();
		NotifyThread notifyThread = test.new NotifyThread("notify01");
		WaitThread waitThread01 = test.new WaitThread("waiter01");
		WaitThread waitThread02 = test.new WaitThread("waiter02");
		WaitThread waitThread03 = test.new WaitThread("waiter03");
		notifyThread.start();
		waitThread01.start();
//		waitThread02.start();
//		waitThread03.start();
	}

	private StringBuilder flag =new StringBuilder("true");

	class NotifyThread extends Thread {
		public NotifyThread(String name) {
			super(name);
		}

		public void run() {
			try {
				sleep(100000);// 推迟3秒钟通知
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (flag) {
				flag.replace(1, flag.length(), "false");
				flag.notifyAll();
			}
		}
	};

	class WaitThread extends Thread {
		public WaitThread(String name) {
			super(name);
		}

		public void run() {
			synchronized (flag) {
				while (flag.toString().equals("true")) {
					System.out.println(getName() + " begin waiting!");
					long waitTime = System.currentTimeMillis();
					try {
						flag.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					waitTime = System.currentTimeMillis() - waitTime;
					System.out.println("wait time :" + waitTime);
				}
				System.out.println(getName() + " end waiting!");
			}
		}
	}
}
