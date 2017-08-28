package zhongqiu.common.base.algorithm.programming;

import java.util.Scanner;

/**
 * Created by wangzhongqiu on 2017/8/28.
 */
public class Fibonacci {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(Fibonacci(n));
    }

    public static int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n < 3) {
            return 1;
        } else {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }
}
