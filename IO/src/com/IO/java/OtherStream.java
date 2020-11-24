package com.IO.java;

import org.junit.Test;

import java.io.*;

/**
 * 其他流的使用
 * 1、标准的输入、输出流
 * 2、打印流
 *      PrintStream, PrintWriter
 *      只有输出流， 可以通过将System.setOut() 改变System.out.println() 从控制台输出到指定文件输出
 * 3、数据流
 *      DataInputStream, DataOutputStream
 *      用于读取和写入基本数据类型
 */
public class OtherStream {
    /*
    1、标准的输入、输出流
        -- System.in：标准的输入流， 默认从键盘输入
           System.out: 标准的输出流， 默认从控制台输出
        -- System类的setIn(InputStream in)/setOut(PrintStream out) 方式重新指定输入和输出的流
        -- 练习：
            从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作
            直至当输入"e"或者"exit"时，退出程序
            方法一：使用Scanner实现， 调用next() 返回一个字符串
            方法二：使用System.in实现， System.in ---> BufferedReader 的readline()
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        while (true) {
            String data = br.readLine();
            if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)){
                System.out.println("game over!");
                break;
            }
            System.out.println(data.toUpperCase());

        }
        br.close();
    }

    /*
    数据流
    DataInputStream 和  DataOutputStream
    作用：用于读取或写出基本数据类型的变量或字符串
    练习：将内存中的字符串、基本数据类型的变量写出到文件中
     */
    @Test
    public void test() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("dataStream.txt"));
        dos.writeUTF("这是数据流");
        dos.flush();//刷新操作，将内存中的数据写入文件
        dos.writeInt(23);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();
        dos.close();
    }
    /*
    将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中
    caution： 读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致！
     */
    @Test
    public void test2() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("dataStream.txt"));

        String s = dis.readUTF();
        int i = dis.readInt();
        boolean b = dis.readBoolean();
        System.out.println(s + i + b);

    }
}
