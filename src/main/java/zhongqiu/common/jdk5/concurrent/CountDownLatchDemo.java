package zhongqiu.common.jdk5.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangzhongqiu
 * @date 2018/2/28.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        int count = 2;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        Cal cal = new Cal(countDownLatch);
        new Thread(cal).start();
        new Thread(cal).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all done");
    }

    static class Cal implements Runnable {
        private CountDownLatch countDownLatch;

        public Cal(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("完成计算");
            countDownLatch.countDown();
        }
    }
}
