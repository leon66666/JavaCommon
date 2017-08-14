package zhongqiu.common.test;


import java.util.Scanner;

/**
 * Created by wangzhongqiu on 2017/8/6.
 */
public class test_one {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = sc.nextInt();
        }
    }
}
