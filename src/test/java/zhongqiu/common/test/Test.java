package zhongqiu.common.test;

public class Test {
	static {
		System.out.println("Test��̬��ʼ����ִ���ˣ�");
	}
	Integer i;
	Integer j;

	Test() {

	}

	Test(Integer i, Integer j) {
		setI(i);
		setJ(j);
	}

	public Integer getJ() {
		return j;
	}

	public void setJ(Integer j) {
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}
}
