package zhongqiu.common.test;

import javafx.beans.binding.ObjectExpression;

/**
 * Created by wangzhongqiu on 2017/9/8.
 */
public class test4 {
    static int cnt = 0;

    public static void main(String[] args) {
        Other o = new Other();
        new test4().addOne(o);
        System.out.println(o.i);
    }

    public void addOne(final Other o) {
        o.i++;
    }
}

class Other {
    public int i;
}