package zhongqiu.common.base.algorithm.sort;

/**
 * Created by wangzhongqiu on 2017/8/9.
 * 插入排序
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
}
