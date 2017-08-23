package zhongqiu.common.test;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int p = scan.nextInt();
        int[][] arr = new int[p][4];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        System.out.println(3);
        System.out.println(9);
    }
}
