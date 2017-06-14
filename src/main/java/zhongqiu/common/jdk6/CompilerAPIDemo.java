package zhongqiu.common.jdk6;

/*在我们可以用JDK6 的Compiler API(JSR 199)去动态编译Java源文件，
Compiler API结合反射功能就可以实现动态的产生Java代码并编译执行这些代码，有点动态语言的特征。
这个特性对于某些需要用到动态编译的应用程序相当有用，比如JSP Web Server，
当我们手动修改JSP后，是不希望需要重启Web Server才可以看到效果的，
这时候我们就可以用Compiler API来实现动态编译JSP文件，当然，现在的JSP Web Server也是支持JSP热部署的，
现在的JSP Web Server通过在运行期间通过Runtime.exec或ProcessBuilder来调用javac来编译代码，
这种方式需要我们产生另一个进程去做编译工作，不够优雅而且容易使代码依赖与特定的操作系统；
Compiler API通过一套易用的标准的API提供了更加丰富的方式去做动态编译,而且是跨平台的。*/
public class CompilerAPIDemo {

}
