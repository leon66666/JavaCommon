package zhongqiu.common.base.thread;

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

    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while (true) {
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
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new TestSimpleDateFormatThreadSafe().start();
        }
    }
}
