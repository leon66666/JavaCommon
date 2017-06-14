package zhongqiu.common.jdk5;

import java.util.Random;

//泛型用法。
//泛型类，泛型接口，泛型方法，通配符用法extends和super
/*在于Java中的泛型这一概念提出的目的，导致其只是作用于代码编译阶段，
在编译过程中，对于正确检验泛型结果后，会将泛型的相关信息擦出，也就是说，
成功编译过后的class文件中是不包含任何泛型信息的。泛型信息不会进入到运行时阶段。*/
public class GenericsDemo {
	public static void main(String[] args) {
		// 泛型作为参数
		Container<String, String> c1 = new Container<String, String>("name", "findingsea");
		Container<String, Integer> c2 = new Container<String, Integer>("age", 24);
		Container<Double, Double> c3 = new Container<Double, Double>(1.1, 2.2);
		Container<Integer, Object> c4 = new Container<Integer, Object>(1, new Object());
		getContainer(c1);
		getContainer(c2);
		getContainer(c3);
		getContainerSuper(c4);// getContainerSuper(c3)会报错
		getContainerExtends(c1);// getContainerExtends(c2);会报错

		// 泛型接口
		FruitGenerator generator = new FruitGenerator();
		System.out.println(generator.next());
		System.out.println(generator.next());

		// 泛型方法
		out("findingsea");
		out(123);
		out("abc", "lkj", "sgh");
	}

	// 泛型类
	public static class Container<K, V> {
		private K key;
		private V value;

		public Container(K k, V v) {
			key = k;
			value = v;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}

	// 通配符接受泛型
	public static void getContainer(Container<?, ?> data) {
		System.out.println("key :" + data.getKey());
		System.out.println("value :" + data.getValue());
	}

	// 通配符接受泛型(extends用法)
	public static void getContainerExtends(Container<?, ? extends String> data) {
		System.out.println("key :" + data.getKey());
		System.out.println("value :" + data.getValue());
	}

	// 通配符接受泛型(super用法)
	public static void getContainerSuper(Container<?, ? super String> data) {
		System.out.println("key :" + data.getKey());
		System.out.println("value :" + data.getValue());
	}

	// 泛型接口
	public interface Generator<T> {
		public T next();
	}

	// 泛型接口的实现类
	public static class FruitGenerator implements Generator<String> {

		private String[] fruits = new String[] { "Apple", "Banana", "Pear" };

		@Override
		public String next() {
			Random rand = new Random();
			return fruits[rand.nextInt(3)];
		}
	}

	public static <R, T> R in(T t, R r) {
		return r;
	}

	// 泛型方法
	public static <T> void out(T t) {
		System.out.println(t);
	}

	// 泛型方法和可变参数
	public static <T> void out(T... args) {
		for (T t : args) {
			System.out.println(t);
		}
	}

}
