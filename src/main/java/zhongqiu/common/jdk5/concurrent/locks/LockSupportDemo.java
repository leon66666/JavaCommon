package zhongqiu.common.jdk5.concurrent.locks;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangzhongqiu
 * @date 2018/2/1.
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        SpinRunnable spinRunnable = new SpinRunnable();
        Thread thread = new Thread(spinRunnable, "new");
        thread.start();
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread线程状态：" + thread.getState());

        LockSupport.unpark(thread);
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread线程状态：" + thread.getState());

        LockSupport.unpark(thread);
        LockSupport.unpark(thread);
        LockSupport.unpark(thread);
        System.out.println("thread线程状态：" + thread.getState());
    }

    //阻塞thread
    public static class BlockRunnable implements Runnable {
        private volatile int count = 50;
        private static Object lock = new Object();

        @Override
        public void run() {
            System.out.println("thread线程开始运行");
            synchronized (lock) {
                try {
                    lock.wait(1000 * 10);
                } catch (InterruptedException e) {
                    System.out.println("thread线程状态：" + Thread.currentThread().getState());
                }
            }
            System.out.println("thread线程运行结束");
        }
    }

    //自旋thread
    public static class SpinRunnable implements Runnable {
        private volatile int count = 50;
        private static Object lock = new Object();

        @Override
        public void run() {
            System.out.println("thread线程开始运行");
            LockSupport.park();

            while (true) {

            }
        }
    }
}
