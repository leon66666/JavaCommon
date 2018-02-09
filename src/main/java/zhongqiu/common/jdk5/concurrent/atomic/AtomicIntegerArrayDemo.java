package zhongqiu.common.jdk5.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author wangzhongqiu
 * @date 2018/2/9.
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        atomicIntegerArray.getAndIncrement(0);

    }
}
