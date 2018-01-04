package zhongqiu.common.base;

//Switch用法
/*
* switch(A),括号中A的取值只能是整型或者可以转换为整型的数值类型，比如byte、short、int、char、还有枚举；
  需要强调的是：long和String类型是不能作用在switch语句上的。
* case B：C；case是常量表达式，也就是说B的取值只能是常量（需要定义一个final型的常量）
  或者int、byte、short、char（比如1、2、3、200000000000（注意了这是整型）），
  而case后的常量值与switch后的表达式相对应。 case后的语句可以不用大括号，就是C不需要用大括号包裹着；
* default就是如果没有符合的case就执行它,default并不是必须的.
* 一旦case匹配,就会顺序执行后面的程序代码,而不管后面的case是否匹配,直到遇见break,利用这一特性可以让好几个case执行统一语句.
* switch中可以使用字串了。这个新特性是在编译器这个层次上实现的
*/
public class SwitchDemo {
    public static void main(String[] args) {
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


        final int i = 3;
        switch (i) {
            case '1':
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
