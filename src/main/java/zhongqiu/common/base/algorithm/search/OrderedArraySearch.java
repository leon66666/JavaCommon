package zhongqiu.common.base.algorithm.search;

import java.util.Scanner;

/**
 * Created by wangzhongqiu on 2017/8/17.
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上往下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class OrderedArraySearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        int[][] array = new int[columns][rows];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = sc.nextInt();
            }
        }
        OrderedArraySearch orderedArraySearch = new OrderedArraySearch();
        System.out.print(orderedArraySearch.find(7, array));
    }

    public boolean find(int target, int[][] array) {
        boolean found = false;
        int rows = array.length;
        int columns = array[0].length;
        if (array != null && rows > 0 && columns > 0) {
            int r = 0;
            int c = columns - 1;
            while (r < rows && c >= 0) {
                int temp = array[r][c];
                if (target > temp) {
                    r++;
                } else if (target < temp) {
                    c--;
                } else {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}
