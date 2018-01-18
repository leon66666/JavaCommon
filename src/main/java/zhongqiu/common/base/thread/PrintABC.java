package zhongqiu.common.base.thread;

// 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
// 主要考察obj.wait()和obj.notify() 的用法
public class PrintABC extends Thread {
    public static void main(String[] args) throws InterruptedException {
        PrintABC pa = new PrintABC("A");
        PrintABC pb = new PrintABC("B");
        PrintABC pc = new PrintABC("C");
        pa.setWait(pc);
        pb.setWait(pa);
        pc.setWait(pb);

        new Thread(pa).start();
        Thread.sleep(100); // 确保按顺序A、B、C执行
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }

    private String name;
    private PrintABC wait;

    public PrintABC getWait() {
        return wait;
    }

    public void setWait(PrintABC wait) {
        this.wait = wait;
    }

    private PrintABC(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (wait) {
                synchronized (this) {
                    System.out.println(name);
                    count--;
                    // notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，
                    // 自动释放锁后,JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
                    this.notify();
                }
                try {
                    // Thread.sleep()与Object.wait()二者都可以暂停当前线程，释放CPU控制权，
                    // 主要的区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。
                    wait.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
