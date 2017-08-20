package zhongqiu.common.test;

import java.util.Scanner;

/**
 * Created by wangzhongqiu on 2017/8/14.
 */
public class test_three {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int total = sc.nextInt();
        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = sc.nextInt();
        }
    }
}
