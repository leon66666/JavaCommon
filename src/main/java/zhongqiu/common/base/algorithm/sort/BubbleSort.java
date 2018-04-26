package zhongqiu.common.base.algorithm.sort;

/**
 * Created by wangzhongqiu on 2017/8/15.
 * 冒泡排序：它重复地走访过要排序的数列，一次比较两个元素，
 * 如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 时间复杂度为O(n^2)，是一种稳定的排序算法
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
