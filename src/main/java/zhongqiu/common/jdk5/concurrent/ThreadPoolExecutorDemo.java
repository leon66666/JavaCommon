package zhongqiu.common.jdk5.concurrent;

import java.util.concurrent.*;

//ThreadPoolExecutor 线程池
// http://825635381.iteye.com/blog/2184680
/*
* ThreadPoolExecutor extends AbstractExecutorService
* abstract class AbstractExecutorService implements ExecutorService
* interface ExecutorService extends Executor
*
* */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, blockingQueue);

        //execute方法，没有返回值，执行runnable
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });

        //submit方法带有返回值，用future接收，通过future.get()方法获取返回值
        Future future = threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}