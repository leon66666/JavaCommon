package zhongqiu.common.base.algorithm;

/**
 * Created by wagnzhongqiu on 2017/5/17.
 */
public class LinkedList {
    public Node head;
    public Node current;

    // 向链表中添加数据
    public void add(int data) {
        // 判断链表为空的时候
        if (head == null) {// 如果头结点为空，说明这个链表还没有创建，那就把新的结点赋给头节点
            head = new Node(data);
            current = head;
        } else {
            current.setNext(new Node(data));// 创建新的结点，放在当前节点的后面（把新的节点和链表进行关联）
            current = current.getNext();// 把链表的当前索引向后移动一位，此步操作完成之后，current结点指向新添加的那个结点
        }
    }

    // 方法重载：向链表中添加结点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (head == null) {
            head = node;
            current = head;
        } else {
            current.setNext(node);
            current = current.getNext();
        }
    }

    // 方法：遍历链表（打印输出链表。方法的参数表示从节点node开始进行遍历
    public void print(Node node) {
        if (node == null) {
            return;
        }
        current = node;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        // 向LinkList中添加数据
        for (int i = 0; i < 6; i++) {
            list1.add(i);
        }
        for (int i = 3; i < 6; i++) {
            list2.add(i);
        }
        Node node = LinkedListUtil.getFirstCommonNode(list1, list2);
        System.out.println(node.getData());
    }

    static class Node {
        // 注：此处的两个成员变量权限不能为private，因为private的权限是仅对本类访问
        private int data;// 数据域
        private Node next;// 指针域

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    static class LinkedListUtil {
        // 方法：求两个单链表相交的第一个交点
        public static Node getFirstCommonNode(LinkedList list1, LinkedList list2) {
            Node head1 = list1.head;
            Node head2 = list2.head;
            if (head1 == null && head2 == null) {
                return null;
            }
            int length1 = getLength(head1);
            int length2 = getLength(head2);
            int lengthDif = 0;// 两个链表长度的差值
            Node Longhead;
            Node Shorthead;
            // 找出较长的那个链表
            if (length1 > length2) {
                Longhead = head1;
                Shorthead = head2;
                lengthDif = length1 - length2;
            } else {
                Longhead = head2;
                Shorthead = head1;
                lengthDif = length2 - length1;
            }
            // 将较长的那个链表的指针向前走length个距离
            for (int i = 0; i < lengthDif; i++) {
                Longhead = Longhead.getNext();
            }
            // 将两个链表的指针同时向前移动 
            while (Longhead != null && Shorthead != null) {
                Longhead = Longhead.getNext();
                Shorthead = Shorthead.getNext();
                if (Longhead == Shorthead) {// 第一个相同的结点就是相交的第一个结点
                    return Longhead;
                }
            }
            return null;
        }

        // 方法：获取单链表的长度
        public static int getLength(Node head) {
            if (head == null) {
                return 0;
            }
            int length = 0;
            Node current = head;
            while (current != null) {
                length++;
                current = current.getNext();
            }
            return length;
        }
    }
}




