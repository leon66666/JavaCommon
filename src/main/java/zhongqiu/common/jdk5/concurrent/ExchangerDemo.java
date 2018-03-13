package zhongqiu.common.jdk5.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/*
* Exchanger是双向的数据传输，2个线程在一个同步点，交换数据。先到的线程会等待第二个线程执行exchange
* SynchronousQueue，是2个线程之间单向的数据传输，一个put，一个take。
*
* */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        new Consumer(exchanger).start();
        new Producer(exchanger).start();
    }

    static class Producer extends Thread {
        List<Integer> list = new ArrayList<>();
        Exchanger<List<Integer>> exchanger = null;

        public Producer(Exchanger<List<Integer>> exchanger) {
            super();
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < 10; i++) {
                list.clear();
                list.add(rand.nextInt(10000));
                list.add(rand.nextInt(10000));
                list.add(rand.nextInt(10000));
                list.add(rand.nextInt(10000));
                list.add(rand.nextInt(10000));
                try {
                    list = exchanger.exchange(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        List<Integer> list = new ArrayList<>();
        Exchanger<List<Integer>> exchanger = null;

        public Consumer(Exchanger<List<Integer>> exchanger) {
            super();
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    list = exchanger.exchange(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(list.get(0) + ", ");
                System.out.print(list.get(1) + ", ");
                System.out.print(list.get(2) + ", ");
                System.out.print(list.get(3) + ", ");
                System.out.println(list.get(4) + ", ");
            }
        }
    }
}
