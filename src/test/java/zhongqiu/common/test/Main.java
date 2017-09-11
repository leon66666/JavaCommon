package zhongqiu.common.test;
/**
 * 赛码网例题
 */


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = sc.nextInt();

        }
        int kg = sc.nextInt();
        System.out.println(solve(array, kg));
    }

    public static int solve(int[] array, int kg) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            if (max > array.length - i) return max;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum % kg == 0) {
                    if (max < j - i + 1) max = j - i + 1;
                }
            }
        }
        return max;
    }

}