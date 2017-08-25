package zhongqiu.common.test;
/*
 斐波那契数列
* */
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(Fibonacci(n));
    }

    public static int Fibonacci(int n) {
        if (n < 3) {
            return 1;
        } else {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }
}
