package zhongqiu.common.jdk5.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/*
* Exchanger是双向的数据传输，2个线程在一个同步点，交换数据。先到的线程会等待第二个线程执行exchange
* SynchronousQueue，是2个线程之间单向的数据传输，一个put，一个take。
* 多个重载的exchange方法最终都是调用的doExchange(Object item, boolean timed, long nanos)方法
* doExchange方法：A线程调用exchange(object),生成封装的Node对象【Node me = new Node(item)】，获取到A线程的index。开始for循环
*                  第一步：如果arena[index]不为空，则和【slot=arena[index]】位置的线程B交换数据，
*                         交换中先获取到B线程的交换数据【Object y = slot.get()】
*                         然后释放slot以便其他线程可以使用【slot.compareAndSet(y, null)】
*                         转换成Node【Node you = (Node)y】
*                         把交换数据传递给B线程【you.compareAndSet(null, item)】
*                         唤醒B线程【LockSupport.unpark(you.waiter)】
*                         返回B线程获取到的待交换数据【return you.item】
*                  第二步：【y=slot.get()==null】说明index位置没有等待交换的线程，则把A线程放到index位置等待，
*                          如果【index == 0】【await(me, slot)】自旋次数减少，减到0为止【LockSupport.park(me)】
*                          否则，自旋等待【spinWait(me, slot)】，自旋结束前获取到了其他线程的交换数据【Object v = me.get();】返回v
*                                                              自旋次数结束，返回CANCEL
*                          index >>>= 1。index减少一半，换位置继续执行for循环
*                  第三步：上面两步跳过了，跳过次数过多，增大index，重新for循环
* */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        new Consumer(exchanger).start();
        //方便调试，让consumer先执行exchange
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Producer(exchanger).start();
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
            for (int i = 0; i < 1; i++) {
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
            for (int i = 0; i < 1; i++) {
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
}
