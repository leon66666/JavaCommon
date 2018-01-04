package zhongqiu.common.base;

import java.nio.BufferOverflowException;

//异常
//http://www.runoob.com/java/java-exceptions.html
public class ThrowableDemo extends Throwable {
    /*   Throwable包含了错误(Error)和异常(Exception两类)
         Exception又包含了运行时异常(RuntimeException, 又叫非检查异常)和非运行时异常(又叫检查异常)
         (1) Error是程序无法处理了,  这些异常发生时,java虚拟机一般会终止线程.
             OutOfMemoryError,内存溢出
             StackOverflowError，堆栈溢出
         (2) 运行时异常都是RuntimeException类及其子类,
             是在程序运行的时候可能会发生的, 所以程序可以捕捉, 也可以不捕捉. 这些错误一般是由程序的逻辑错误引起的, 程序应该从逻辑角度去尽量避免                        NullPointerException  空指针异常
             IndexOutOfBoundsException 数组越界异常
             ClassCastException  类转换异常
             ArrayStoreException  数组存储异常
             nio.BufferOverflowException  缓冲区溢出
         (3) 检查异常是运行时异常以外的异常, 也是Exception及其子类, 这些异常从程序的角度来说是必须经过捕捉检查处理的, 否则不能通过编译.
             IOException
             SQLException
             ClassNotFoundException，继承自ReflectiveOperationException
             FileNotFoundException，继承自ReflectiveOperationException
             ReflectiveOperationException
             InterruptedException
               当某个线程处于长时间的等待、休眠或其他暂停状态，而此时其他的线程通过Thread的interrupt方法终止该线程时抛出该异常。
         (4) throw语句用在方法体内，表示抛出异常，由方法体内的语句处理。
             throws语句用在方法声明后面，表示再抛出异常，由该方法的调用者来处理。*/
    public static void main(String[] args) {
//        OutOfMemoryError
//        BufferOverflowException
//        StackOverflowError
    }
}
