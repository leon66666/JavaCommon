package zhongqiu.common.base.algorithm.search;

/**
 * Created by wangzhongqiu on 2017/8/17.
 * 顺序查找
 */
public class SequentialSearch {
    public static void mian(String[] args) {

    }

    public boolean find(int target, int[][] array) {
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                if (array[x][y] == target) return true;
            }
        }
        return false;
    }
}
