package zhongqiu.common.base.algorithm.programming.swordatoffer;

import java.util.Stack;

/**
 * Created by wangzhongqiu on 2017/8/22.
 * 剑指offer：两个栈实现队列的功能。实现push和pop方法
 */
public class TwoStackReplaceQueen {
    public static void main(String[] args) {
        TwoStackReplaceQueen test = new TwoStackReplaceQueen();
        test.push(1);
        test.push(2);
        test.push(3);
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        if (stack1.isEmpty()) {
            stack1.push(node);
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack1.push(node);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
    }

    public int pop() {
        return stack1.pop();
    }
}
