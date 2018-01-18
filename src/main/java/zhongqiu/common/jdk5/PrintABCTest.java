package zhongqiu.common.jdk5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PrintABCTest {
    static Semaphore A = new Semaphore(0);
    static Semaphore B = new Semaphore(0);
    static Semaphore C = new Semaphore(0);
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        A.release();
        for(int i = 0 ; i < 10; i ++) {
            executorService.execute(new SayA());
            executorService.execute(new SayB());
            executorService.execute(new SayC());
        }

        executorService.shutdown();
    }

    static class SayA implements Runnable {
        @Override
        public void run() {
            try {
                A.acquire();
                System.out.println("A"+Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                B.release();
            }
        }
    }

    static class SayB implements Runnable {
        @Override
        public void run() {
            try {
                B.acquire();
                System.out.println("B"+Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                C.release();
            }
        }
    }
    static class SayC implements Runnable {
        @Override
        public void run() {
            try {
                C.acquire();
                System.out.println("C"+Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                A.release();
            }
        }
    }
}
