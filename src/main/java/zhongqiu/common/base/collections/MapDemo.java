package zhongqiu.common.base.collections;

import java.net.Inet4Address;
import java.security.KeyStore.Entry;
import java.util.*;

import zhongqiu.common.jdk7.ValueWithUnderline;

import javax.net.ssl.SSLEngineResult;

//Map
public class MapDemo {

    public static void main(String[] args) {
        init();
        // traversal();
        String str = "sdk,gljgl,;ldogl,ljkjg,dog,cat,dog";
        String subStr = "dog";
        statis(str, 40);
        count(str);
        subCounter(str, subStr);
    }

    // HashMap按照哈希算法来存取键对象，有很好的存取性能
    private static HashMap<Integer, Integer> hMap = new HashMap<>();

    // TreeMap实现了SortedMap接口，能对键对象进行排序。支持自然排序和客户化排序两种方式。
    private static TreeMap<Integer, Integer> tMap = new TreeMap<>();

    //线程安全，synchronized内置锁
    private static Hashtable<Integer, Integer> hashtable = new Hashtable<>();

    // map的赋值
    public static void init() {
        hMap.put(1, 1);
        hMap.put(2, 2);
        hMap.put(3, 3);
        hMap.put(null, null);
    }

    // map的遍历
    public static void traversal() {
        // 第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (Integer key : hMap.keySet()) {
            System.out.println("key= " + key + " and value= " + hMap.get(key));
        }

        // 第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<Integer, Integer>> it = hMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        // 第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<Integer, Integer> entry : hMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        // 第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (Integer v : hMap.values()) {
            System.out.println("value= " + v);
        }
    }

    // 统计字符串中每个字符出现的次数
    public static void statis(String str, int top) {
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        char[] cs = str.toCharArray();
        for (char c : cs) {
            if (null == hashMap.get(c)) {
                hashMap.put(c, 1);
            } else {
                hashMap.put(c, hashMap.get(c) + 1);
            }
        }
        // 把entry取出来进行排序
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        for (int i = 0; i < top; i++) {
            if (i < list.size()) {
                System.out.println(list.get(i).getKey() + "--" + list.get(i).getValue());
            }
        }
        // 只把value取出来
        List<Integer> valueList = new ArrayList<>(hashMap.values());
        Collections.sort(valueList, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });
        for (int i = 0; i < top; i++) {
            if (i < valueList.size()) {
                System.out.println(valueList.get(i));
            }
        }
    }

    // 统计字符串中的大写，小写，数字，其他字符个数
    public static void count(String s) {
        int low, upper, num, others;
        low = upper = num = others = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num++;
                continue;
            }
            if (Character.isLowerCase(s.charAt(i))) {
                low++;
                continue;
            }
            if (Character.isUpperCase(s.charAt(i))) {
                upper++;
                continue;
            } else {
                others++;
            }
        }
        System.out
                .println(" 大写字母的个数为：" + upper + "\n 小写字母的个数为：" + low + "\n 数字的个数为： " + num + "\n 其他字符的个数为： " + others);
    }

    // 统计字符串中子字符串出现的次数
    public static void subCounter(String str1, String str2) {
        int counter = 0;
        for (int i = 0; i <= str1.length() - str2.length(); i++) {
            if (str1.substring(i, i + str2.length()).equalsIgnoreCase(str2)) {
                counter++;
            }
        }
        System.out.println("子字符串的个数为： " + counter);
    }
}