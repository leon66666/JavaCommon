package zhongqiu.common.base.algorithm;

import java.util.Scanner;

/**
 * Created by wangzhongqiu on 2017/8/6.
 * 题目：疯狂队列
 * 出处：网易2018校招
 * 来源：牛客网
 * 测试结果：90%测试用例通过
 */
public class CrazyQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = sc.nextInt();
        }
        insertSort(arr, 0, arr.length - 1);
        int[] newArray = create(arr);
        int num = calculate(newArray);
        System.out.println(num);
    }

    public static void insertSort(int[] arr, int low, int high) {
        int i, j;
        int n = arr.length;
        int target;
        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 - n-1
        for (i = low + 1; i < high + 1; i++) {
            j = i;
            target = arr[j];
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = target;
        }
    }

    public static int[] create(int[] arr) {
        int[] newArray = new int[arr.length];
        int i = 0;
        int j = arr.length - 1;
        int newI = 0;
        int m = (i + j) / 2;
        newArray[m] = arr[j];
        j--;
        int num = 0;
        while (i <= j) {
            switch (num) {
                case 0:
                    newArray[m + newI + 1] = arr[i];
                    i++;
                    num++;
                    break;
                case 1:
                    newArray[m - newI - 1] = arr[i];
                    i++;
                    newI = newI + 1;
                    num++;
                    break;
                case 2:
                    newArray[m + newI + 1] = arr[j];
                    num++;
                    j--;
                    break;
                case 3:
                    newArray[m - newI - 1] = arr[j];
                    j--;
                    newI = newI + 1;
                    num++;
                    break;
            }
            if (num == 4) {
                num = 0;
            }
        }
        return newArray;
    }

    public static int calculate(int[] arr) {
        int num = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] >= 0) {
                num = num + arr[i + 1] - arr[i];
            } else {
                num = num + arr[i] - arr[i + 1];
            }
        }
        return num;
    }
}
