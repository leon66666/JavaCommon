package zhongqiu.common.jdkversion.jdk7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//在try catch异常扑捉中，一个catch可以写多个异常类型，用"|"隔开
public class TryCatchDemo {
	public static void main(String[] args) {

		// jdk7之前
		try {
			BufferedReader reader = new BufferedReader(new FileReader(""));
			Connection con = null;
			Statement stmt = con.createStatement();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// jdk7
		try {
			BufferedReader reader = new BufferedReader(new FileReader(""));
			Connection con = null;
			Statement stmt = con.createStatement();
		} catch (IOException | SQLException e) {
			// 捕获多个异常，e就是final类型的 e.printStackTrace();
		}
	}
}
