/**
 *
 */
/**
 * @author zhongqiu
 *https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 *重要概念：通道，缓冲区
 *区别：io是面向io，面向流的；nio是面向缓冲区的。
 *传统的socket IO中，需要为每个连接创建一个线程，当并发的连接数量非常巨大时，线程所占用的栈内存和CPU线程切换的开销将非常巨大。
 *使用NIO，不再需要为每个线程创建单独的线程，可以用一个含有限数量线程的线程池，甚至一个线程来为任意数量的连接服务。
 *由于线程数量小于连接数量，所以每个线程进行IO操作时就不能阻塞，如果阻塞的话，有些连接就得不到处理，NIO提供了这种非阻塞的能力。
 *一、三个状态变量：position，limit，capacity。position<=limit,limit<=capacity
 *   buffer.flip(),改变position和limit的值。
 *二、访问方法：get()和put()
 *   get() 和 put() 的相对方法和绝对方法，相对方法在调用后会改变position的值，绝对方法不会改变
 *三、缓存区的使用。FileChannel.read() 和FileChannel. write() 调用得到了极大的简化，
 *   因为许多工作细节都由缓冲区完成了。buffer. clear() 和 buffer.flip() 方法用于让缓冲区在读和写之间切换。buffer.rewind(),重绕
 *四、缓冲区分配和包装。分配：ByteBuffer.allocate( 1024 )；
 *               包装：byte array[] = new byte[1024];
ByteBuffer buffer = ByteBuffer.wrap( array );
 *五、缓冲区分片和数据共享。缓冲区和子缓冲区共享同一个底层数据数组
 *   分片（子缓冲区）：buffer.position( 3 );buffer.limit( 7 );ByteBuffer slice = buffer.slice();
 *六、只读缓冲区。通过调用缓冲区的 asReadOnlyBuffer() 方法，将任何常规缓冲区转换为只读缓冲区，
 *   这个方法返回一个与原缓冲区完全相同的缓冲区(并与其共享数据)，只不过它是只读的。  不能将只读的缓冲区转换为可写的缓冲区。
 *七、直接和间接缓冲区。 FastCopyFile.java。ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );
 *八、将文件映射到内存。FileChannel.map
 *九、分散和聚集。缓冲区数组、
 *十、网络IO。ServerSocketChannel，SocketChannel,ServerSocket,selector,
 *十一、文件锁定。FileChannel.lock()获取对此通道的文件的独占锁定;FileLock.release()释放锁
 */
package zhongqiu.common.base.nio;