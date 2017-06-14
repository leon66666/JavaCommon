package zhongqiu.common.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//流(Stream)、文件(File)和IO
//http://www.runoob.com/java/java-files-io.html
public class FileIODemo {
	public static void main(String[] args) {
		// BRRead();
		// BRReadLine();
		// Write();

		mkdirs();
		fileStream();
		dirList("mkdirs");
	}

	// 读取输入的字符
	public static void BRRead() {
		char c = 0;
		// 使用 System.in 创建 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入字符, 按下 'q' 键退出。");
		// 读取字符
		do {
			try {
				c = (char) br.read();
				System.out.println("你输入的字符是：" + c);
			} catch (IOException e) {
				System.out.println("获取输入内容失败");
			}
		} while (c != 'q');
	}

	// 读取输入的字符串
	public static void BRReadLine() {
		// 使用 System.in 创建 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		System.out.println("Enter lines of text.");
		System.out.println("Enter 'end' to quit.");
		do {
			try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("你输入的是：" + str);
		} while (!str.equals("end"));
	}

	// 控制台输出
	public static void Write() {
		char b;
		b = 'A';
		System.out.write(b);
		System.out.write('\n');
		System.out.println("第二种输出方法");
	}

	// 目录操作
	// mkdir()方法创建一个文件夹，成功则返回true，失败则返回false。失败表明File对象指定的路径已经存在，或者由于整个路径还不存在，该文件夹不能被创建。
	// mkdirs()方法创建一个文件夹和它的所有父文件夹。
	public static void mkdirs() {
		String dirname = "mkdirs/test";
		File d = new File(dirname);
		// 现在创建目录
		d.mkdirs();
		System.out.println("成功");
	}

	// 文件操作
	public static void fileStream() {
		try {
			File f = new File("mkdirs/test/filetest.txt");
			FileOutputStream fop = new FileOutputStream(f);
			// 构建FileOutputStream对象,文件不存在会自动新建

			OutputStreamWriter writer = new OutputStreamWriter(fop, "gbk");
			// 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk

			writer.append("中文输入");
			// 写入到缓冲区

			writer.append("\r\n");
			// 换行

			writer.append("English");
			// 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入

			writer.close();
			// 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉

			fop.close();
			// 关闭输出流,释放系统资源

			FileInputStream fip = new FileInputStream(f);
			// 构建FileInputStream对象

			InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
			// 构建InputStreamReader对象,编码与写入相同

			StringBuffer sb = new StringBuffer();
			while (reader.ready()) {
				sb.append((char) reader.read());
				// 转成char加到StringBuffer对象中
			}
			System.out.println(sb.toString());
			reader.close();
			// 关闭读取流

			fip.close();
			// 关闭输入流,释放系统资源
		} catch (Exception e) {
		}
	}

	//读取目录下的所有子目录和文件
	public static void dirList(String dirName) {
		File f1 = new File(dirName);
		if (f1.isDirectory()) {
			System.out.println("目录：" + dirName);
			String s[] = f1.list();
			for (int i = 0; i < s.length; i++) {
				dirList(dirName + "/" + s[i]);
			}
		} else {
			System.out.println("文件：" + dirName);
		}
	}
}
