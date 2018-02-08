package zhongqiu.common.jdk5.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

//这是由硬件提供原子操作指令实现的。在非激烈竞争的情况下，开销更小，速度更快
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        final Counter counter = new Counter();
        final CounterSyn counterSyn = new CounterSyn();
        final CounterAtomic counterAtomic = new CounterAtomic();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        counterSyn.increment();
//                        counterAtomic.increment();
                        counter.increment();
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("counter:" + counter.getCount());
        System.out.println("counterSyn:" + counterSyn.getCount());
        System.out.println("counterAtomic:" + counterAtomic.getCount());
    }

    static class Counter implements Count {
        private volatile int count = 0;

        @Override
        public void increment() {
            count++;
        }

        @Override
        public int getCount() {
            return count;
        }
    }

    static class CounterSyn implements Count {
        private volatile int count = 0;

        // 若要线程安全执行执行count++，需要加锁
        @Override
        public synchronized void increment() {
            try {
                Thread.sleep(1000 * 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }

        @Override
        public int getCount() {
            return count;
        }
    }

    static class CounterAtomic implements Count {
        private AtomicInteger count = new AtomicInteger();

        @Override
        public void increment() {
            count.incrementAndGet();
        }

        @Override
        public int getCount() {
            return count.get();
        }
    }

    interface Count {
        void increment();

        int getCount();
    }

    static class Add implements Runnable {
        public Count getCount() {
            return count;
        }

        public void setCount(Count count) {
            this.count = count;
        }

        private Count count;

        Add(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            count.increment();
        }
    }
}


