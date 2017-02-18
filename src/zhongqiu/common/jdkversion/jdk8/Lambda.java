package zhongqiu.common.jdkversion.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Lambda 表达式
//方法与构造函数引用
public class Lambda {
	public static List<String> fruitList = Arrays.asList("Apple", "Banan", "Orange", "Pear");
	public static ArrayList<String> values = new ArrayList<>(1000000);

	public static void main(String[] args) {
		// StreamDemo.filter();
		// StreamDemo.sorted();
		// StreamDemo.match();
		// StreamDemo.count();
		// StreamDemo.reduce();
		// StreamDemo.map();
		StreamDemo.flagMap();
		// ParallelStreamDemo.init();
		// ParallelStreamDemo.stream();
		// ParallelStreamDemo.parallelStream();
		// ReuseLambda.reUseLambda();
	}

	// Stream 接口
	// Stream操作分为中间操作或者最终操作两种，最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样你就可以将多个操作依次串起来。
	// 最终操作：forEach,match,count,reduce,collect
	// 中间操作：filter,sorted,map,flagMap
	public static class StreamDemo {
		// forEach 遍历
		public static void forEach() {
			fruitList.forEach(fruit -> System.out.println(fruit));
			fruitList.forEach(System.out::println); // 简写形式（方法引用）
		}

		// match 匹配
		public static void match() {
			boolean anyStartsWithA = fruitList.stream().anyMatch((s) -> s.startsWith("A"));

			System.out.println(anyStartsWithA); // true

			boolean allStartsWithA = fruitList.stream().allMatch((s) -> s.startsWith("A"));

			System.out.println(allStartsWithA); // false

			boolean noneStartsWithZ = fruitList.stream().noneMatch((s) -> s.startsWith("z"));

			System.out.println(noneStartsWithZ);// true
		}

		// count
		public static void count() {
			long count = fruitList.stream().count();
			System.out.println(count);
		}

		// reduce 规约
		public static void reduce() {
			Optional<String> reduced = fruitList.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
			reduced.ifPresent(System.out::println);
		}

		// Filter 过滤
		public static void filter() {
			fruitList.stream().filter(s -> s.startsWith("A")).forEach(System.out::println);

			// 把stream赋值给新的List<String>
			List<String> tempList = fruitList.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());
		}

		// sort 排序
		public static void sorted() {
			// 默认升序排序
			fruitList.stream().sorted().forEach(System.out::println);
			// 降序排序
			fruitList.stream().sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
		}

		// map 映射 1对1
		public static void map() {
			// 遍历输出名字的大写形式
			fruitList.stream().map(name -> name.toUpperCase()).forEach(name -> System.out.print(name + " "));
			fruitList.stream().map(String::toUpperCase).forEach(System.out::println);// 方法与构造函数引用

			// 遍历输出每个名字的长度
			fruitList.stream().map(String::length).forEach(count -> System.out.print(count + " "));
			fruitList.stream().map(String::length).forEach(System.out::println);
		}

		// flagMap 映射 一对多
		public static void flagMap() {
			Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3),
					Arrays.asList(4, 5, 6));
			Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
			outputStream.forEach(System.out::println);
		}

		// 流转换成其他的数据结构
		public static void streamConvert() {
			Stream<String> stream = fruitList.stream();
			// 1. Array
			String[] strArray1 = stream.toArray(String[]::new);
			// 2. Collection
			List<String> list1 = stream.collect(Collectors.toList());
			List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
			Set set1 = stream.collect(Collectors.toSet());
			Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
			// 3. String
			String str = stream.collect(Collectors.joining()).toString();
		}
	}

	// 并行Streams
	public static class ParallelStreamDemo {
		// 初始化
		public static void init() {
			for (int i = 0; i < 1000000; i++) {
				UUID uuid = UUID.randomUUID();
				values.add(uuid.toString());
			}
		}

		// 串行stream
		public static void stream() {
			long t0 = System.nanoTime();

			long count = values.stream().sorted().count();
			System.out.println(count);

			long t1 = System.nanoTime();

			long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
			System.out.println(String.format("sequential sort took: %d ms", millis));
		}

		// 并行stream
		public static void parallelStream() {
			long t0 = System.nanoTime();

			long count = values.parallelStream().sorted().count();
			System.out.println(count);

			long t1 = System.nanoTime();

			long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
			System.out.println(String.format("parallel sort took: %d ms", millis));

		}
	}

	public static class ReuseLambda {
		// 动态生成Lambda表达式的高阶函数。拥有闭包特性
		public static Predicate<String> checkIfStartsWith(final String letter) {
			return name -> name.startsWith(letter);
		}

		public static void reUseLambda() {
			// Lambda表达式的重用
			System.out.println(fruitList.stream().filter(checkIfStartsWith("A")).count());
			System.out.println(fruitList.stream().filter(checkIfStartsWith("B")).collect(Collectors.toList()));
		}
	}

}
