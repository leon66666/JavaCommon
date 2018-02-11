package zhongqiu.common.jdk5.concurrent.atomic;

import javafx.beans.binding.ObjectExpression;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author wangzhongqiu
 * @date 2018/2/9.
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        atomicIntegerArray.getAndIncrement(1);

        Object[] objects={new Object(),new Object()};
        AtomicReferenceArray atomicReferenceArray=new AtomicReferenceArray(objects);
        atomicReferenceArray.compareAndSet(1,new Object(),new Object());
    }

}
