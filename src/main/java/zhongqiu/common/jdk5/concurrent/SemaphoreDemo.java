package zhongqiu.common.jdk5.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
* 实现原理，和countdownlatch类似，内部类sync实现了AbstractQueuedSynchronizer
* 使用场景：Semaphore经常用于限制获取某种资源的线程数量。
*          比如说操场上有5个跑道，一个跑道一次只能有一个学生在上面跑步，一旦所有跑道在使用，
*          那么后面的学生就需要等待，直到有一个学生不跑了
* */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {

                public void run() {
                    try {
                        // 获取许可
                        semaphore.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 10000));
                        // 访问完后，释放
                        semaphore.release();
                        System.out.println("-----------------" + semaphore.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
}
