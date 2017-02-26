package zhongqiu.common.jdk8;

import java.util.Arrays;
import java.util.List;

public class StringJoinDemo {
	public static List<String> fruitList = Arrays.asList("Apple", "Banan", "Orange", "Pear");

	public static void main(String[] args) {
		System.out.println(String.join(", ", fruitList));
	}
}
