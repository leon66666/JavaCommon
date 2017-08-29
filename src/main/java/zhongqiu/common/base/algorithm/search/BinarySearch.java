package zhongqiu.common.base.algorithm.search;

/**
 * Created by wangzhongqiu on 2017/8/17.
 * 二分查找，时间复杂度为O(logn)
 * 元素必须是有序的，如果是无序的则要先进行排序操作。
 * 折半查找的前提条件是需要有序表顺序存储，对于静态查找表，一次排序后不再变化，折半查找能得到不错的效率。
 * 但对于需要频繁执行插入或删除操作的数据集来说，维护有序的排序会带来不小的工作量，那就不建议使用。
 */
public class BinarySearch {
    public static void main(String[] args) {
        int columns = 5;
        int[] array = new int[columns];
        for (Integer i = 0; i < columns; i++) {
            array[i] = i;
        }
        boolean result = binarySearchAdapt(array, 4, 0, array.length - 1);
        System.out.print(result);
    }

    //二分查找（折半查找）
    public static boolean binarySearch(int a[], int value, int low, int high) {
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] == value)
                return true;
            if (a[mid] > value)
                high = mid - 1;
            if (a[mid] < value)
                low = mid + 1;
        }
        return false;
    }

    //二分查找，递归版本
    public static int binarySearchRecursion(int a[], int value, int low, int high) {
        int mid = (low + high) / 2;
        if (a[mid] == value)
            return mid;
        else if (a[mid] > value)
            return binarySearchRecursion(a, value, low, mid - 1);
        else
            return binarySearchRecursion(a, value, mid + 1, high);
    }

    //差值二分查找，自适应
    //对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。
    //反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择。
    public static boolean binarySearchAdapt(int a[], int value, int low, int high) {
        int mid;
        while (low <= high) {
            mid = low + (value - a[low]) / (a[high] - a[low]) * (high - low);
            if (a[mid] == value)
                return true;
            if (a[mid] > value)
                high = mid - 1;
            if (a[mid] < value)
                low = mid + 1;
        }
        return false;
    }
}
