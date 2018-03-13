package zhongqiu.common.jdk5.concurrent;

import java.util.concurrent.*;

/**
 * ScheduledThreadPoolExecutor 调度线程池
 */
public class ScheduledThreadPoolExecutorDemo {
    static ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(5);

    public static void main(String[] args) {

    }

    // 延迟1s后开始执行，只执行一次，没有返回值
    static void scheduleRunable() throws InterruptedException, ExecutionException {
        ScheduledFuture<?> result = scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("gh");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000, TimeUnit.MILLISECONDS);
        System.out.println(result.get());
    }

    // 延迟1s后开始执行，只执行一次，有返回值
    static void scheduleCaller() throws InterruptedException, ExecutionException {
        ScheduledFuture<String> result = scheduledThreadPool.schedule(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "gh";
            }
        }, 1000, TimeUnit.MILLISECONDS);
        // 阻塞，直到任务执行完成
        System.out.print(result.get());
    }

    // 固定时间间隔执行，延迟1s后开始执行任务，任务结束和下一个任务开始之间间隔2秒
    static void scheduleWithFixedDelay() throws ExecutionException, InterruptedException {
        ScheduledFuture<?> result = scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
        // 由于是定时任务，一直不会返回
        result.get();
        System.out.println("over");
    }

    // 固定频率执行：延迟1s后开始执行任务，每隔两秒新执行一个任务，但是如果执行任务时间大约2s则不会并发执行后续任务将会延迟。
    static void scheduleAtFixedRate() throws InterruptedException, ExecutionException {
        ScheduledFuture<?> result = scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
        // 由于是定时任务，一直不会返回
        result.get();
        System.out.println("over");
    }
}
