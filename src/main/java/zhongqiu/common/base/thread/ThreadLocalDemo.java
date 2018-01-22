package zhongqiu.common.base.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private static final ThreadLocal<Integer> threadLocal1 = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        Integer integer = 111;
        threadLocal.set(integer);
        threadLocal1.set(integer);
        Thread thread = Thread.currentThread();
        Date now = new Date("20170102");
    }
}
