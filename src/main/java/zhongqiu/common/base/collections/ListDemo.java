package zhongqiu.common.base.collections;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

//List相关操作
public class ListDemo {
    public static void main(String[] args) {
        initList();
//        traversal();
        // sort();
        // reverse();
        userDefinedSort();
        // shuffle();
        // copy();
        // min();
        // max();
        // lastIndexOfSubList();
        // indexOfSubList();
        // rotate();
        // fill();
//        Iterator<String> iterator = list.iterator();
    }

    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<CollectionPo> arrayList = new ArrayList<>();// 非同步，可变数组
    private static LinkedList<CollectionPo> linkedList = new LinkedList<>();// 非同步，链表
    private static Vector<CollectionPo> vector = new Vector<>();// 同步
    private static Stack<Integer> stack = new Stack<>();

    // 初始化方法
    public static void initList() {
        // Arrays.asList方法
        list = new ArrayList<String>(Arrays.asList("Apple", "Banan", "Orange"));

        arrayList = new ArrayList<CollectionPo>(Arrays.asList(new CollectionPo(1, "赵", 30),
                new CollectionPo(0, "钱", 22), new CollectionPo(0, "孙", 66)));

        // add方法
        CollectionPo cPo = new CollectionPo(1, "李", 7);
        arrayList.add(cPo);

        arrayList.add(new CollectionPo(1, "周", 87));
        linkedList.add(cPo);
        list.add("Pear");
        list.remove("Pear");
        Object o = new Object();
        Boolean b = (Object) Object[].class == o;
    }

    // 遍历方法
    public static void traversal() {
        // foreach遍历，只是遍历，不能改变序列中的元素
        for (String string : list) {
            System.out.println(string);
        }
        for (CollectionPo cPo : arrayList) {
            System.out.println(cPo.name);
        }
        // for遍历，可以对序列中的元素进行操作
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i).name);
        }
        // 迭代器遍历
        Iterator<String> ite = list.iterator();
        while (ite.hasNext())// 判断下一个元素之后有值
        {
            System.out.println(ite.next());
        }
    }

    // 升序排序
    public static void sort() {
        Collections.sort(list);
        System.out.println(list);
    }

    // 降序排序
    public static void reverse() {
        Collections.reverse(list);
        System.out.println(list);
    }

    // 自定义排序
    public static void userDefinedSort() {
        // 排序List<String>,按照String排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        System.out.println(list);
        // 排序List<CollectionPo>,按照age属性排序
        Collections.sort(arrayList, new Comparator<CollectionPo>() {
            @Override
            public int compare(CollectionPo a, CollectionPo b) {
                return b.age.compareTo(a.age);
            }
        });
        for (CollectionPo cPo : arrayList) {
            System.out.println(cPo.getAge() + "--" + cPo.getName());
        }
        // 排序List<CollectionPo>,按照age属性排序
        Collections.sort(arrayList, new Comparator<CollectionPo>() {
            @Override
            public int compare(CollectionPo a, CollectionPo b) {
                if (a.age > b.age) {
                    return 1;
                } else if (a.age == b.age) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        for (CollectionPo cPo : arrayList) {
            System.out.println(cPo.getAge() + "--" + cPo.getName());
        }
    }

    // 混排，打乱排序
    public static void shuffle() {
        Collections.shuffle(list);
        System.out.println(list);
    }

    // 全部替换
    public static void fill() {
        Collections.fill(list, "aaa");
        System.out.println(list);
    }

    // 复制
    public static void copy() {
        // 需要提前设置好目标list的size，否则会报错
        ArrayList<String> newlist = new ArrayList<>(Arrays.asList(new String[list.size()]));

        Collections.copy(newlist, list);
        System.out.println(newlist);
    }

    // 最小值
    public static void min() {
        System.out.println(Collections.min(list));
    }

    // 最大值
    public static void max() {
        System.out.println(Collections.max(list));
    }

    // 最后一次出现的位置
    public static void lastIndexOfSubList() {
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 6, 6, 7, 3));
        ArrayList<Integer> targetList = new ArrayList<>(Arrays.asList(6));
        System.out.println(Collections.lastIndexOfSubList(intList, targetList));
    }

    // 第一次出现的位置
    public static void indexOfSubList() {
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 6, 6, 7, 3));
        ArrayList<Integer> targetList = new ArrayList<>(Arrays.asList(6));
        System.out.println(Collections.indexOfSubList(intList, targetList));
    }

    // 移动列表中的元素，负数向左移动，正数向右移动
    public static void rotate() {
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(intList);
        Collections.rotate(intList, 1);
        System.out.println(intList);
    }
}
