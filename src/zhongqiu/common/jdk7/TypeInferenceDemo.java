package zhongqiu.common.jdk7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//类型自动推断  <>
public class TypeInferenceDemo {

	List<String> tempList = new ArrayList<>();

	// 没有使用类型推断
	Map<String, List<String>> map1 = new HashMap<String, List<String>>();
	// 通过类型推断后变成：
	Map<String, List<String>> map2 = new HashMap<>();// 这个<>被叫做diamond（钻石）运算符，这个运算符从引用的声明中推断类型。
}
