/**
 * 
 */
/**
 * @author zhongqiu
 *https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 *重要概念：通道，缓冲区
 *区别：io是面向io，面向流的；nio是面向缓冲区的
 *
 *一、三个状态变量：position，limit，capacity。position<=limit,limit<=capacity
 *   buffer.flip(),改变position和limit的值。
 *   
 *二、访问方法：get()和put()
 *   get() 和 put() 的相对方法和绝对方法，相对方法在调用后会改变position的值，绝对方法不会改变
 *   
 *三、缓存区的使用。FileChannel.read() 和FileChannel. write() 调用得到了极大的简化，
 *   因为许多工作细节都由缓冲区完成了。 clear() 和 flip() 方法用于让缓冲区在读和写之间切换。
 *   
 *四、缓冲区分配和包装。分配：ByteBuffer.allocate( 1024 )；
 *               包装：byte array[] = new byte[1024];
                     ByteBuffer buffer = ByteBuffer.wrap( array );
  
 *五、缓冲区分片和数据共享。缓冲区和子缓冲区共享同一个底层数据数组
 *   分片（子缓冲区）：buffer.position( 3 );
         buffer.limit( 7 );
         ByteBuffer slice = buffer.slice();    
 *六、只读缓冲区。通过调用缓冲区的 asReadOnlyBuffer() 方法，将任何常规缓冲区转换为只读缓冲区，
 *   这个方法返回一个与原缓冲区完全相同的缓冲区(并与其共享数据)，只不过它是只读的。  不能将只读的缓冲区转换为可写的缓冲区。
 *七、直接和间接缓冲区。 FastCopyFile.java。ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );
 *
 *
 */
package zhongqiu.common.base.nio;

import java.nio.ByteBuffer;
