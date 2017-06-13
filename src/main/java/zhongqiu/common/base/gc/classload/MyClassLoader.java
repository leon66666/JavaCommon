package zhongqiu.common.base.gc.classload;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader {
	@SuppressWarnings("resource")
	public static void main(String[] args)
			throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		URL url = new URL("file:/D:/javaworkspace/JavaCommon/src/");
		ClassLoader myloader = new URLClassLoader(new URL[] { url });
		Class c = myloader.loadClass("zhongqiu.common.base.classload.Test");
		Test t3 = (Test) c.newInstance();
	}
}
