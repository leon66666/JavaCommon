package zhongqiu.common.base;


//根据程序上下文环境，Java关键字final有“这是无法改变的”或者“终态的”含义，它可以修饰非抽象类、非抽象类成员方法和变量。
//
//【1】final类不能被继承，没有子类，final类中的方法默认是final的。
//
//【2】final方法不能被子类的方法覆盖，但可以被继承。
//
//【3】final成员变量表示常量，只能被赋值一次，赋值后值不再改变。
//
//【4】final不能用于修饰构造方法。
//
//【5】注意：父类的private成员方法是不能被子类方法覆盖的，因此private类型的方法默认是final类型的。
public class FinalDemo {

}
