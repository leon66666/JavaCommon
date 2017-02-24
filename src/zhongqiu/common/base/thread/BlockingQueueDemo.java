package zhongqiu.common.base.thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
//http://www.2cto.com/kf/201601/487906.html
//http://blog.csdn.net/ghsau/article/details/7481142
//http://blog.csdn.net/chenchaofuck1/article/details/51660119
//使用场景，主要对生产过剩的生产者进行阻塞，对过多的消费者进行阻塞。
//种类：
public class BlockingQueueDemo {
	// 生产者
	public static class Producer implements Runnable {
		private final BlockingQueue<Integer> blockingQueue;
		private volatile boolean flag;
		private Random random;

		public Producer(BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
			flag = false;
			random = new Random();

		}

		public void run() {
			while (!flag) {
				int info = random.nextInt(100);
				try {
					blockingQueue.put(info);
					System.out.println(Thread.currentThread().getName() + " produce " + info);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void shutDown() {
			flag = true;
		}
	}

	// 消费者
	public static class Consumer implements Runnable {
		private final BlockingQueue<Integer> blockingQueue;
		private volatile boolean flag;

		public Consumer(BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		public void run() {
			while (!flag) {
				int info;
				try {
					info = blockingQueue.take();
					System.out.println(Thread.currentThread().getName() + " consumer " + info);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void shutDown() {
			flag = true;
		}
	}

	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(3);
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);
		// 创建5个生产者，5个消费者
		for (int i = 0; i < 6; i++) {
			if (i < 5) {
				new Thread(producer, "producer" + i).start();
			} else {
				new Thread(consumer, "consumer" + (i - 5)).start();
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		producer.shutDown();
		consumer.shutDown();
	}
}
