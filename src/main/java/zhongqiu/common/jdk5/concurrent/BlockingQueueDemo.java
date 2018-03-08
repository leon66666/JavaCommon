package zhongqiu.common.jdk5.concurrent;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.*;

//http://www.2cto.com/kf/201601/487906.html
//http://blog.csdn.net/ghsau/article/details/7481142
//http://blog.csdn.net/defonds/article/details/44021605#t7
/*
* BlockingQueue
* 使用场景，主要对生产过剩的生产者进行阻塞，对过多的消费者进行阻塞。
* 常用方法：放入元素，add，offer，put     获取元素，peek，poll，take，drainTo
* ArrayBlockingQueue 是一个有界的阻塞队列，其内部实现是将对象放到一个循环数组里。它不能够存储无限多数量的元素。
*                    它有一个同一时间能够存储元素数量的上限。你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了
*     方法源码分析：同时有三个线程调用put方法，线程1执行lock.lockInterruptibly()加锁，线程2和线程3放入到lock的aqs队列中等待锁，
*                 假设阻塞队列中已达到最大值，线程1执行notFull.await();执行addConditionWaiter()把线程1放到nullFull条件队列中，
*                 执行fullyRelease唤醒aqs队列中的下一个节点，线程1阻塞；以此类推，线程2和线程3也被放入到了nullFull条件队列中。
*                 全部阻塞在了notFull.await()方法内部。线程4调用take方法，执行lock.lockInterruptibly()加锁，
*                 执行extract()取走元素，执行notFull.signal()把阻塞在nullFull条件队列中的第一个线程放入到aqs队尾，也就是线程1，
*                 执行lock.unlock()解锁，唤醒aqs中的第一个线程。线程1被唤醒，继续执行insert，notEmpty.signal()，lock.unlock()
* LinkedBlockingQueue 内部以一个链式结构(链接节点)对其元素进行存储。
*                     如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE 作为上限。
*                     使用了put和take两个锁进行锁分离，效率比ArrayBlockingQueue高
* SynchronousQueue 阻塞队列，基于Spin和CAS方法。其中每个插入操作必须等待另一个线程的对应移除操作 ，反之亦然。
*                  同步队列没有任何内部容量，甚至连一个队列的容量都没有。
*     TransferQueue 先进先出队列，公平模式。核心方法,transfer
*
* PriorityBlockingQueue 基于数组实现的线程安全的无界优先级队列，你无法向这个队列中插入 null 值。
*                       所有插入到 PriorityBlockingQueue 的元素必须实现 java.lang.Comparable 接口
*                       以数组的形式实现的最大或最小堆.
*     添加方法：add,offer,put，最终调用的都是offer方法，因为是无界队列，入队列不会阻塞。
*     offer方法： lock.lock()加锁，tryGrow(array, cap)增加容量，siftUpUsingComparator(n, e, array, cmp)自下而上调整，
*               notEmpty.signal()唤醒take线程。lock.unlock()解锁
*     获取方法：如果队列中没有元素，获取失败，线程park，进入waiting状态。
*     take方法： lock.lockInterruptibly()加锁，while ( (result = dequeue()) == null) notEmpty.await()。最后lock.unlock()解锁
* DelayQueue  无界的优先级的阻塞队列，其中的对象只能在其到期时才能从队列中取走。
*             DelayQueue<E extends Delayed>,需实现方法long getDelay(TimeUnit unit);public int compareTo(T o);
*     添加方法：add,offer,put，最终调用的都是offer方法，因为是无界队列，入队列不会阻塞。
*     应用场景：具有过期时间的缓存；多学生答题系统(时间到，自动交卷);实现订单的定时取消(用户也可以主动取消);
*
* */
public class BlockingQueueDemo {
    private ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
    private LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();
    private SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    private PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
    private DelayQueue<Student> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        ArrayBlockingQueueTest();

//        delayQueueTest_Exam();
//        delayQueueTest_Cache();
    }

    public static void delayQueueTest_Exam() {
        int studentNumber = 20;
        DelayQueue<Student> students = new DelayQueue<Student>();
        Random random = new Random();
        for (int i = 0; i < studentNumber; i++) {
            students.put(new Student("student" + (i + 1), 30 + random.nextInt(120)));
        }
        students.put(new Student("student", 120));
        Thread teacherThread = new Thread(new Teacher(students));
        teacherThread.start();
    }

    public static void delayQueueTest_Cache() {
        Random random = new Random();
        int cacheNumber = 10;
        int liveTime = 0;
        Cache<String, Integer> cache = new Cache<String, Integer>();

        for (int i = 0; i < cacheNumber; i++) {
            liveTime = random.nextInt(3000);
            System.out.println(i + "  " + liveTime);
            cache.put(i + "", i, random.nextInt(liveTime));
            if (random.nextInt(cacheNumber) > 7) {
                liveTime = random.nextInt(3000);
                System.out.println(i + "  " + liveTime);
                cache.put(i + "", i, random.nextInt(liveTime));
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void PriorityBlockingQueueTest() {
        final PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start");
                priorityBlockingQueue.put(1);
                System.out.println("put thread end");
            }
        });
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    System.out.println("take from putThread: " + priorityBlockingQueue.take());
                } catch (InterruptedException e) {
                }
                System.out.println("take thread end");
            }
        });
    }

    public static void SynchronousQueueTest() {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start");
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                }
                System.out.println("put thread end");
            }
        });
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    System.out.println("take from putThread: " + queue.take());
                } catch (InterruptedException e) {
                }
                System.out.println("take thread end");
            }
        });
        putThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takeThread.start();
    }

    public static void ArrayBlockingQueueTest() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        // 创建5个生产者，5个消费者
        for (int i = 0; i < 6; i++) {
            if (i < 5) {
                new Thread(producer, "producer" + i).start();
            } else {
                new Thread(consumer, "consumer" + (i - 5)).start();
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        producer.shutDown();
        consumer.shutDown();
    }

    //http://www.cnblogs.com/sunzhenchao/p/3515085.html
    static public class Cache<K, V> {
        public ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();
        public DelayQueue<DelayedItem<K>> queue = new DelayQueue<DelayedItem<K>>();

        public void put(K k, V v, long liveTime) {
            V v2 = map.put(k, v);
            DelayedItem<K> tmpItem = new DelayedItem<K>(k, liveTime);
            if (v2 != null) {
                queue.remove(tmpItem);
            }
            queue.put(tmpItem);
        }

        public Cache() {
            Thread t = new Thread() {
                @Override
                public void run() {
                    dameonCheckOverdueKey();
                }
            };
            t.setDaemon(true);
            t.start();
        }

        public void dameonCheckOverdueKey() {
            while (true) {
                DelayedItem<K> delayedItem = queue.poll();
                if (delayedItem != null) {
                    map.remove(delayedItem.getT());
                    System.out.println(System.nanoTime() + " remove " + delayedItem.getT() + " from cache");
                }
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

        static class DelayedItem<T> implements Delayed {

            private T t;
            private long liveTime;
            private long removeTime;

            public DelayedItem(T t, long liveTime) {
                this.setT(t);
                this.liveTime = liveTime;
                this.removeTime = TimeUnit.NANOSECONDS.convert(liveTime, TimeUnit.NANOSECONDS) + System.nanoTime();
            }

            @Override
            public int compareTo(Delayed o) {
                if (o == null)
                    return 1;
                if (o == this)
                    return 0;
                if (o instanceof DelayedItem) {
                    DelayedItem<T> tmpDelayedItem = (DelayedItem<T>) o;
                    if (liveTime > tmpDelayedItem.liveTime) {
                        return 1;
                    } else if (liveTime == tmpDelayedItem.liveTime) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
                long diff = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
                return diff > 0 ? 1 : diff == 0 ? 0 : -1;
            }

            @Override
            public long getDelay(TimeUnit unit) {
                return unit.convert(removeTime - System.nanoTime(), unit);
            }

            public T getT() {
                return t;
            }

            public void setT(T t) {
                this.t = t;
            }

            @Override
            public int hashCode() {
                return t.hashCode();
            }

            @Override
            public boolean equals(Object object) {
                if (object instanceof DelayedItem) {
                    return object.hashCode() == hashCode() ? true : false;
                }
                return false;
            }
        }
    }

    static class Student implements Runnable, Delayed {

        private String name;
        public long workTime;
        private long submitTime;
        private boolean isForce = false;

        public Student() {
        }

        public Student(String name, long workTime) {
            this.name = name;
            this.workTime = workTime;
            this.submitTime = TimeUnit.NANOSECONDS.convert(workTime, TimeUnit.NANOSECONDS) + System.nanoTime();// 纳秒级别
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == null || !(o instanceof Student))
                return 1;
            if (o == this)
                return 0;
            Student s = (Student) o;
            if (this.workTime > s.workTime) {
                return 1;
            } else if (this.workTime == s.workTime) {
                return 0;
            } else {
                return -1;
            }
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public void run() {
            if (isForce) {
                System.out.println(name + " 交卷，实际用时 120分钟");
            } else {
                System.out.println(name + " 交卷," + "实际用时 " + workTime + " 分钟");
            }
        }

        public boolean isForce() {
            return isForce;
        }

        public void setForce(boolean isForce) {
            this.isForce = isForce;
        }

    }

    static class Teacher implements Runnable {
        private int counter = 20;
        private DelayQueue<Student> students;

        public Teacher(DelayQueue<Student> students) {
            this.students = students;
        }

        @Override
        public void run() {
            try {
                System.out.println(" test start");
                while (counter > 0) {
                    Student student = students.take();
                    if (student.workTime < 120) {
                        student.run();
                        if (counter > 0) {
                            counter--;
                        }
                    } else {
                        System.out.println(" 考试时间到，全部交卷！");
                        Student tmpStudent;
                        for (Iterator<Student> iterator2 = students.iterator(); iterator2.hasNext(); ) {
                            tmpStudent = iterator2.next();
                            tmpStudent.setForce(true);
                            tmpStudent.run();
                            if (counter > 0) {
                                counter--;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 生产者
    public static class Producer implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private volatile boolean flag;
        private Random random;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
            flag = false;
            random = new Random();

        }

        public void run() {
            while (!flag) {
                int info = random.nextInt(100);
                try {
                    blockingQueue.put(info);
                    System.out.println(Thread.currentThread().getName() + " produce " + info);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void shutDown() {
            flag = true;
        }
    }

    // 消费者
    public static class Consumer implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private volatile boolean flag;

        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void run() {
            while (!flag) {
                int info;
                try {
                    info = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " consumer " + info);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void shutDown() {
            flag = true;
        }
    }
}
