package zhongqiu.common.base.thread;

/**
 * @author wangzhongqiu
 * @date 2018/1/31.
 */
public class ThreadDemo {
    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        Thread thread = new Thread(runnableDemo, "new");
        thread.start();
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread线程状态：" + thread.getState());
        thread.interrupt();
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread线程状态：" + thread.getState());
    }

    public static class RunnableDemo implements Runnable {
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
}
