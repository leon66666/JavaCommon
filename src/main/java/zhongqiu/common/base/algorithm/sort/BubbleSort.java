package zhongqiu.common.base.algorithm.sort;

/**
 * Created by wangzhongqiu on 2017/8/1.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {10, 8, 4, 7, 34, 12, 367, 21};
        sort(a);
        for (int o : a) {
            System.out.print(o + ",");
        }
    }

    public static void sort(int[] a) {
        int temp = 0;
        for (int i = a.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (a[j + 1] < a[j]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void sortNew(int[] a) {
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }
}
