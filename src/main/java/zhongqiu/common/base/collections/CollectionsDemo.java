package zhongqiu.common.base.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangzhongqiu
 * @date 2018/1/22.
 */
public class CollectionsDemo {
    private static ArrayList<String> lists = new ArrayList<>();

    public static void main(String[] args) {
        lists.add("1");
        lists.add("2");
        lists.add("3");
        List<String> synlist = Collections.synchronizedList(lists);
    }
}
