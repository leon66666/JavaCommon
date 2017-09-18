package zhongqiu.common.test;
/**
 * 赛码网例题
 */


import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private static final ThreadLocal<Integer> threadLocal1 = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        Integer integer = 111;
        threadLocal.set(integer);
        threadLocal1.set(integer);
        Thread thread = Thread.currentThread();
        Date now=new Date("20170102");
    }
}