package zhongqiu.common.base;

//随机数。Math.random() -- 返回0和1之间的伪随机数 可能为0，但总是小于1，[0,1)
public class MathRandomDemo {
	public static void main(String[] args) {
		intAndChar();
		for (int i = 0; i < 100; i++) {
			System.out.println(Math.random());
		}
		for (int i = 0; i < 10; i++) {
			sixRandomStr();
		}

	}

	// int和char对应关系
	public static void intAndChar() {
		for (int i = 1; i < 257; i++) {
			System.out.println(i + "对应:" + (char) i);
		}
	}

	// 生成一个6位的随机字符串
	public static void sixRandomStr() {
		String result = "";
		for (int i = 0; i < 6; i++) {
			// 生成97-122的int型的整型
			int intValue = (int) (Math.random() * 25 + 97);
			// 将intValue强制转化成char类型后接到result后面
			result = result + (char) intValue;
		}
		// 输出字符串
		System.out.println(result);
	}
}
