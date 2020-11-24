package com.IO.java;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试FileInputStream 和 FileOutputStream 的使用
 * 结论：
 * 1、 对于文本文件（.txt, .java, .c, .cpp)，使用字符流处理
 * 2、对于非文本文件(.jpg, .mp3, .mp4, .doc, .ppt,...)，使用字节流处理
 *      doc 文件不属于文本文件，因为它可以包含图片
 * 3、 对于完整的读入写出问题，文本文件也可以用字节流处理，只有读入的话不提倡字节流处理。
 */
public class FileInputOutputStream {
    /*
    使用字节流FileInputStream处理文本文件，可能出现乱码，英文没事，但是对于中文或者其他字符可能出现不适宜
     */
    @Test
    public void testFileInputStream(){
        FileInputStream fs = null;
        try {
            //  1、 造文件
            File f = new File("hello.txt");
            //2、 造流
            fs = new FileInputStream(f);
            //3、读数据
            byte[] buffer = new byte[5];
            int len;//记录每次读去的字节的个数
            while ((len = fs.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null){
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    实现对图片的复制操作
     */
    @Test
    public void testFileInputOutputStream(){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try{
            File inf = new File("/Users/Wangjue/Desktop/screenshot.png");
            File outf = new File("screenshot.jpg");
            fi = new FileInputStream(inf);
            fo = new FileOutputStream(outf);
            byte[] buffer = new byte[5];
            int len;
            while((len = fi.read(buffer)) != -1){
                fo.write(buffer,0, len);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(fi != null)
                    fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fo != null)
                    fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //指定路径下文件的复制
    public void copyFile(String srcPath, String destPath){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try{
            File inf = new File(srcPath);
            File outf = new File(destPath);
            fi = new FileInputStream(inf);
            fo = new FileOutputStream(outf);
            byte[] buffer = new byte[5];
            int len;
            //复制粘贴的过程
            while((len = fi.read(buffer)) != -1){
                fo.write(buffer,0, len);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(fi != null)
                    fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fo != null)
                    fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFile(){
        long start = System.currentTimeMillis();

        copyFile("/Users/Wangjue/Downloads/jjw.mov", "sample.mov");
        long end = System.currentTimeMillis();
        System.out.println(end - start); //1949
    }
}
