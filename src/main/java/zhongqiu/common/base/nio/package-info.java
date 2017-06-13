/**
 * @author zhongqiu
 * https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 * ��Ҫ���ͨ����������
 * ����io������io���������ģ�nio�����򻺳����ġ�
 * ��ͳ��socket IO�У���ҪΪÿ�����Ӵ���һ���̣߳������������������ǳ��޴�ʱ���߳���ռ�õ�ջ�ڴ��CPU�߳��л��Ŀ������ǳ��޴�
 * ʹ��NIO��������ҪΪÿ���̴߳����������̣߳�������һ�������������̵߳��̳߳أ�����һ���߳���Ϊ�������������ӷ���
 * �����߳�����С����������������ÿ���߳̽���IO����ʱ�Ͳ�����������������Ļ�����Щ���Ӿ͵ò�������NIO�ṩ�����ַ�������������
 * һ������״̬������position��limit��capacity��position<=limit,limit<=capacity
 * buffer.flip(),�ı�position��limit��ֵ��
 * �������ʷ�����get()��put()
 * get() �� put() ����Է����;��Է�������Է����ڵ��ú��ı�position��ֵ�����Է�������ı�
 * ������������ʹ�á�FileChannel.read() ��FileChannel. write() ���õõ��˼���ļ򻯣�
 * ��Ϊ��๤��ϸ�ڶ��ɻ���������ˡ�buffer. clear() �� buffer.flip() ���������û������ڶ���д֮���л���buffer.rewind(),����
 * �ġ�����������Ͱ�װ�����䣺ByteBuffer.allocate( 1024 )��
 * ��װ��byte array[] = new byte[1024];
 * ByteBuffer buffer = ByteBuffer.wrap( array );
 * �塢��������Ƭ�����ݹ������������ӻ���������ͬһ���ײ���������
 * ��Ƭ���ӻ���������buffer.position( 3 );buffer.limit( 7 );ByteBuffer slice = buffer.slice();
 * ����ֻ����������ͨ�����û������� asReadOnlyBuffer() ���������κγ��滺����ת��Ϊֻ����������
 * �����������һ����ԭ��������ȫ��ͬ�Ļ�����(�����乲������)��ֻ��������ֻ���ġ�  ���ܽ�ֻ���Ļ�����ת��Ϊ��д�Ļ�������
 * �ߡ�ֱ�Ӻͼ�ӻ������� FastCopyFile.java��ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );
 * �ˡ����ļ�ӳ�䵽�ڴ档FileChannel.map
 * �š���ɢ�;ۼ������������顢
 * ʮ������IO��ServerSocketChannel��SocketChannel,ServerSocket,selector,
 * ʮһ���ļ�������FileChannel.lock()��ȡ�Դ�ͨ�����ļ��Ķ�ռ����;FileLock.release()�ͷ���
 */
/**
 * @author zhongqiu
 *https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 *��Ҫ���ͨ����������
 *����io������io���������ģ�nio�����򻺳����ġ�
 *��ͳ��socket IO�У���ҪΪÿ�����Ӵ���һ���̣߳������������������ǳ��޴�ʱ���߳���ռ�õ�ջ�ڴ��CPU�߳��л��Ŀ������ǳ��޴�
 *ʹ��NIO��������ҪΪÿ���̴߳����������̣߳�������һ�������������̵߳��̳߳أ�����һ���߳���Ϊ�������������ӷ���
 *�����߳�����С����������������ÿ���߳̽���IO����ʱ�Ͳ�����������������Ļ�����Щ���Ӿ͵ò�������NIO�ṩ�����ַ�������������
 *һ������״̬������position��limit��capacity��position<=limit,limit<=capacity
 *   buffer.flip(),�ı�position��limit��ֵ��
 *�������ʷ�����get()��put()
 *   get() �� put() ����Է����;��Է�������Է����ڵ��ú��ı�position��ֵ�����Է�������ı�
 *������������ʹ�á�FileChannel.read() ��FileChannel. write() ���õõ��˼���ļ򻯣�
 *   ��Ϊ��๤��ϸ�ڶ��ɻ���������ˡ�buffer. clear() �� buffer.flip() ���������û������ڶ���д֮���л���buffer.rewind(),����
 *�ġ�����������Ͱ�װ�����䣺ByteBuffer.allocate( 1024 )��
 *               ��װ��byte array[] = new byte[1024];
ByteBuffer buffer = ByteBuffer.wrap( array );
 *�塢��������Ƭ�����ݹ������������ӻ���������ͬһ���ײ���������
 *   ��Ƭ���ӻ���������buffer.position( 3 );buffer.limit( 7 );ByteBuffer slice = buffer.slice();    
 *����ֻ����������ͨ�����û������� asReadOnlyBuffer() ���������κγ��滺����ת��Ϊֻ����������
 *   �����������һ����ԭ��������ȫ��ͬ�Ļ�����(�����乲������)��ֻ��������ֻ���ġ�  ���ܽ�ֻ���Ļ�����ת��Ϊ��д�Ļ�������
 *�ߡ�ֱ�Ӻͼ�ӻ������� FastCopyFile.java��ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );
 *�ˡ����ļ�ӳ�䵽�ڴ档FileChannel.map
 *�š���ɢ�;ۼ������������顢
 *ʮ������IO��ServerSocketChannel��SocketChannel,ServerSocket,selector,
 *ʮһ���ļ�������FileChannel.lock()��ȡ�Դ�ͨ�����ļ��Ķ�ռ����;FileLock.release()�ͷ���
 */
package zhongqiu.common.base.nio;