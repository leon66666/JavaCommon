package zhongqiu.common.base.algorithm.programming;

/**
 * Created by wangzhongqiu on 2017/8/22.
 * 剑指offer：旋转数组
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class RevolvingArray {
    public static void main(String[] args) {
        RevolvingArray revolvingArray = new RevolvingArray();
        int[] array = {3,2};
        int i = revolvingArray.minNumberInRotateArray(array);
    }

    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int flag = 0;
        int[] newArray = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                flag = i;
                break;
            }
        }
        int newArrayNum = 0;
        for (int i = flag; i < array.length; i++) {
            newArray[newArrayNum++] = array[i];
        }
        for (int i = 0; i < flag; i++) {
            newArray[newArrayNum++] = array[i];
        }
        return newArray[0];
    }
}
