package zhongqiu.common.jdk5.concurrent.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author wangzhongqiu
 * @date 2018/2/27.
 */
public class AtomicMarkableReferenceDemo {
    public static void main(String[] args) {
        Integer a = 1;
        AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(a, false);
        atomicMarkableReference.compareAndSet(1, 2, false, true);
    }

}
