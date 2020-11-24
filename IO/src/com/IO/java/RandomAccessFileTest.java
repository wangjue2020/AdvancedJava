package com.IO.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile 的使用
 * 1、 RandomAccessFile 直接继承于java.lang.Object 类， 实现了DataInput 和 DataOutput 接口
 * 2、 RandomAccessFile 既可以作为一个输入流，又可以作为一个输出流
 * 3、  new RandomAccessFile(new File("screenshot.jpg"),"r"); 第二个实参是mode， mode一共有四种
 *      "r" 以只读的方式打开
 *      "rw" 打开以便读取和写入
 *      "rwd" 打开以便读取和写入；同步文件内容的更新
 *      "rws" 打开以便读取和写入； 同步文件内容和元数据的更新
 * 4、 如果RandomAccessFile 作为输出流时，写出到文件的文件如果不存在，则在执行过程中自动创建
 *     如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖，能覆盖多少就看要写入多少
 * 5、 可以通过相关的操作，实现RandomAccessFile "插入"数据的效果， 灵活运用seek(int index) 的设置指针位置的方法
 *      i. 先把插入位置后面的读取下来
 *      ii. 插入所需要插入的内容
 *      iii. 接着插入之前读取下来的数据
 * 6、 RandomAccessFile类的应用：
 *      eg. 可以用这个类来实现一个多线程断点下载的功能，在用下载工具的时候，下载前都会建立两个临时的文件，
 *          一个是与被下载文件大小相同的空文件，另一个是记录文件指针的位置文件，每次暂停的时候，都会保存上一次的指针，
 *          然后断点下载的时候，会继续从上一次的地方开始下载，从而实现断点下载或上传的功能
 *
 */
public class RandomAccessFileTest {

    @Test
    public void test(){
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try{
            raf1 = new RandomAccessFile(new File("screenshot.jpg"),"r");
            raf2 = new RandomAccessFile(new File("screenshot1.jpg"),"rw");
            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer))!=-1){
                raf2.write(buffer, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void test1() throws IOException {
        RandomAccessFile rf = new RandomAccessFile("hello.txt", "rw");
        rf.write("zyx".getBytes());
        rf.close();
    }
    @Test
    public void test2() throws IOException {
        RandomAccessFile rf = new RandomAccessFile("hello.txt", "rw");
        rf.seek(3);//将指针调到角标为3的位置
        rf.write("zyx".getBytes());//始终都覆盖位置原有的内容
        rf.close();
    }
    /*
    使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void test3() throws IOException {
        RandomAccessFile rf = new RandomAccessFile("hello.txt", "rw");
        rf.seek(3);//将指针调到角标为3的位置
        byte[] buffer = new byte[20];
        //保存指针在index 3 后面的所有数据到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        int len;
        while((len = rf.read(buffer)) != -1){
            builder.append(new String(buffer, 0, len));
        }
        //调回指针到指定插入位置，进行插入操作
        rf.seek(3);
        rf.write("zyx".getBytes());
        //将stringBuilder中的数据写入到文件中
        rf.write(builder.toString().getBytes());
        rf.close();
    }
}
