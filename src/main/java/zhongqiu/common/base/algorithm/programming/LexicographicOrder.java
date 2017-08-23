package zhongqiu.common.base.algorithm.programming;

import java.util.Scanner;

/**
 * Created by wangzhongqiu on 2017/8/23.
 * 头条：求一串数字的字典序
 */
public class LexicographicOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] array = new int[n];
        int num = 0;
        int flag = 0;
        int sonFlag = 0;
        boolean canContinue = true;
        for (int i = 1; i < n + 1 && i < 10; i++) {
            flag = i;
            while (flag <= n) {
                sonFlag = flag;
                while (num < n && sonFlag <= n) {
                    if (flag >= 10) {
                        canContinue = sonFlag < flag + flag / i;
                    } else {
                        canContinue = sonFlag == flag;
                    }
                    if (!canContinue) {
                        break;
                    }
                    array[num] = sonFlag;
                    num++;
                    sonFlag++;
                }
                flag = flag * 10;
            }
        }
        System.out.println(array[m - 1]);
        scanner.close();
    }
}
