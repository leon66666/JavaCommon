package zhongqiu.common.jdkversion.jdk5;

//内省机制
/*是 Java语言对Bean类属性、事件的一种缺省处理方法。
例如类A中有属性name,那我们可以通过getName,setName来得到其值或者设置新 的值。
通过getName/setName来访问name属性，这就是默认的规则。
Java中提供了一套API用来访问某个属性的getter /setter方法，
通过这些API可以使你不需要了解这个规则（但你最好还是要搞清楚），这些API存放于包java.beans中。
一 般的做法是通过类Introspector来获取某个对象的BeanInfo信息，
然后通过BeanInfo来获取属性的描述器 （PropertyDescriptor），
通过这个属性描述器就可以获取某个属性对应的getter/setter方法，然后我们就可以通过反射机制来 调用这些方法。*/
public class Introspector {

}
