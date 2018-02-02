package zhongqiu.common.jdk5.concurrent.locks;

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


