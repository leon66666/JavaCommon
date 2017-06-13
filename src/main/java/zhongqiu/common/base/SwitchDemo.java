package zhongqiu.common.base;


//Switch�÷�
//һ��caseƥ��,�ͻ�˳��ִ�к���ĳ������,�����ܺ����case�Ƿ�ƥ��,ֱ������break,������һ���Կ����úü���caseִ��ͳһ���.
//http://www.cnblogs.com/wangzhongqiu/p/6574529.html
//case 1,2д������
public class SwitchDemo {
	public static void main(String[] args) {
		// switch�п���ʹ���ִ��ˡ�������������ڱ�������������ʵ�ֵ�
		String s = "test";
		switch (s) {
		case "test":
			System.out.println("test");
			break;
		case "a":
			System.out.println("a");
			break;
		case "test1":
			System.out.println("test1");
			break;
		default:
			System.out.println("break");
			break;
		}
		test();
	}

	public static void test() {
		int i = 0, j = -1;
		switch (i) {
		case 3:
			j = 1;
		case 4:
			j = 2;
		}
		System.out.println(j);
	}
}
