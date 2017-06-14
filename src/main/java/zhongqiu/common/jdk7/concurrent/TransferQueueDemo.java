package zhongqiu.common.jdk7.concurrent;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

//Java7中加入了JSR 166y规范对集合类和并发类库的改进。其中的一项是增加了接口TransferQueue和其实现类LinkedTransferQueue。
//TransferQueue继承了BlockingQueue（BlockingQueue又继承了Queue）并扩展了一些新方法。
//TransferQueue则更进一步，生产者会一直阻塞直到所添加到队列的元素被某一个消费者所消费（不仅仅是添加到队列里就完事）。
//新添加的transfer方法用来实现这种约束。顾名思义，阻塞就是发生在元素从一个线程transfer到另一个线程的过程中，
//它有效地实现了元素在线程之间的传递（以建立Java内存模型中的happens-before关系的方式）。
//LinkedTransferQueue实际上是ConcurrentLinkedQueue、SynchronousQueue（公平模式）和LinkedBlockingQueue的超集。
public class TransferQueueDemo {
	private static TransferQueue<String> queue = new LinkedTransferQueue<String>();

	public static void main(String[] args) throws Exception {
		// queue.transfer用法
		new Productor1(1).start();
		Thread.sleep(100);
		System.out.println("over.size=" + queue.size());

		// queue.tryTransfer(result, 1, TimeUnit.SECONDS); 用法
		new Productor2(1).start();
		Thread.sleep(100);
		System.out.println("over.size=" + queue.size());// 2
		Thread.sleep(1500);
		System.out.println("over.size=" + queue.size());// 1
	}

	// transfer(E e)
	// 若当前存在一个正在等待获取的消费者线程，即立刻将e移交之；否则将元素e插入到队列尾部，并且当前线程进入阻塞状态，直到有消费者线程取走该元素。
	static class Productor1 extends Thread {
		private int id;

		public Productor1(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				String result = "id=" + this.id;
				System.out.println("begin to produce." + result);
				queue.transfer(result);
				System.out.println("success to produce." + result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// tryTransfer(E e, long timeout, TimeUnit unit)
	// 若当前存在一个正在等待获取的消费者线程，会立即传输给它;
	// 否则将元素e插入到队列尾部，并且等待被消费者线程获取消费掉。若在指定的时间内元素e无法被消费者线程获取，则返回false，同时该元素从队列中移除。
	static class Productor2 extends Thread {
		private int id;

		public Productor2(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				String result = "id=" + this.id;
				System.out.println("begin to produce." + result);
				queue.tryTransfer(result, 1, TimeUnit.SECONDS);
				System.out.println("success to produce." + result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
