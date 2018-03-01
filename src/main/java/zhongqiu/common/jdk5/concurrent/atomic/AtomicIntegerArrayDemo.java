package zhongqiu.common.jdk5.concurrent.atomic;

import javafx.beans.binding.ObjectExpression;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author wangzhongqiu
 * @date 2018/2/9.
 */
public class AtomicIntegerArrayDemo {
    public volatile int[] a = {1, 2};

    public static void main(String[] args) {
        System.out.println(31 - Integer.numberOfLeadingZeros(8));
        System.out.println(32 - Integer.numberOfLeadingZeros(8 - 1));
        int[] array = {1, 2, 3, 4};
        long[] longArray = {1, 2, 3, 4};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        atomicIntegerArray.getAndIncrement(1);

        AtomicLongArray atomicLongArray = new AtomicLongArray(longArray);
        atomicLongArray.getAndIncrement(1);

        Object[] objects = {new Object(), new Object()};
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(objects);
        atomicReferenceArray.getAndSet(1, new Object());
    }

}
