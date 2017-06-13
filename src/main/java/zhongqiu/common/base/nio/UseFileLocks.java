package zhongqiu.common.base.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class UseFileLocks {
    static private final int start = 10;
    static private final int end = 20;

    static public void main(String args[]) throws Exception {
        new Thread(new Runnable() {
            public void run() {
                // Get file channel
                RandomAccessFile raf = null;
                try {
                    raf = new RandomAccessFile("usefilelocks.txt", "rw");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                FileChannel fc = raf.getChannel();

                // Get lock
                System.out.println("trying to get lock");
                FileLock lock = null;
                try {
                    lock = fc.lock(start, end, false);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("got lock!");

                // Pause
                System.out.println("pausing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                }

                // Release lock
                System.out.println("going to release lock");
                try {
                    lock.release();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("released lock");

                try {
                    raf.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                // Get file channel
                RandomAccessFile raf = null;
                try {
                    raf = new RandomAccessFile("usefilelocks.txt", "rw");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                FileChannel fc = raf.getChannel();

                // Get lock
                System.out.println("trying to get lock");
                FileLock lock = null;
                try {
                    lock = fc.lock(start, end, false);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("got lock!");

                // Release lock
                System.out.println("going to release lock");
                try {
                    lock.release();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("released lock");

                try {
                    raf.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
