package zhongqiu.common.jdk5.concurrent.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author wangzhongqiu
 * @date 2018/2/9.
 */
public class AtomicBooleanDemo {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.getAndSet(true);
    }

}
