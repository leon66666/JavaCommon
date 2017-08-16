package zhongqiu.common.utils;

/**
 * Created by wangzhongqiu on 2017/8/16.
 */
public class CommonUtils {
    public static void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
