package zhongqiu.common.base;

//异常
//http://www.runoob.com/java/java-exceptions.html
public class ThrowableDemo extends Throwable {
    // Throwable包含了错误(Error)和异常(Exception两类)
    // Exception又包含了运行时异常(RuntimeException, 又叫非检查异常)和非运行时异常(又叫检查异常)
    // (1) Error是程序无法处理了, 如果OutOfMemoryError等等, 这些异常发生时,java虚拟机一般会终止线程.
    // (2) 运行时异常都是RuntimeException类及其子类,如
    // NullPointerException、IndexOutOfBoundsException等, 这些异常是不检查的异常,
    // 是在程序运行的时候可能会发生的, 所以程序可以捕捉, 也可以不捕捉. 这些错误一般是由程序的逻辑错误引起的, 程序应该从逻辑角度去尽量避免.
    // (3) 检查异常是运行时异常以外的异常, 也是Exception及其子类, 这些异常从程序的角度来说是必须经过捕捉检查处理的, 否则不能通过编译.
    // 如IOException、SQLException等
    public static void main(String[] args) {
//        OutOfMemoryError
    }
}
