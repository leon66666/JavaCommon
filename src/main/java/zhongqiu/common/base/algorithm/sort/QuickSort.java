package zhongqiu.common.base.algorithm.sort;

import zhongqiu.common.base.MathRandomDemo;

import java.util.Date;
import java.util.Random;

/**
 * Created by wangzhongqiu on 2017/8/2.
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        //生成准备数据
        QuickSort quickSort = new QuickSort();
        int n=100000;
//        int[] arr = MathRandomDemo.getRandomIntArrayWithRepeat(n);
        int[] arr = MathRandomDemo.getRandomIntArrayWithoutRepeat(0,n-1, n);
        int[] brr = arr;
//        quickSort.print(arr);

        //开始计时，排序开始
        Date arrBegin = new Date();
        quickSort.sort_one(arr, 0, arr.length - 1);
        Date arrEnd = new Date();
        System.out.println("sort_one总运行时间:" + (arrEnd.getTime() - arrBegin.getTime()) + "毫秒");
        System.out.println("sort_one总运行次数:" + quickSort.getNum());
//        quickSort.print(arr);

        //开始计时，排序开始
        Date brrBegin = new Date();
        quickSort.sort_two(brr, 0, arr.length - 1);
        Date brrEnd = new Date();
        System.out.println("sort_two总运行时间:" + (brrEnd.getTime() - brrBegin.getTime()) + "毫秒");
        System.out.println("sort_two总运行次数:" + quickSort.getNum());
//        quickSort.print(brr);
    }

    //经典写法
    public void sort_one(int[] arr, int low, int high) {
        num++;
        if (low < high) {
            int l = low;
            int h = high;
            int pivot = arr[low];
            while (l < h) {
                while (l < h && arr[h] >= pivot)
                    h--;
                if (l < h) {
                    int temp = arr[h];
                    arr[h] = arr[l];
                    arr[l] = temp;
                    l++;
                }

                while (l < h && arr[l] <= pivot)
                    l++;

                if (l < h) {
                    int temp = arr[h];
                    arr[h] = arr[l];
                    arr[l] = temp;
                    h--;
                }
            }
//            print(arr);
//            System.out.print("l=" + l + ",low=" + low + ",h=" + h + ",high=" + high + ",pivot=" + pivot + "\n");
            sort_one(arr, low, l - 1);
            sort_one(arr, l + 1, high);
        }
    }

    //优化之后的快速排序算法，减少执行次数，不用每次都互换值
    void sort_two(int[] arr, int low, int high) {
        num++;
        if (low < high) {
            int l = low, h = high, pivot = arr[low];
            while (l < h) {
                while (l < h && arr[h] >= pivot) // 从右向左找第一个小于x的数
                    h--;
                if (l < h) {
                    arr[l] = arr[h];
                    l++;
                }
                while (l < h && arr[l] < pivot) // 从左向右找第一个大于等于x的数
                    l++;
                if (l < h) {
                    arr[h] = arr[l];
                    h--;
                }
            }
            arr[l] = pivot;
            if (l > low) sort_two(arr, low, l - 1); // 递归调用
            if (h < high) sort_two(arr, l + 1, high);
        }
    }


    private Integer num = 0;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println("");
    }
}
