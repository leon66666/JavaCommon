package zhongqiu.common.base.algorithm.dynamicplanning;

/**
 * Created by wangzhongqiu on 2017/8/30.
 * 01背包问题。
 * F[i,v]表示前i件物品恰放入一个容量为v的背包可以获得的最大价值。状态转移方程：F[i,v]=max{F[i−1,v],F[i−1,v−Ci]+Wi}
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] weight = {3, 5, 2, 6, 4}; //物品重量
        int[] val = {4, 4, 3, 5, 3}; //物品价值
        int m = 12; //背包容量
//        knapsack01ByTwoDimensionalArray(m, weight, val);
        knapsack01(m, weight, val, true);
    }

    /**
     * 01背包。每个物品最多放一个。恰好装满背包
     *
     * @param m      背包容量
     * @param weight 物品重量
     * @param val    物品价值
     *               主要思想，利用动态规划来解决。每次遍历到的第i个物品，根据w[i]和v[i]来确定是否需要将该物品放入背包中。
     *               即对于给定的n个物品，设v[i]、w[i]分别为第i个物品的价值和重量，C为背包的容量。
     *               再令v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值。则我们有下面的结果：
     *               （1）v[i][0]=v[0][j]=0;
     *               （2）v[i][j]=v[i-1][j]   当w[i]>j
     *               （3）v[i][j]=max{v[i-1][j],v[i-1][j-w[i]]+v[i]}  当j>=w[i]
     */
    public static void knapsack01ByTwoDimensionalArray(int m, int[] weight, int[] val) {
        int n = val.length; //物品个数
        int[][] f = new int[n + 1][m + 1]; //f[i][j]表示前i个物品能装入容量为j的背包中的最大价值
        int[][] path = new int[n + 1][m + 1];//标记
        //初始化第一列和第一行
        for (int i = 0; i < f.length; i++) {
            f[i][0] = 0;
        }
        for (int i = 0; i < f[0].length; i++) {
            f[0][i] = 0;
        }
        //通过公式迭代计算
        for (int i = 1; i < f.length; i++) {
            for (int j = 1; j < f[0].length; j++) {
                if (weight[i - 1] > j)
                    f[i][j] = f[i - 1][j];
                else {
                    if (f[i - 1][j] < f[i - 1][j - weight[i - 1]] + val[i - 1]) {
                        f[i][j] = f[i - 1][j - weight[i - 1]] + val[i - 1];
                        path[i][j] = 1;
                    } else {
                        f[i][j] = f[i - 1][j];
                    }
                }
            }
        }
        for (int i = 1; i < f.length; i++) {
            for (int j = 1; j < f[0].length; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }
        int i = f.length - 1;
        int j = f[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个物品装入 ");
                j -= weight[i - 1];
            }
            i--;
        }
        System.out.print("最大价值:" + f[f.length - 1][f[0].length - 1]);
    }

    /**
     * 一维数组实现，更节省空间
     *
     * @param m        背包容量
     * @param weight   物品重量
     * @param val      物品价值
     * @param needFull 是否需要装满
     */
    public static void knapsack01(int m, int[] weight, int[] val, boolean needFull) {
        int n = val.length; //物品个数
        int[] f = new int[m + 1];//f[i]代表背包容量为i时的最大价值。
        int[] path = new int[m + 1];//标记
        //初始化
        if (needFull) {
            for (int i = 1; i < f.length; i++) {     //必装满则f[0]=0,f[1...m]都初始化为无穷小
                f[i] = Integer.MIN_VALUE;
            }
        } else {
            for (int i = 0; i < f.length; i++) {     //不必装满则初始化为0
                f[i] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = f.length - 1; j >= weight[i]; j--) {
                if (f[j] < f[j - weight[i]] + val[i]) {
                    f[j] = f[j - weight[i]] + val[i];
//                    path[]
                }
//                f[j] = Math.max(f[j], f[j - weight[i]] + val[i]);
            }
            for (int k = 0; k < f.length; k++) {
                System.out.print(f[k] + " ，");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("最大价值为" + f[f.length - 1]);
    }
}
