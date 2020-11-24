package com.IO.java;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 一、流的分类
 *  1、操作数据单位：字节流、字符流
 *  2、数据的流向：输入流、输出流
 *  3、流的角色： 节点流、数据流
 *
 * 二、流的体系结构
 * 抽象基类                 节点流（或文件流）           缓冲流（处理流的一种）
 * InputStream              FileInputStream             BufferedInputStream
 * OutputStream             FileOutputStream            BufferedOutputStream
 * Reader                   FileReader                  BufferedReader
 * Writer                   FileWriter                  BufferedWriter
 * */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");//相较于当前工程
        System.out.println(file.getAbsolutePath());
        file = new File("IO/hello.txt");
        System.out.println(file.getAbsolutePath());
    }
    /*
    将IO下的hello.txt 文件内容读入程序中，并输出到控制台

    说明点：
    1、read() 的理解： 返回读入的一个字符。如果达到文件末尾，返回-1
    2、异常的处理：为了保证流资源一定可以执行关闭操作， 需要使用try-catch-finally 处理
    3、读入的文件一定要存在，否则就会报FileNotFoundException
     */
    @Test
    public void testFileReader() {
        FileReader fr = null;
        try {
            //1、实例化File类的对象，指明要操作的文件
            File f = new File("hello.txt");
            //2、提供具体的流
            fr = new FileReader(f);
            //3、数据的读入
            //read() ： 返回读入的一个字符。如果达到文件末尾，返回-1
            int read ;
            while((read = fr.read()) != -1){
                System.out.print((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //对read（）操作升级：使用read的重载方法
    @Test
    public void testFileReader1(){
        FileReader fr=null;
        //1.File类的实例化
        File f = new File("hello.txt");
        //2. FileReader流的实例化
        try {
            fr = new FileReader(f);
            //3. 读入的操作
            //read(char[] cbuf): 返回每次读入cbuf数组中的字符的个数，如果达到末尾，返回-1
            char[] cbuf = new char[(int) f.length()];
            System.out.println(f.length());
            int len;
            while((len = fr.read(cbuf)) != -1){
                String s = new String(cbuf, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 资源的关闭
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    从内存中写出数据到硬盘的文件里
    说明：
    1、输出操作，对应的File可以不存在。并不会报异常
        File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件
        File对应的硬盘中的文件如果存在：
            如果流使用的构造器是FileWriter(file, false)/ FileWriter(file), write内容是对原有文件的覆盖（即overwrite）
            如果流使用的构造器是FileWriter(file, true)， write 内容是对原有文件的尾部接着添加而不是整个覆盖
     */
    @Test
    public void testFileWriter() throws IOException {
        //1、提供File类的对象，指明写出到的文件
        File f = new File( "sample.txt");
        //2、提供FileWriter的对象，用于数据的写出
        FileWriter fw = new FileWriter(f,true);
        //3、写出的操作
        fw.write("Today is Sunday");
        fw.write("Tomorrow is Monday");
        //4、流资源的关闭
        fw.close();
    }

    @Test
    public void testFileReaderFileWriter(){
        FileReader fr = null;
        FileWriter fw = null;
        try{
            //1、 创建File类的对象，指明读入和写出的对象
            File rf = new File("IO.iml");
            File wf = new File("hello.txt");
            //2、 创建输入流和输出流的对象
            fr = new FileReader(rf);
            fw = new FileWriter(wf,true);
            //3、数据的读入和写出操作
            char[] buf = new char[5];
            int res;
            while ((res = fr.read(buf)) != -1){
                fw.write(new String(buf,0, res));
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //4、关闭流资源
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fw!=null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
