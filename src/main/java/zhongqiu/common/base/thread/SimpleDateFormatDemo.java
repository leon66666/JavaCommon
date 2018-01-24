package zhongqiu.common.base.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangzhongqiu
 * @date 2018/1/23.
 */
public class SimpleDateFormatDemo {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) throws ParseException {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {

        return sdf.parse(strDate);
    }

    public static class SimpleDateFormatTest extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 100) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName() + ":" + parse("2013-05-24 06:02:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    public static class ThreadLocalDateUtilTest extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 100) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName() + ":" + ThreadLocalDateUtil.parse("2013-05-24 06:02:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 3; i++) {
//            new SimpleDateFormatTest().start();
//        }
        for (int i = 0; i < 3; i++) {
            new ThreadLocalDateUtilTest().start();
        }
    }
}

//性能较好的解决方案，threadlocal
class ThreadLocalDateUtil {
    private static final String date_format = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    public static DateFormat getDateFormat() {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(date_format);
            threadLocal.set(df);
        }
        return df;
    }

    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }
}