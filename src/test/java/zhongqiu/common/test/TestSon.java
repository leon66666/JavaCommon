package zhongqiu.common.test;

public class TestSon extends Test {
	String name;

	TestSon(Integer i, Integer j, String name) {
		super(i, j);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
