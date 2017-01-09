package zhongqiu.common.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则相关
//http://www.runoob.com/java/java-regular-expressions.html
public class RegexDemo {
	public static void main(String[] args) {
		match();
		replace();
	}

	public static void match() {
		// 按指定模式在字符串查找
		String line = "This order was placed for QT3000! OK?";
		String pattern = "(\\D*)(\\d+)(.*)";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.groupCount() > 0) {
			System.out.println(m.groupCount());
			for (int i = 0; i < m.groupCount(); i++) {
				System.out.println("Found value: " + m.group(i));
			}
		} else {
			System.out.println("NO MATCH");
		}
	}

	public static void replace() {
		String REGEX = "dog";
		String INPUT = "The dog says meow. " + "All dogs say meow.";

		// 全部替换
		System.out.println(Pattern.compile(REGEX).matcher(INPUT).replaceAll("cat"));

		// 替换第一个
		System.out.println(Pattern.compile(REGEX).matcher(INPUT).replaceFirst("cat"));

	}

}
