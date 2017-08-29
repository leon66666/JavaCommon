package zhongqiu.common.test;
/*
*
* */

import java.util.Scanner;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String filePathTemp = "C:/Users/Sun/Desktop/测试.xls";
        String REGEX = "/";
        String aaa=Pattern.compile(REGEX).matcher(filePathTemp).replaceAll("\\\\\\\\");
        System.out.print(aaa);
    }
}
