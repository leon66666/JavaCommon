package zhongqiu.common.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test1 {
    public static void main(String[] args) {
        int T = 0;
        // InputStreamReader 是字节流通向字符流的桥梁;
        // 为了达到最高效率，可要考虑在 BufferedReader 内包装 InputStreamReader。例如：
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            T = Integer.parseInt(bufferedReader.readLine());
            for(int i=0; i<T; i++){
                String str = bufferedReader.readLine();
                System.out.println(firstAppearsOnlyonce(str));
                }
            } catch (NumberFormatException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }

        }

            /**
     * 找字符串中第一个只出现一次的字符
     */
            private static char firstAppearsOnlyonce(String str) {
        int[] hash = new int[256]; //记录每个字符对应的个数,共有256个ASCII码
        for(int i=0; i<256; i++){
            hash[i] = 0;
            }
        for(int i=0; i<str.length(); i++){
            hash[str.charAt(i)] ++; //建立一个字符与个数反映射关系!
            }
        for(int i=0; i<str.length(); i++){ //再遍历一遍字符串,找第一个出现一次字符
            if(hash[str.charAt(i)] == 1){
                return str.charAt(i);
                }
            }
        return '\0';
        }
}
