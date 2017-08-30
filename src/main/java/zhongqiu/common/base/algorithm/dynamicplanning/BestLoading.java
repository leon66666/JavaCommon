package zhongqiu.common.base.algorithm.dynamicplanning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/8/6.
 * 最优装载问题。有一批集装箱要装上一艘载重量为c的轮船。其中集装箱i的重量为Wi。要求确定在装载体积不受限制的情况下，将尽可能多的集装箱装上轮船。
 * 关键字：装载体积不受限制，尽可能多的集装箱装上轮船
 * 解决方案：贪心算法
 */

public class BestLoading {
    public float loading(float maxWeight, float[] weights, List x) {
        int n = weights.length;
        Element[] d = new Element[n];
        for (int i = 0; i < n; i++) {
            d[i] = new Element(weights[i], i);
        }
        java.util.Arrays.sort(d);
        float op = 0;
        int xNum = 0;
        for (int i = 0; i < n && d[i].weight <= maxWeight; i++) {
            op += d[i].weight;
            maxWeight -= d[i].weight;
            x.add(d[i].index);
        }
        return op;
    }

    static class Element implements Comparable {
        float weight;
        int index;

        public Element(float w, int i) {
            weight = w;
            index = i;
        }

        @Override
        public int compareTo(Object x) {//按每个重量从小到大排列
            float xx = ((Element) x).weight;
            if (this.weight < xx) return -1;
            if (this.weight == xx) return 0;
            return 1;
        }
    }

    public static void main(String[] args) {
        float w[] = {20, 10, 26, 15};//下标从0开始
        float c = 70;
        List<Integer> x = new ArrayList<>();
        BestLoading be = new BestLoading();
        System.out.println("最优得到装载重量为：" + be.loading(c, w, x));
        System.out.println("被装载的集装箱序号为(下标从0开始)：");
        for (int i = 0; i < x.size(); i++) {
            System.out.print(x.get(i) + ",");
        }
    }
}
