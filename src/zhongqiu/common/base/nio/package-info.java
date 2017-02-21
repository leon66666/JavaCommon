/**
 * 
 */
/**
 * @author zhongqiu
 *https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
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
  
 *五、
                  
 */
package zhongqiu.common.base.nio;