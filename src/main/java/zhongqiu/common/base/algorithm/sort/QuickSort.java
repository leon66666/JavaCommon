package zhongqiu.common.base.algorithm.sort;

import zhongqiu.common.base.MathRandomDemo;

import java.util.Date;
import java.util.Random;

/**
 * Created by wangzhongqiu on 2017/8/2.
 * 快速排序，是不稳定的排序
 */
public class QuickSort {
    public static void main(String[] args) throws InterruptedException {
        //生成准备数据
        int n = 100000;
//        int[] arr = MathRandomDemo.getRandomIntArrayWithRepeat(n);
//        int[] arr = MathRandomDemo.getRandomIntArrayWithoutRepeat(0, n - 1, n);
//        int[] arr = MathRandomDemo.getIntArrayAllRepeat(n, 10);
//        int[] arr = MathRandomDemo.getIntArrayAsc(n);
        int[] arr = MathRandomDemo.getIntArrayDesc(n);
        int[] brr = new int[n];
        for (int i = 0; i < n; i++) {
            brr[i] = arr[i];
        }
        QuickSort quickSort_one = new QuickSort();
        QuickSort quickSort_two = new QuickSort();

        Thread.sleep(3000);
        //开始计时，排序开始
        Date arrBegin = new Date();
        quickSort_one.sort_one(arr, 0, arr.length - 1);
        Date arrEnd = new Date();
        System.out.println("sort_one总运行时间:" + (arrEnd.getTime() - arrBegin.getTime()) + "毫秒");
        System.out.println("sort_one总运行次数:" + quickSort_one.getNum());
//        quickSort_one.print(arr);


        Thread.sleep(3000);
        //开始计时，排序开始
        Date brrBegin = new Date();
        quickSort_two.sort_five(brr, 0, brr.length - 1);
        Date brrEnd = new Date();
        System.out.println("sort_two总运行时间:" + (brrEnd.getTime() - brrBegin.getTime()) + "毫秒");
        System.out.println("sort_two总运行次数:" + quickSort_two.getNum());
//        quickSort_two.print(brr);
    }

    //互换写法
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
            sort_one(arr, low, l - 1);
            sort_one(arr, l + 1, high);
        }
    }

    //不需要互换的写法,不需要创建大量的临时变量
    //优化递归操作
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
            if (l > low) sort_two(arr, low, l - 1); //优化递归操作
            if (h < high) sort_two(arr, l + 1, high);//优化递归操作
        }
    }

    //不需要互换的写法,不需要创建大量的临时变量。
    //三数取中选取枢轴。
    //优化递归操作。
    void sort_three(int[] arr, int low, int high) {
        num++;
        if (low < high) {
            int l = low, h = high;
            int pivot = SelectPivotMedianOfThree(arr, l, h);
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
            if (l > low) sort_three(arr, low, l - 1);//优化递归操作
            if (h < high) sort_three(arr, l + 1, high);//优化递归操作
        }
    }

    //不需要互换的写法,不需要创建大量的临时变量。
    //三数取中选取枢轴。
    //优化递归操作。
    //长度小于阈值采用插入排序。对于很小和部分有序的数组，快排不如插排好。当待排序序列的长度分割到一定大小后，继续分割的效率比插入排序要差，此时可以使用插排而不是快排
    void sort_four(int[] arr, int low, int high) {
        num++;
        if (low < high) {
            if (high - low + 1 < 10) {
                InsertSort.insertSort(arr, low, high);
                return;
            }
            int l = low, h = high;
            int pivot = SelectPivotMedianOfThree(arr, l, h);
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
            if (l > low) sort_four(arr, low, l - 1);//优化递归操作
            if (h < high) sort_four(arr, l + 1, high);//优化递归操作
        }
    }

    //不需要互换的写法,不需要创建大量的临时变量。
    //三数取中选取枢轴。
    //优化递归操作。
    //长度小于阈值采用插入排序。对于很小和部分有序的数组，快排不如插排好。当待排序序列的长度分割到一定大小后，继续分割的效率比插入排序要差，此时可以使用插排而不是快排
    //聚集相等元素
    void sort_five(int[] arr, int low, int high) {
        num++;
        if (low < high) {
            if (high - low + 1 < 10) {
                InsertSort.insertSort(arr, low, high);
                return;
            }

            int l = low;
            int h = high;

            int left = low;
            int right = high;

            int leftLen = 0;
            int rightLen = 0;

            int pivot = SelectPivotMedianOfThree(arr, l, h);

            while (l < h) {
                // 从右向左找第一个小于x的数
                while (l < h && arr[h] >= pivot) {
                    if (arr[h] == pivot)//处理相等元素
                    {
                        swap(arr, right, h);
                        right--;
                        rightLen++;
                    }
                    h--;
                }
                if (l < h) {
                    arr[l] = arr[h];
                    l++;
                }
                // 从左向右找第一个大于等于x的数
                while (l < h && arr[l] < pivot) {
                    if (arr[l] == pivot) {
                        swap(arr, left, l);
                        left++;
                        leftLen++;
                    }
                    l++;
                }
                if (l < h) {
                    arr[h] = arr[l];
                    h--;
                }
            }
            arr[l] = pivot;

            //一次快排结束
            //把与枢轴key相同的元素移到枢轴最终位置周围
            int i = l - 1;
            int j = low;
            while (j < left && arr[i] != pivot) {
                swap(arr, i, j);
                i--;
                j++;
            }
            i = l + 1;
            j = high;
            while (j > right && arr[i] != pivot) {
                swap(arr, i, j);
                i++;
                j--;
            }

            if (l - leftLen > low) sort_five(arr, low, l - leftLen - 1);//优化递归操作
            if (h + rightLen < high) sort_five(arr, l + rightLen + 1, high);//优化递归操作
        }
    }

    /*函数作用：取待排序序列中low、mid、high三个位置上数据，选取他们中间的那个数据作为枢轴*/
    public int SelectPivotMedianOfThree(int[] arr, int low, int high) {
        int mid = low + ((high - low) >> 1);//计算数组中间的元素的下标
        //使用三数取中法选择枢轴
        if (arr[mid] > arr[high])//目标: arr[mid] <= arr[high]
        {
            swap(arr, mid, high);
        }
        if (arr[low] > arr[high])//目标: arr[low] <= arr[high]
        {
            swap(arr, low, high);
        }
        if (arr[mid] > arr[low]) //目标: arr[low] >= arr[mid]
        {
            swap(arr, mid, low);
        }
        //此时，arr[mid] <= arr[low] <= arr[high]
        return arr[low];
        //low的位置上保存这三个位置中间的值
        //分割时可以直接使用low位置的元素作为枢轴，而不用改变分割函数了
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

    //交换数组两个下标的值
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
