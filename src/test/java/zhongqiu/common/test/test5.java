package zhongqiu.common.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wangzhongqiu on 2017/9/8.
 */
public class test5 extends ReentrantReadWriteLock{
    public static void main(String[] args) {
        Double a = Double.valueOf(5);
        Double b = Double.valueOf(6);
        System.out.println(a.compareTo(b));
    }
}
