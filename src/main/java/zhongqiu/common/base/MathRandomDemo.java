package zhongqiu.common.base;

import java.util.Random;

//随机数。Math.random() -- 返回0和1之间的伪随机数 可能为0，但总是小于1，[0,1)
public class MathRandomDemo {
    public static void main(String[] args) {
        intAndChar();
        for (int i = 0; i < 100; i++) {
            System.out.println(Math.random());
        }
        for (int i = 0; i < 10; i++) {
            sixRandomStr();
        }
    }

    // int和char对应关系
    public static void intAndChar() {
        for (int i = 1; i < 257; i++) {
            System.out.println(i + "对应:" + (char) i);
        }
    }

    // 生成一个6位的随机字符串
    public static void sixRandomStr() {
        String result = "";
        for (int i = 0; i < 6; i++) {
            // 生成97-122的int型的整型
            int intValue = (int) (Math.random() * 25 + 97);
            // 将intValue强制转化成char类型后接到result后面
            result = result + (char) intValue;
        }
        // 输出字符串
        System.out.println(result);
    }

    public static int getRadom(int i) {
        Random r = new Random();
        return r.nextInt(i);
    }

    //获取长度为n的随机数组，局限性：数组里会有重复
    public static int[] getRandomIntArrayWithRepeat(int n) {
        Random r = new Random();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(n);
        }
        return a;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     *
     * @param max 指定范围最大值
     * @param min 指定范围最小值
     * @param n   随机数个数
     * @return int[] 随机数结果集
     */
    public static int[] getRandomIntArrayWithoutRepeat(int min, int max, int n) {
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    public static int[] getIntArrayAsc(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    public static int[] getIntArrayDesc(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - i - 1;
        }
        return a;
    }
}
