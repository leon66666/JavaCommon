package zhongqiu.common.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//多线程    http://www.mamicode.com/info-detail-517008.html
//     http://www.runoob.com/java/java-multithreading.html

//在java中，每次程序运行至少启动2个线程。一个是main线程，一个是垃圾收集线程。
//因为每当使用java命令执行一个类的时候，实际上都会启动一个ＪＶＭ，每一个ｊＶＭ就是在操作系统中启动了一个进程。
//线程类的一些常用方法： 
//sleep(): 强迫一个线程睡眠Ｎ毫秒。 
//isAlive(): 判断一个线程是否存活。 
//join(): 等待线程终止。 
//activeCount(): 程序中活跃的线程数。 
//enumerate(): 枚举程序中的线程。 
//currentThread(): 得到当前线程。 
//isDaemon(): 一个线程是否为守护线程。 
//setDaemon(): 设置一个线程为守护线程。(用户线程和守护线程的区别在于，是否等待主线程依赖于主线程结束而结束) 
//setName(): 为线程设置一个名称。 
//wait(): 强迫一个线程等待。 
//notify(): 通知一个线程继续运行。 
//setPriority(): 设置一个线程的优先级。
public class MultiThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		// 匿名类实现多线程
		// new Thread(new Runnable() {
		// public void run() {
		// for (int i = 0; i < 10; i++) {
		// System.out.println("线程1");
		// }
		// }
		// }).start();
		// new Thread(new FutureTask<>(new Callable<Integer>() {
		// public Integer call()
		// {
		// System.out.println("线程2");
		// return 1;
		// }
		// })).start();

		// start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
		// new ThreadDemo("A").start();
		// new ThreadDemo("B").start();

		// 多线程资源共享
		// RunnableDemo my = new RunnableDemo();
		// new Thread(my, "C").start();
		// new Thread(my, "D").start();

		// 经典面试题
		 SYNPo a = new SYNPo("a");
		 SYNPo b = new SYNPo("b");
		 SYNPo c = new SYNPo("c");
		 MyThreadPrinter pa = new MyThreadPrinter("A", c, a);
		 MyThreadPrinter pb = new MyThreadPrinter("B", a, b);
		 MyThreadPrinter pc = new MyThreadPrinter("C", b, c);
		 new Thread(pa).start();
		 Thread.sleep(100); // 确保按顺序A、B、C执行
		 new Thread(pb).start();
		 Thread.sleep(100);
		 new Thread(pc).start();
		 Thread.sleep(100);

		// 通过 Callable 和 Future 创建线程
		// 1. 创建 Callable 接口的实现类，并实现 call() 方法，该 call() 方法将作为线程执行体，并且有返回值。
		// 2. 创建 Callable 实现类的实例，使用 FutureTask 类来包装 Callable 对象，该 FutureTask
		// 对象封装了该 Callable 对象的 call() 方法的返回值。
		// 3. 使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程。
		// 4. 调用 FutureTask 对象的 get() 方法来获得子线程执行结束后的返回值。
		// FutureTask有下面几个重要的方法：
		// 1.get()
		// 阻塞一直等待执行完成拿到结果
		// 2.get(int timeout, TimeUnit timeUnit)
		// 阻塞一直等待执行完成拿到结果，如果在超时时间内，没有拿到抛出异常
		// 3.isCancelled()
		// 是否被取消
		// 4.isDone()
		// 是否已经完成
		// 5.cancel(boolean mayInterruptIfRunning)
		// 试图取消正在执行的任务
		// CallableDemo cDemo = new CallableDemo();
		// FutureTask<Integer> fTask = new FutureTask<>(cDemo);
		// for (int i = 0; i < 100; i++) {
		// System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" +
		// i);
		// if (i == 20) {
		// new Thread(fTask, "有返回值的线程").start();
		// }
		// }
		// try {
		// System.out.println("子线程的返回值：" + fTask.get());
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }
	}

	// 继承Thread类实现多线程
	public static class ThreadDemo extends Thread {
		private String name;
		private int count = 5;

		public ThreadDemo(String name) {
			this.name = name;
		}

		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println(name + "运行  count= " + count--);
				try {
					// Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
					sleep((int) Math.random() * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 实现Runnable接口。 可以资源共享
	public static class RunnableDemo implements Runnable {
		private int count = 5;

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				if (count > 0) {
					System.out.println(Thread.currentThread().getName() + "运行  count= " + count--);
				}
				try {
					Thread.sleep((int) Math.random() * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 实现Callable 接口。并实现 call() 方法，该 call() 方法将作为线程执行体，并且有返回值。
	public static class CallableDemo implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int i = 0;
			for (; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			return i;
		}
	}

	public static class SYNPo {
		public SYNPo(String name) {
			setName(name);
		}

		public String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// 经典面试题
	// 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
	// 主要考察obj.wait()和obj.notify() 的用法
	public static class MyThreadPrinter implements Runnable {

		private String name;
		private SYNPo prev;
		private SYNPo self;

		private MyThreadPrinter(String name, SYNPo prev, SYNPo self) {
			this.name = name;
			this.prev = prev;
			this.self = self;
		}

		@Override
		public void run() {
			int count = 10;
			while (count > 0) {
				System.out.println("线程" + name + "申请获得对象" + prev.getName() + "的锁");
				synchronized (prev) {
					System.out.println("线程" + name + "获得了对象" + prev.getName() + "的锁");
					System.out.println("线程" + name + "申请获得对象" + self.getName() + "的锁");
					synchronized (self) {
						System.out.println("线程" + name + "获得了对象" + self.getName() + "的锁");
						System.out.println(name);
						count--;

						// notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，
						// 自动释放锁后,JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
						self.notify();
//						System.out.println("对象" + self.getName() + "被赋予给了等待的进程" + "【唤醒操作!!!】");
					}
					System.out.println("线程" + name + "释放了对象" + self.getName() + "的锁");
					try {
						// Thread.sleep()与Object.wait()二者都可以暂停当前线程，释放CPU控制权，
						// 主要的区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。
						System.out.println("线程" + name + "释放了对象" + prev.getName() + "的锁");
						System.out.println("线程" + name + "进入休眠状态。等待获取" + prev.getName() + "的锁" + "【等待中。。。】");
						prev.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}
}
