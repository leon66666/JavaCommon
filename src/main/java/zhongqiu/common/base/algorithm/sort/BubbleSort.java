package zhongqiu.common.base.algorithm.sort;

/**
 * Created by wangzhongqiu on 2017/8/1.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(a);
        for (int o : a) {
            System.out.print(o + ",");
        }
    }

    public static void bubbleSort(int[] a) {
        int temp = 0;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
