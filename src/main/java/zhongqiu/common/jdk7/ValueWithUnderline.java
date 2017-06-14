package zhongqiu.common.jdk7;

//数值可加下划线
//很长的数字可读性不好，在Java 7中可以使用下划线分隔长int以及long了
public class ValueWithUnderline {
	public static void main(String[] args) {
		int one_million = 1_000_000;
		System.out.println(one_million);
		int i=1_1 * 10;
		System.out.println(i);
		int j=120 - 1_________________0;
		System.out.println(j);
	}
}
