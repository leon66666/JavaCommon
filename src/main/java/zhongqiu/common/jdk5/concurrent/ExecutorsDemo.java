package zhongqiu.common.jdk5.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//线程池相关
public class ExecutorsDemo {
    public static void main(String[] args) {
        // CachedThreadPool.demo();
        // FixedThreadPool.demo();
        // SingleThreadExecutor.demo();
        // ScheduledThreadPool.demo();
    }

    // Executors.newFixedThreadPool(3);
    // 创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。
    // FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。
    // 但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
    // 堆积的请求处理队列，会占用非常大的内存
    static class FixedThreadPool {
        static void demo() {
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
            for (int i = 0; i < 10; i++) {
                final int index = i;
                fixedThreadPool.execute(new Runnable() {
                    public void run() {
                        try {
                            System.out.println(index);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    // Executors.newSingleThreadExecutor();
    // 创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO,
    // 优先级)执行。
    // 如果这个线程异常结束，会有另一个取代它，保证顺序执行。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
    // 堆积的请求处理队列，会占用非常大的内存
    static class SingleThreadExecutor {
        static void demo() {
            ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                singleThreadExecutor.execute(new Runnable() {
                    public void run() {
                        try {
                            System.out.println(index);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    // Executors.newScheduledThreadPool(5);
    // 创建一个定长的线程池，支持定时的以及周期性的任务执行
    // 缺点：最大线程数Integer.Max。可能会创建非常多的线程
    static class ScheduledThreadPool {
        static void demo() {
            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
            // 延迟3秒执行
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.out.println("delay 3 seconds");
                }
            }, 3, TimeUnit.SECONDS);

            // 表示延迟1秒后每3秒执行一次
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    System.out.println("delay 1 seconds, and excute every 3 seconds");
                }
            }, 1, 3, TimeUnit.SECONDS);
        }
    }

    // Executors.newCachedThreadPool()
    // 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
    // 这种类型的线程池特点是：
    // 工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
    // 如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。
    // 终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
    // 缺点：最大线程数Integer.Max。可能会创建非常多的线程。
    static class CachedThreadPool {
        static void demo() {
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                try {
                    Thread.sleep(index * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cachedThreadPool.execute(new Runnable() {
                    public void run() {
                        System.out.println(index);
                    }
                });
            }
        }
    }
}


