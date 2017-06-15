package zhongqiu.common.base.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ReadAndShow {
    static public void main(String args[]) throws Exception {
        FileInputStream fin = new FileInputStream("readandshow.txt");
        FileChannel fc = fin.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //通过通道读取数据到缓存区
        fc.read(buffer);

        buffer.flip();

        int i = 0;
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.println("Character " + i + ": " + ((char) b));
            i++;
        }

        fin.close();
    }
}
