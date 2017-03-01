package zhongqiu.common.base.collections;

import java.util.Arrays;
import java.util.List;

//Arrays类相关
public class ArraysDemo {
	public static void main(String[] args) {
		byte[] test1 = { 23, 34 };

		byte[] test2 = { 23, 34 };

		int[] test3 = { 4, 5, 6, 7 };

		// 根据第二个参数作为索引找出对应的下标，二分法查找算法
		System.out.println(Arrays.binarySearch(test3, 7));

		// toString方法
		System.out.println(test1.toString());
		System.out.println(Arrays.toString(test1));// 重写Tostring方法

		// Equals方法
		System.out.println(test1.equals(test2));
		System.out.println(Arrays.equals(test1, test2));// 重写了Equals方法
		System.out.println(test1 == test2);
		System.out.println(test1.getClass());
		// fill方法
		// 将指定的 byte 值分配给指定byte型数组指定范围中的每个元素。
		Arrays.fill(test1, 0, 1, (byte) 56);
		System.out.println(Arrays.toString(test1));

		// hashCode方法
		System.out.println(test1.hashCode());
		System.out.println(Arrays.hashCode(test1));// 基于指定数组的内容返回哈希码

		// aslist
		List list = Arrays.asList(test1);
		
	}

}
