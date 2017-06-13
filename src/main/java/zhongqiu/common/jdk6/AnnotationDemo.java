package zhongqiu.common.jdk6;

//插入式注解处理api
public class AnnotationDemo {
	/*
	 * 插入式注解处理API(JSR 269)提供一套标准API来处理Annotations.JSR 269用Annotation
	 * Processor【在编译期间而不是运行期间处理】Annotation, Annotation Processor相当于编译器的一个插件,
	 * 
	 * 所以称为插入式注解处理.如果Annotation
	 * Processor处理Annotation时(执行process方法)产生了新的Java代码,编译器会再调用一次Annotation
	 * Processor,如果第二次处理还有新代码产生,就会接着调用Annotation
	 * Processor,直到没有新代码产生为止.每执行一次process()方法被称为一个"round",这样整个Annotation
	 * processing过程可以看作是一个round的序列.
	 * 举个例子：们想建立一套基于Annotation的单元测试框架(如TestNG),在测试类里面用Annotation来标识测试期间需要执行的测试方法
	 */
	public @interface TestMethod {

	}
	@TestMethod
	 public void testCheckName(){  
	       //do something here  
	 }
}
