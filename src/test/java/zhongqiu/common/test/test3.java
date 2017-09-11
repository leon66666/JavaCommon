package zhongqiu.common.test;
import java.util.Scanner;
/**
 * Created by hxb002 on 2017/8/31.
 */
public class test3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = scan.nextInt();
        }
        int goalNum = scan.nextInt();
        long sum = 0;
        int index = 0;
        for (int i = num; i > 0; i--) {
            for (int j = 0; j <= num - i; j++) {
                while (index != num) {
                    sum = sum + arr[j];
                    index++;
                }
                index = 0;
                if (sum % goalNum == 0) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(0);
    }
}
