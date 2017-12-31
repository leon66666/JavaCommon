package zhongqiu.common.base.thread;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/12/24.
 */
public class VolatileDemo2 {
    public static Hashtable<String, Integer> num = new Hashtable<>();
    private  Integer a = 1;
    private  Integer b = 2;

    public void init() {
        a = 1;
        b = 2;
    }

    public void change() {
        a = 1;
        b = 2;
        a = 3;
        b = a;
    }

    public void print() {
        String key = a.toString() + b.toString();
        if (num.keySet().contains(key)) {
            num.put(key, num.get(key) + 1);
        } else {
            num.put(key, 1);
        }
    }

    public static void test1() {
        final VolatileDemo2 test = new VolatileDemo2();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        ExecutorService fixedThreadPool1 = Executors.newFixedThreadPool(1);
        ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        test.change();
                    }
                }
            });
            fixedThreadPool1.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        test.init();
                    }
                }
            });
            fixedThreadPool2.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        test.print();
                    }
                }
            });
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Map.Entry entry : num.entrySet()) {
            System.out.println("key:" + entry.getKey() + "--value:" + entry.getValue());
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
