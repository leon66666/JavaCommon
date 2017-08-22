package zhongqiu.common.base.algorithm.programming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by wangzhongqiu on 2017/8/22.
 * 头条2017秋招
 * 头条的2017校招开始了！为了这次校招，我们组织了一个规模宏大的出题团队。每个出题人都出了一些有趣的题目，而我们现在想把这些题目组合成若干场考试出来。在选题之前，我们对题目进行了盲审，并定出了每道题的难度系数。一场考试包含3道开放性题目，假设他们的难度从小到大分别为a, b, c，我们希望这3道题能满足下列条件：
 * a＜= b＜= c
 * b - a＜= 10
 * c - b＜= 10
 * 所有出题人一共出了n道开放性题目。现在我们想把这n道题分布到若干场考试中（1场或多场，每道题都必须使用且只能用一次），然而由于上述条件的限制，可能有一些考试没法凑够3道题，因此出题人就需要多出一些适当难度的题目来让每场考试都达到要求。然而我们出题已经出得很累了，你能计算出我们最少还需要再出几道题吗？
 */
public class TestDistribution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        int res = 0;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            //判断第二个数和第一个数字的差 大于20则跳过（还需要出2道题目）
            if (i + 1 < n && arr[i + 1] - arr[i] > 20) {
                res += 2;
                continue;
                //如果不到与20但是大于10，则还需要出一道题
            } else if (i + 1 < n && arr[i + 1] - arr[i] > 10) {
                res += 1;
                i = i + 1;
                continue;
                //如果在10以内的情况
            } else if (i + 1 < n) {
                //判断第三个数字比第二个数字大多少
                //10以内
                if (i + 2 < n && arr[i + 2] - arr[i + 1] <= 10) {
                    i = i + 2;
                    //10以外
                } else if (i + 2 < n) {
                    res += 1;
                    i = i + 1;
                    //不存在第三个数字
                } else {
                    res += 1;
                    i = i + 1;
                }
            } else {
                //如果没有下一个了就+2
                res += 2;
            }
        }
        System.out.println(res);
    }
}
