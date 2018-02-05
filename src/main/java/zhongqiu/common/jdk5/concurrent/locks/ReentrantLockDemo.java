package zhongqiu.common.jdk5.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

/*
* 1. Lock的加锁和解锁都是由java代码配合native方法（调用操作系统的相关方法）实现的，而synchronize的加锁和解锁的过程是由JVM管理的
* 2. 当一个线程使用synchronize获取锁时，若锁被其他线程占用着，那么当前只能被阻塞，直到成功获取锁。
*    而Lock则提供超时锁和可中断等更加灵活的方式，在未能获取锁的条件下提供一种退出的机制。
* 3. 一个锁内部可以有多个Condition实例，即有多路条件队列，而synchronize只有一路条件队列；
*    同样Condition也提供灵活的阻塞方式，在未获得通知之前可以通过中断线程以及设置等待时限等方式退出条件队列。
* 4. synchronize对线程的同步仅提供独占模式，而Lock即可以提供独占模式，也可以提供共享模式
* */
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
            @Override
            public void run() {
                output.output("lisi");
            }

            ;
        }.start();
    }

    static class Outputter1 {
        private ReentrantLock nonfairLock = new ReentrantLock();
        private ReentrantLock fairLock = new ReentrantLock(true);

        public void output(String name) {
            nonfairLock.lock();
            fairLock.lock();
            try {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
            } finally {
                nonfairLock.unlock();
                fairLock.unlock();
            }
        }
    }
}


