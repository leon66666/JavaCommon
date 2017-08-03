package zhongqiu.common.base.algorithm.sort;

/**
 * Created by wangzhongqiu on 2017/8/2.
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {7, 5, 4, 3, 2, 1};
        QuickSort quickSort = new QuickSort();
//        quickSort.sort_one(arr, 0, arr.length - 1);
        quickSort.sort_two(arr, 0, arr.length - 1);
        quickSort.print(arr);
    }

    private Integer num = 0;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    //经典写法
    public void sort_one(int[] arr, int low, int high) {
        num++;
        System.out.println("执行次数=" + num);
        if (low < high) {
            int l = low;
            int h = high;
            int povit = arr[low];
            while (l < h) {
                while (l < h && arr[h] >= povit)
                    h--;
                if (l < h) {
                    int temp = arr[h];
                    arr[h] = arr[l];
                    arr[l] = temp;
                    l++;
                }

                while (l < h && arr[l] <= povit)
                    l++;

                if (l < h) {
                    int temp = arr[h];
                    arr[h] = arr[l];
                    arr[l] = temp;
                    h--;
                }
            }
            print(arr);
            System.out.print("l=" + l + ",low=" + low + ",h=" + h + ",high=" + high + ",povit=" + povit + "\n");
            sort_one(arr, low, l - 1);
            sort_one(arr, l + 1, high);
        }
    }

    //优化之后的快速排序算法，减少执行次数，不用每次都互换值
    void sort_two(int[] arr, int low, int high) {
        num++;
        System.out.println("执行次数=" + num);
        if (low < high) {
            int l = low, h = high, povit = arr[low];
            while (l < h) {
                while (l < h && arr[h] >= povit) // 从右向左找第一个小于x的数
                    h--;
                if (l < h) {
                    arr[l] = arr[h];
                    l++;
                }
                while (l < h && arr[l] < povit) // 从左向右找第一个大于等于x的数
                    l++;
                if (l < h) {
                    arr[h] = arr[l];
                    h--;
                }
            }
            arr[l] = povit;
            if (l > low) sort_two(arr, low, l - 1); // 递归调用
            if (h < high) sort_two(arr, l + 1, high);
        }
    }

    public void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }
}
