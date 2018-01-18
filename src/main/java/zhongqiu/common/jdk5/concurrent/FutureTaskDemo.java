package zhongqiu.common.jdk5.concurrent;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wangzhongqiu
 * @date 2018/1/18.
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Date begin = new Date();
        Calculate calculate = new Calculate();
        int c = 0;

        //异步计算
        int two = 0;
        CallableDemo cDemo = new CallableDemo();
        cDemo.setCalculate(calculate);
        FutureTask<Integer> fTask = new FutureTask<>(cDemo);
        new Thread(fTask, "有返回值的线程").start();
        int one = calculate.calOne();
        two = fTask.get();

        //求和
        c = one + two;
        Date end = new Date();
        System.out.println("计算结果：" + c);
        long aaa = end.getTime() - begin.getTime();
        System.out.println("计算耗时：" + aaa);
    }
}

class Calculate {
    public int calOne() throws InterruptedException {
        Thread.sleep(10000);
        return 10;
    }

    public int calTwo() throws InterruptedException {
        Thread.sleep(5000);
        return 5;
    }

}

class CallableDemo implements Callable<Integer> {
    private Calculate calculate;

    public Calculate getCalculate() {
        return calculate;
    }

    public void setCalculate(Calculate calculate) {
        this.calculate = calculate;
    }

    @Override
    public Integer call() throws Exception {
        return calculate.calTwo();
    }
}