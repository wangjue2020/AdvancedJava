package com.IO.java;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之二：转换流的使用
 * 1、 转换流：属于字符流
 *      InputStreamReader : 将一个字节的输入流转换为字符的输入流
 *      OutputStreamWriter： 将一个字符的输出流转换为字节的输出流
 * 2、 作用：提供字节流与字符流之间的转换
 * 3、 解码：字节、字节数组 ---》 字符数组、字符串
 *     编码：字符数组、字符串 ---》字节、字节数组
 * 4、 字符集
 *      ASCII: 美国标准信息交换码
 *      ISO8859-1： 拉丁码表。 欧洲码表， 用一个字节的8位表示
 *      GB2312： 中国的中文编码表，最多两个字节编码所有字符
 *      GBK：中国的中文编码表升级， 融合了更多的中文文字符号，最多两个字节编码
 *      Unicode： 国际标准码， 融合了目前人类使用的所有字符。为每个字符分配一个字符码。所有的文字都用两个字节来表示
 *      UTF-8： 变长的编码方式， 可能1-4个字节来表示字符
 *
 */
public class InputStreamReaderTest {
    /*
    此时处理异常的话，仍然应该使用try-catch-finally
    InputStreamReader 的使用，实现字节的输入流到字符的输入流的转换
     */
    @Test
    public void test1() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        FileInputStream fis = new FileInputStream("hello.txt");
//        InputStreamReader ir = new InputStreamReader(fis);//使用系统默认的字符集
        //参数2指明了字符集，具体使用那个字符集，取悦于文件hello.txt保存时用的字符集
        InputStreamReader ir = new InputStreamReader(fis, "UTF-8");

        char[] cbuf = new char[20];
        int len;
        while((len = ir.read(cbuf)) != -1){
            String str = new String(cbuf, 0, len);
            System.out.print(str);
        }
        ir.close();
    }

    /*
    综合使用InputStreamReader 和 OutputStreamWriter
     */
    @Test
    public void test2() throws IOException {
        File f1 = new File("hello.txt");
        File f2 = new File("hello_gbk.txt");
        FileInputStream fi = new FileInputStream(f1);
        FileOutputStream fo = new FileOutputStream(f2);
        InputStreamReader ir = new InputStreamReader(fi, "UTF-8");
        //读取和写入不用同一个字符集的话会出现乱码
        OutputStreamWriter ow = new OutputStreamWriter(fo, "GBK");
        char[] cbuf = new char[20];
        int len;
        while((len = ir.read(cbuf)) != -1) {
            ow.write(cbuf,0, len);
        }
        ir.close();
        ow.close();
    }
}
