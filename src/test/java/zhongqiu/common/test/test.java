package zhongqiu.common.test;

import java.util.concurrent.ArrayBlockingQueue;

public class test {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue=new ArrayBlockingQueue<Integer>(4);
        try {
            arrayBlockingQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}