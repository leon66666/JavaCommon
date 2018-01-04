package zhongqiu.common.test;

/**
 * Created by wangzhongqiu on 2017/9/8.
 */
public class test5 {
    public static void main(String[] args) {
        AAA aaa = new AAA();
        BBB bbb = aaa.getBbb();
        bbb.setI(2);
        System.out.println(bbb.i);
        System.out.println(aaa.getBbb().i);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                str[0] = "qwer";
//            }
//        });
    }
}

class AAA {
    private BBB bbb = new BBB();

    public BBB getBbb() {
        return bbb;
    }
}

class BBB {
    public int i;

    public void setI(int j) {
        i = i + j;
    }
}
