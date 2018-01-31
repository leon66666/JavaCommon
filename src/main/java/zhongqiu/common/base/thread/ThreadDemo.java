package zhongqiu.common.base.thread;

/**
 * @author wangzhongqiu
 * @date 2018/1/31.
 */
public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println("main线程状态：" + Thread.currentThread().getState());
        RunnableDemo runnableDemo = new RunnableDemo();
        Thread thread = new Thread(runnableDemo, "new");
        thread.start();
        System.out.println("thread线程状态：" + thread.getState());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main线程状态：" + Thread.currentThread().getState());
    }

    public static class RunnableDemo implements Runnable {
        private volatile int count = 50;

        @Override
        public void run() {
            System.out.println("111");
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            while (true) {
//                int i = 0;
//            }
        }
    }
}
