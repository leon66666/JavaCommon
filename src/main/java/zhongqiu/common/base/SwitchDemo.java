package zhongqiu.common.base;

//Switch用法
//一旦case匹配,就会顺序执行后面的程序代码,而不管后面的case是否匹配,直到遇见break,利用这一特性可以让好几个case执行统一语句.
//http://www.cnblogs.com/wangzhongqiu/p/6574529.html
public class SwitchDemo {
	public static void main(String[] args) {
		//switch中可以使用字串了。这个新特性是在编译器这个层次上实现的
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
	}
}
