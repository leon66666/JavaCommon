package zhongqiu.common.jdk6;

import java.io.Console;

//用Console开发控制台程序 
/*你如果是在一个IDE中如eclipse, netbeans中运行你将得到：
No Console!
因为只有在命令行中才能得到Console对象。*/
public class ConsoleDemo {
	public static void main(String[] args) {
		Console console = System.console();
		if (console != null) {
			String user = new String(console.readLine("Enter User:", new Object[0]));
			String pwd = new String(console.readPassword("Enter Password:", new Object[0]));
			console.printf("User name is:%s", new Object[] { user });
			console.printf("Password is:%s", new Object[] { pwd });
		} else {
			System.out.println("No Console!");
		}
	}
}
