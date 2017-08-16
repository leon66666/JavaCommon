package zhongqiu.common.base.algorithm.sort;

import zhongqiu.common.utils.CommonUtils;

import java.util.Arrays;

/**
 * Created by wangzhongqiu on 2017/8/15.
 * 堆排序，不稳定排序，时间复杂度为O(nlogn)。堆排序是一种选择排序
 * 思想：二叉堆，最大堆（递减），最小堆（递增）。
 * 堆的存储:一般都用数组来表示堆，i结点的父结点下标就为(i – 1) / 2。它的左右子结点下标分别为2 * i + 1和2 * i + 2。
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] a = {9, 12, 17, 30, 50, 20, 60, 65, 4, 49};
        HeapSort heapSort = new HeapSort();
        heapSort.HeapSort(a, a.length-1);
        System.out.print(Arrays.toString(a));
    }

    //在最小堆中加入新的数据nNum
    public void MinHeapAddNumber(int a[], int n, int nNum) {
        a[n] = nNum;
        MinHeapFixup(a, n);
    }

    //  新加入i结点  其父结点为(i - 1) / 2
    public void MinHeapFixup(int a[], int i) {
        int j, temp;

        temp = a[i];
        j = (i - 1) / 2;      //父结点
        while (j >= 0 && i != 0 && a[j] > temp) {
            a[i] = a[j];     //把较大的子结点往下移动,替换它的子结点
            i = j;
            j = (i - 1) / 2;
        }
        a[i] = temp;
    }

    //  从i节点开始调整,n为节点总数 从0开始计算 i节点的子节点为 2*i+1, 2*i+2
    void MinHeapFixdown(int a[], int i, int n) {
        int j, temp;

        temp = a[i];
        j = 2 * i + 1;
        while (j < n) {
            if (j + 1 < n && a[j + 1] < a[j]) //在左右孩子中找最小的
                j++;

            if (a[j] >= temp)
                break;

            a[i] = a[j];     //把较小的子结点往上移动,替换它的父结点
            i = j;
            j = 2 * i + 1;
        }
        a[i] = temp;
    }

    //在最小堆中删除数
    public void MinHeapDeleteNumber(int a[], int n) {
        CommonUtils.swap(a, 0, n - 1);
        MinHeapFixdown(a, 0, n - 1);
    }

    //建立最小堆
    public void MakeMinHeap(int a[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            MinHeapFixdown(a, i, n);
    }

    //最小堆排序
    public void MinheapsortTodescendarray(int a[], int n) {
        for (int i = n - 1; i >= 1; i--) {
            CommonUtils.swap(a, i, 0);
            MinHeapFixdown(a, 0, i);
        }
    }

    void HeapAdjust(int H[], int start, int end)
    {

        int temp = H[start];

        for(int i = 2*start + 1; i<=end; i*=2)
        {
            //因为假设根结点的序号为0而不是1，所以i结点左孩子和右孩子分别为2i+1和2i+2
            if(i<end && H[i]<H[i+1])//左右孩子的比较
            {
                ++i;//i为较大的记录的下标
            }

            if(temp > H[i])//左右孩子中获胜者与父亲的比较
            {
                break;
            }

            //将孩子结点上位，则以孩子结点的位置进行下一轮的筛选
            H[start]= H[i];
            start = i;

        }

        H[start]= temp; //插入最开始不和谐的元素
    }

    void HeapSort(int A[], int n)
    {
        //先建立大顶堆
        for(int i=n/2; i>=0; --i)
        {
            HeapAdjust(A,i,n);
        }
        //进行排序
        for(int i=n-1; i>0; --i)
        {
            //最后一个元素和第一元素进行交换
            int temp=A[i];
            A[i] = A[0];
            A[0] = temp;

            //然后将剩下的无序元素继续调整为大顶堆
            HeapAdjust(A,0,i-1);
        }

    }
}
