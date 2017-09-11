package zhongqiu.common.test;


import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {

        }
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int first = a[0];
        int index = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > first) {
                first = a[i];
                index = i;
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (i == index)
                continue;
            first -= a[i];
        }

        if (first > 0)
            System.out.println("no");
        else
            System.out.println("yes");
    }
}
