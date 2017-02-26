package zhongqiu.common.jdk5;

//枚举
/*1、switch的枚举用法
2、枚举的自定义方法
3、枚举实现接口
4、枚举集合的使用。java.util.EnumSet和java.util.EnumMap是两个枚举集合。
  EnumSet保证集合中的元素不重复；EnumMap中的key是enum类型，而value则可以是任意类型。*/
public class EnumDemo {
	public static void main(String[] args) {
		EnumSwitch();
	}

	public enum Color {
		RED, GREEN, BLANK, YELLOW
	}

	// 自定义枚举
	public enum ColorExt {
		RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private ColorExt(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (ColorExt c : ColorExt.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 覆盖方法
		@Override
		public String toString() {
			return this.index + "_" + this.name;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	// 枚举的switch用法
	public static void EnumSwitch() {
		Color color = Color.RED;
		System.out.println(color);
		switch (color) {
		case RED:
			color = Color.GREEN;
			System.out.println(color);
			break;
		case YELLOW:
			color = Color.RED;
			System.out.println(color);
			break;
		case GREEN:
			color = Color.YELLOW;
			System.out.println(color);
			break;
		}
	}

	// 枚举实现接口
	public interface Behaviour {
		void print();

		String getInfo();
	}

	public enum ColorImpl implements Behaviour {
		RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private ColorImpl(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 接口方法
		@Override
		public String getInfo() {
			return this.name;
		}

		// 接口方法
		@Override
		public void print() {
			System.out.println(this.index + ":" + this.name);
		}
	}
}
