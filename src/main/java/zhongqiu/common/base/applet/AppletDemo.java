package zhongqiu.common.base.applet;

import java.applet.Applet;
import java.awt.Graphics;

//Applet
//http://www.runoob.com/java/java-applet-basics.html
@SuppressWarnings("serial")
public class AppletDemo extends Applet{
	public void paint (Graphics g)
	   {
	      g.drawString ("Hello World", 25, 50);
	   }
}
