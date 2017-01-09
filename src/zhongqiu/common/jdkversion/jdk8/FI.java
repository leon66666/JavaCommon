package zhongqiu.common.jdkversion.jdk8;

//函数式接口：我们把这些只拥有一个方法的接口称为 函数式接口。也叫作 单抽象方法类型
// 因为 默认方法 不算抽象方法，所以你也可以给你的函数式接口添加默认方法。
// Lambda表达式会被编译器转换成相应函数式接口的一个实例
// FI<String, Integer> fI = (from) -> Integer.valueOf(from);
// Integer converted = fI.convert("123");
// System.out.println(converted);
@FunctionalInterface
public interface FI<F, T> {
	T convert(F from);
}
