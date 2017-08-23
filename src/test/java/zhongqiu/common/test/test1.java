package zhongqiu.common.test;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int min = -1;
        int sum = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            min = arr[j];
            sum = min;
            if (min * sum > max) {
                max = min * sum;
            }
            while (j + 1 < arr.length) {
                j++;
                sum = sum + arr[j];
                if (arr[j] < min) {
                    min = arr[j];
                }
                if (min * sum > max) {
                    max = min * sum;
                }
            }
        }
        System.out.println(max);
        scanner.close();
    }
}
