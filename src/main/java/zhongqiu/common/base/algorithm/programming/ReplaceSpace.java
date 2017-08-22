package zhongqiu.common.base.algorithm.programming;

/**
 * Created by wangzhongqiu on 2017/8/20.
 * 剑指offer：替换字符串中的所有空格为"%20"
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer(" ");
        String str = replaceSpace(s);
        System.out.print(str);
    }

    public static String replaceSpace(StringBuffer str) {
        String[] arr = new String[str.length()];
        int arrMark = 0;
        int j = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                if (i > j + 1) {
                    arr[arrMark++] = str.substring(j + 1, i);
                    j = i;
                }
                arr[arrMark++] = "%20";
                j = i;
            } else if (i == str.length() - 1) {
                arr[arrMark++] = str.substring(j + 1, i + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}
