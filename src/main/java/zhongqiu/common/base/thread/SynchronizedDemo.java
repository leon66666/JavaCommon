package zhongqiu.common.base.thread;

//多线程资源同步
/*
1、synchronized关键字的作用域有二种：
1）是某个对象实例内，synchronized aMethod(){}可以防止多个线程同时访问这个对象的synchronized方法
      （如果一个对象有多个synchronized方法，只要一个线程访问了其中的一个synchronized方法，
       其它线程不能同时访问这个对象中任何一个synchronized方法）。
       这时，不同的对象实例的synchronized方法是不相干扰的。
       也就是说，其它线程照样可以同时访问相同类的另一个对象实例中的synchronized方法；
2）是某个类的范围，synchronized static aStaticMethod{}防止多个线程同时访问这个类中的synchronized static 方法。它可以对类的所有对象实例起作用。
2、除了方法前用synchronized关键字，synchronized关键字还可以用于方法中的某个区块中，
   表示只对这个区块的资源实行互斥访问。用法是: synchronized(this){区块}，它的作用域是当前对象；
3、synchronized关键字是不能继承的，也就是说，
   基类的方法synchronized f(){} 在继承类中并不自动是synchronized f(){}，
   而是变成了f(){}。继承类需要你显式的指定它的某个方法为synchronized方法；
*/
public class SynchronizedDemo implements Runnable {
    public static void main(String[] args) {
        test1();
//		test2();
//        test3();
    }

    // synchronized(this)同步代码块
    public void run() {
//        System.out.println("线程" + Thread.currentThread().getName() + "申请获得syn的锁");
//        synchronized (this) {
//            System.out.println("线程" + Thread.currentThread().getName() + "获得了syn的锁");
//            System.out.println("线程" + Thread.currentThread().getName() + "休眠5秒");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("线程" + Thread.currentThread().getName() + "睡醒了");
//            for (int i = 0; i < 200; i++) {
//                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
//            }
//        }

        System.out.println("线程" + Thread.currentThread().getName() + "申请获得syn的方法锁");
        print();
    }

    public synchronized static void print() {
        System.out.println("线程" + Thread.currentThread().getName() + "获得了syn的方法锁");
        System.out.println("线程" + Thread.currentThread().getName() + "休眠5秒");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "睡醒了");
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }

    // synchronized(this)同步代码块
    public void SYN() {
        System.out.println("线程" + Thread.currentThread().getName() + "申请获得syn的锁");
        synchronized (this) {
            System.out.println("线程" + Thread.currentThread().getName() + "获得了syn的锁");
            for (int i = 0; i < 200; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    // 非synchronized(this)同步代码块
    public void notSYN() {
        System.out.println("对象非synchronized(this)同步代码块不受锁的影响");
        System.out.println("线程" + Thread.currentThread().getName() + "继续使用对象syn的其他非synchronized(this)方法");
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }

    /*
     * 当有一个明确的对象作为锁时，就可以这样写程序，但当没有明
     *
     * 确的对象作为锁，只是想让一段代码同步时，可以创建一个特殊的instance变量（它得是一个对象）来充当锁：
     */
    /*
     * 注：零长度的byte数组对象创建起来将比任何对象都经济――查看编译后的字节码：生成零长度的byte[]对象只需3条操作码，而Object lock
	 *
	 * = new Object()则需要7行操作码。
	 */
    private byte[] lock1 = new byte[0]; // 特殊的instance变量

    void methodA() {
        synchronized (lock1) {
            for (int i = 0; i < 200; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    /*
     * 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，
     * 一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
     */
    public static void test1() {
//		SynchronizedDemo syn = new SynchronizedDemo();
//		Thread ta = new Thread(syn, "A");
//		Thread tb = new Thread(syn, "B");
//		ta.start();
//		tb.start();

        SynchronizedDemo syna = new SynchronizedDemo();
        SynchronizedDemo synb = new SynchronizedDemo();
        Thread ta = new Thread(syna, "A");
        Thread tb = new Thread(synb, "B");
        ta.start();
        tb.start();
    }

    /*
     * 当一个线程访问object的一个synchronized(this)同步代码块时，
     * 另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
     */
    public static void test2() {
        /*
         * final修饰的类不能被继承。 Sting就是一个被final修饰的类，我们只能用，不用继承
		 * final不仅可以修饰类，还可以修饰变量，被final修饰的变量就是一个常量，只能赋值一次
		 */
        final SynchronizedDemo syn = new SynchronizedDemo();
        new Thread(new Runnable() {
            public void run() {
                syn.run();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            public void run() {
                syn.notSYN();
            }
        }, "t2").start();
    }

    /*
     * 当一个线程访问object的一个synchronized(this)同步代码块时，
     * 其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
     * 也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。
     * 结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
     */
    public static void test3() {
        final SynchronizedDemo syn = new SynchronizedDemo();

        new Thread(new Runnable() {
            public void run() {
                syn.run();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            public void run() {
                syn.SYN();
            }
        }, "t2").start();

        new Thread(new Runnable() {
            public void run() {
                syn.methodA();
            }
        }, "t3").start();
    }

}
