package zhongqiu.common.jdk5.concurrent;

import java.util.concurrent.ConcurrentHashMap;

//http://www.cnblogs.com/wangzhongqiu/p/6464230.html
//源码解析：http://www.importnew.com/22007.html
public class ConcurrentHashMapDemo {
    private static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        concurrentHashMap.put("1", "111");
        concurrentHashMap.get("1");
    }
}
