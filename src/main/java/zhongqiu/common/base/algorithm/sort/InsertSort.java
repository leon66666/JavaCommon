package zhongqiu.common.base.algorithm.sort;

/**
 * Created by wangzhongqiu on 2017/8/9.
 * 插入排序
 * 入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，
 * 从而得到一个新的、个数加一的有序数据，算法适用于少量数据的排序，
 * 时间复杂度为O(n^2)。是稳定的排序方法。
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        insertSort(a, 0, a.length - 1);
        for (int o : a) {
            System.out.print(o + ",");
        }
    }

    public static void insertSort(int[] arr, int low, int high) {
        int i, j, target, n = arr.length;
        //假定第一个元素被放到了正确的位置上,这样，仅需遍历1 - n-1
        for (i = low + 1; i < high + 1; i++) {
            j = i;
            target = arr[j];
            while (j > 0 && target < arr[j - 1]) {
                arr[--j] = arr[j - 1];
            }
            arr[j] = target;
        }
    }
}
