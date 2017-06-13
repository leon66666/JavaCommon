package zhongqiu.common.base.thread;

//经典面试题
// 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
// 主要考察obj.wait()和obj.notify() 的用法
public class PrintABC extends Thread {
	public static void main(String[] args) throws InterruptedException {
		// 经典面试题
		SYNPo a = new SYNPo("a");
		SYNPo b = new SYNPo("b");
		SYNPo c = new SYNPo("c");
		PrintABC pa = new PrintABC("A", c, a);
		PrintABC pb = new PrintABC("B", a, b);
		PrintABC pc = new PrintABC("C", b, c);
		new Thread(pa).start();
		Thread.sleep(100); // 确保按顺序A、B、C执行
		new Thread(pb).start();
		Thread.sleep(100);
		new Thread(pc).start();
		Thread.sleep(100);
	}

	private String name;
	private SYNPo prev;
	private SYNPo self;

	private PrintABC(String name, SYNPo prev, SYNPo self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			// System.out.println("线程" + name + "申请获得对象" + prev.getName() +
			// "的锁");
			synchronized (prev) {
				// System.out.println("线程" + name + "获得了对象" + prev.getName() +
				// "的锁");
				// System.out.println("线程" + name + "申请获得对象" + self.getName() +
				// "的锁");
				synchronized (self) {
					// System.out.println("线程" + name + "获得了对象" + self.getName()
					// + "的锁");
					System.out.println(name);
					count--;

					// notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，
					// 自动释放锁后,JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
					self.notify();
					// System.out.println("对象" + self.getName() +
					// "被赋予给了等待的进程" + "【唤醒操作!!!】");
				}
				// System.out.println("线程" + name + "释放了对象" + self.getName() +
				// "的锁");
				try {
					// Thread.sleep()与Object.wait()二者都可以暂停当前线程，释放CPU控制权，
					// 主要的区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。
					// System.out.println("线程" + name + "释放了对象" + prev.getName()
					// + "的锁");
					// System.out.println("线程" + name + "进入休眠状态。等待获取" +
					// prev.getName() + "的锁" + "【等待中。。。】");
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

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
}
