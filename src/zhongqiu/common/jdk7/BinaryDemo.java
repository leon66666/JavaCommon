package zhongqiu.common.jdk7;

//支持二进制文字
public class BinaryDemo {
	public static void main(String[] args) {
		int binary = 0b1001_1001;
		System.out.println(binary);

		byte aByte = (byte) 0b001;
		System.out.println(aByte);

		short aShort = (short) 0b010;
		System.out.println(aShort);
	}
}
