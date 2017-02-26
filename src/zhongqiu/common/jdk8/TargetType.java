package zhongqiu.common.jdk8;

//目标类型
//对于给定的 lambda 表达式，它的类型是由其上下文推导而来
//类型自动推导
public class TargetType {

	// 由于目标类型（函数式接口）已经“知道” lambda 表达式的形式参数（Formal
	// parameter）类型，所以我们没有必要把已知类型再重复一遍。也就是说，lambda 表达式的参数类型可以从目标类型中得出：
	// Comparator<String> c = (s1, s2) -> s1.compareToIgnoreCase(s2);
	// 在上面的例子里，编译器可以推导出 s1 和 s2 的类型是 String。此外，当 lambda
	// 的参数只有一个而且它的类型可以被推导得知时，该参数列表外面的括号可以被省略：
	//
	// FileFilter java = f -> f.getName().endsWith(".java");
	// button.addActionListener(e -> ui.dazzle(e.getModifiers()));
	// 这些改进进一步展示了我们的设计目标：“不要把高度问题转化成宽度问题。”我们希望语法元素能够尽可能的少，以便代码的读者能够直达 lambda
	// 表达式的核心部分。
	//
	// lambda 表达式并不是第一个拥有上下文相关类型的 Java 表达式：泛型方法调用和“菱形”构造器调用也通过目标类型来进行类型推导：
	// List<String> ls = Collections.emptyList();
	// List<Integer> li =Collections.emptyList(); Map<String, Integer> m1 = new
	// HashMap<>();
	// Map<Integer, String> m2 = new HashMap<>();

}