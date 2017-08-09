package zhongqiu.common.test;


/**
 * Created by wangzhongqiu on 2017/8/6.
 */
public class test_one {
    public static void main(String[] args) throws Exception {
        System.out.println(new test_one().test());
    }

    public static int test() throws Exception {
        int x = 1;
        try {
            x++;
            return x;
        } finally {
            ++x;
            throw new Exception("11");
        }
    }
}
