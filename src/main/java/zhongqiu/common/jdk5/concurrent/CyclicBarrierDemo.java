package zhongqiu.common.jdk5.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
*
我们知道AQS自己维护的队列是当前等待资源的队列，AQS会在资源被释放后，依次唤醒队列中从前到后的所有节点，使他们对应的线程恢复执行。直到队列为空。
而Condition自己也维护了一个队列，该队列的作用是维护一个等待signal信号的队列，两个队列的作用是不同，
事实上，每个线程也仅仅会同时存在以上两个队列中的一个，流程是这样的：
1. 线程1调用reentrantLock.lock时，线程2、线程3、线程4获取reentrantLock失败，被加入到AQS的等待队列中,进入waiting状态
2. 线程1调用await方法，--count，该线程被加入到Condition的等待队列中，
3. 唤醒AQS队列中的第一个等待的线程，也就是线程2，执行LockSupport.park(this);等待获得许可，线程1进入waiting状态
4. 接下来线程2，线程3，同样方式，依次，调用reentrantLock.lock，调用await方法，加入到Condition的等待队列中，进入waiting状态，等待获得许可
5. 直到线程4将count减到0，执行trip.signalAll();把Condition的等待队列中的线程重新加入到AQS的等待队列中。
6. 线程4执行完毕，执行lock.unlock();唤醒AQS的等待队列中最先进入的线程1，线程1执行完毕，执行lock.unlock()，
7. 线程1执行完毕，执行lock.unlock()，唤醒线程2，依次线程3.
可以看到，整个协作过程是靠结点在AQS的等待队列和Condition的等待队列中来回移动实现的，Condition作为一个条件类，很好的自己维护了一个等待信号的队列，
并适时的时候将结点加入到AQS的等待队列中来实现的唤醒操作。
* */
//场景分析:10个人去春游,规定达到一个地点后才能继续前行.
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int num = 10;
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println("go on together!");
            }
        });
        for (int i = 1; i <= 10; i++) {
            new Thread(new CyclicBarrierWorker(i, barrier)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class CyclicBarrierWorker implements Runnable {
        private int id;
        private CyclicBarrier barrier;

        public CyclicBarrierWorker(int id, final CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(id + " th people wait");
                barrier.await(); // 大家等待最后一个线程到达
                System.out.println(id + " th people go on");
            } catch (InterruptedException | BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}


