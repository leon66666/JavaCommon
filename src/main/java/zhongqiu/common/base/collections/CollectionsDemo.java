package zhongqiu.common.base.collections;

import java.util.*;

/**
 * @author wangzhongqiu
 * @date 2018/1/22.
 */
public class CollectionsDemo {
    private static ArrayList<String> lists = new ArrayList<>();
    private static HashSet<String> set = new HashSet<>();
    private static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        lists.add("1");
        lists.add("2");
        lists.add("3");
        set.add("1");
        map.put("1", "1");

        List<String> synlist = Collections.synchronizedList(lists);
        Set<String> synset = Collections.synchronizedSet(set);
        Map<String, String> synmap = Collections.synchronizedMap(map);
    }
}
