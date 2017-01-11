package zhongqiu.common.base.collections;

import java.util.Stack;

//栈，Vector的子类
public class StackDemo {
	// 把元素放入栈顶
	static void showpush(Stack st, int a) {
		st.push(new Integer(a));
		System.out.println("push(" + a + ")");
		System.out.println("stack: " + st);
	}

	// 从栈顶删除元素
	static void showpop(Stack st) {
		System.out.print("pop -> ");
		// 判断栈是否为空
		if (st.empty()) {
			System.out.println("Stack is empty.");
		} else {
			Integer a = (Integer) st.pop();
			System.out.println(a);
			System.out.println("stack: " + st);
		}
	}

	// 查看栈顶元素
	static void showpeek(Stack st) {
		System.out.print("peek -> ");
		if (st.empty()) {
			System.out.println("Stack is empty.");
		} else {
			Integer a = (Integer) st.peek();
			System.out.println(a);
			System.out.println("stack: " + st);
		}
	}

	// 查询指定元素
	static void showsearch(Stack st, int i) {
		System.out.print("search -> " + i);
		Integer index = (Integer) st.search(i);
		System.out.println("--index -> " + index);
		System.out.println("stack: " + st);
	}

	public static void main(String args[]) {
		Stack st = new Stack();
		System.out.println("stack: " + st);
		showpush(st, 42);
		showpush(st, 66);
		showpeek(st);
		showsearch(st, 66);
		showsearch(st, 88);
		showpop(st);
		showpop(st);
		showpop(st);
	}
}
