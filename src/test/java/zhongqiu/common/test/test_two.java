package zhongqiu.common.test;

import java.util.*;

/**
 * Created by wangzhongqiu on 2017/8/12.
 */
public class test_two {
    public static void main(String[] args) {

    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (listNode != null) {
            arrayList.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> newArrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            newArrayList.add(arrayList.get(arrayList.size() - 1 - i));
        }
        return newArrayList;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

