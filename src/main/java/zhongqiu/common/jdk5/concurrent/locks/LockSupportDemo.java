package zhongqiu.common.jdk5.concurrent.locks;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangzhongqiu
 * @date 2018/2/1.
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());
    }
}
