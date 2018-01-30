package zhongqiu.common.jdk5.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        final Outputter1 output = new Outputter1();
//        new Thread() {
//            public void run() {
//                output.output("zhangsan");
//            }
//
//            ;
//        }.start();
        new Thread() {
            public void run() {
                output.output("lisi");
            }

            ;
        }.start();
    }
}

class Outputter1 {
    private ReentrantLock lock = new ReentrantLock();// 锁对象

    public void output(String name) {
        // TODO 线程输出方法
        lock.lock();// 得到锁
        lock.lock();
        try {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
        } finally {
            lock.unlock();// 释放锁
        }
    }
}
