package zhongqiu.common.base.collections;

//数组相关
public class ArrayDemo {
	public static int[] intArray={1,2,3,4}; // 首选的方法

	// public static int doubleArray2[]; 效果相同，但不是首选方法。沿用c/c++的写法

	public static void main(String[] args) {
		initArray();
		printArray(intArray);
		intArray = reverse(intArray);
		printArray(intArray);
	}

	// 创建数组
	public static void initArray() {
		int[] intArray1 = new int[10];
		int[] intArray2={1,2,3,4};
	}

	// 数组作为函数的参数
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	// 数组作为函数的返回值
	public static int[] reverse(int[] list) {
		int[] result = new int[list.length];

		for (int i = 0, j = result.length - 1; i < list.length; i++, j--) {
			result[j] = list[i];
		}
		return result;
	}

}
