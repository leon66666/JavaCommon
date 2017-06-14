package zhongqiu.common.jdk7;

import java.util.List;

//可变参数非具体化是提示警告
public class VarableParameter {
	public static void main(String[] args) {

	}

	public static <T> void addToList (List<T> listArg, T... elements) {
		for (T x : elements) {
			listArg.add(x);
		}
	}
}
