package zhongqiu.common.jdk5.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
//http://www.2cto.com/kf/201601/487906.html
//http://blog.csdn.net/ghsau/article/details/7481142
//http://blog.csdn.net/defonds/article/details/44021605#t7
/*
* 使用场景，主要对生产过剩的生产者进行阻塞，对过多的消费者进行阻塞。
* 常用方法：放入元素，add，offer，put     获取元素，peek，poll，take，drainTo
* ArrayBlockingQueue 是一个有界的阻塞队列，其内部实现是将对象放到一个数组里。它不能够存储无限多数量的元素。
*                    它有一个同一时间能够存储元素数量的上限。你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了
*    方法源码分析：同时有三个线程调用put方法，线程1执行lock.lockInterruptibly()加锁，线程2和线程3放入到lock的aqs队列中等待锁，
*                 假设阻塞队列中已达到最大值，线程1执行notFull.await();执行addConditionWaiter()把线程1放到nullFull条件队列中，
*                 执行fullyRelease唤醒aqs队列中的下一个节点，线程1阻塞；以此类推，线程2和线程3也被放入到了nullFull条件队列中。
*                 全部阻塞在了notFull.await()方法内部。线程4调用take方法，执行lock.lockInterruptibly()加锁，
*                 执行extract()取走元素，执行notFull.signal()把阻塞在nullFull条件队列中的第一个线程放入到aqs队尾，也就是线程1，
*                 执行lock.unlock()解锁，唤醒aqs中的第一个线程。线程1被唤醒，继续执行insert，notEmpty.signal()，lock.unlock()
*
* LinkedBlockingQueue 内部以一个链式结构(链接节点)对其元素进行存储。
*                     如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE 作为上限。
*
* */

//PriorityBlockingQueue：PriorityBlockingQueue 是一个无界的并发队列。
//它使用了和类 java.util.PriorityQueue 一样的排序规则。你无法向这个队列中插入 null 值。
//所有插入到 PriorityBlockingQueue 的元素必须实现 java.lang.Comparable 接口。因此该队列中元素的排序就取决于你自己的 Comparable 实现。

//SynchronousQueue：SynchronousQueue。
//一种阻塞队列，其中每个插入操作必须等待另一个线程的对应移除操作 ，反之亦然。同步队列没有任何内部容量，甚至连一个队列的容量都没有。
//同步队列类似于 CSP 和 Ada 中使用的 rendezvous 信道。
//它非常适合于传递性设计，在这种设计中，在一个线程中运行的对象要将某些信息、事件或任务传递给在另一个线程中运行的对象，它就必须与该对象同步。
public class BlockingQueueDemo {
    private ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
    private LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
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
}
