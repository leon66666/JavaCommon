package zhongqiu.common.base.algorithm.search;

/**
 * Created by wangzhongqiu on 2017/8/17.
 * 顺序查找，时间复杂度为O(n)
 */
public class SequentialSearch {
    public static void main(String[] args) {
        int columns = 5;
        int rows = 5;
        int[][] array = new int[columns][rows];
        for (Integer i = 0; i < columns; i++) {
            for (Integer j = 0; j < rows; j++) {
                array[i][j] = Integer.parseInt(i.toString() + j.toString());
            }
        }
        boolean result=find(21,array);
        System.out.print(result);
    }

    public static boolean find(int target, int[][] array) {
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                if (array[x][y] == target) return true;
            }
        }
        return false;
    }
}
