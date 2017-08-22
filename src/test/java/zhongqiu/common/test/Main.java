package zhongqiu.common.test;
/**
 * 赛码网例题
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scan.nextInt();
        }
        int res=0;
        Arrays.sort(arr);
        for(int i =0;i<n;i++){
            //判断第二个数和第一个数字的差 大于20则跳过（还需要出2道题目）
            if(i+1<n && arr[i+1]-arr[i]>20){
                res+=2;
                continue;
                //如果不到与20但是大于10，则还需要出一道题
            }else if(i+1<n && arr[i+1]-arr[i]>10){
                res+=1;
                i=i+1;
                continue;
                //如果在10以内的情况
            }else if(i+1<n){
                //判断第三个数字比第二个数字大多少
                //10以内
                if(i+2<n && arr[i+2]-arr[i+1]<=10){
                    i=i+2;
                    //10以外
                }else if(i+2<n){
                    res+=1;
                    i=i+1;
                    //不存在第三个数字
                }else{
                    res+=1;
                    i=i+1;
                }
            }else{
                //如果没有下一个了就+2
                res+=2;
            }
        }
        System.out.println(res);
    }
}

class test {

}