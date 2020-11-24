package com.IO.java;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的使用
 * 1、缓冲流
 *      BufferedInputStream
 *      BufferedOutputStream
 *      BufferedReader
 *      BufferedWriter
 * 2、 提高流的读取、写入的速度
 *      提高读写速度的原因：内部提供了一个缓冲区
 *
 * 3、 处理流， 就是"套接"在已有的留的基础上。用BufferedRead、BufferedWriter处理FileReader、FileWriter
 */
public class BufferedTest {
    /*
    实现非文本文件的复制
     */
    @Test
    public void BufferedStreamTest(){
        BufferedInputStream bi = null;
        BufferedOutputStream bo = null;
        try{
            //1、造文件
            File srcFile = new File("screenshot.jpg");
            File destFile = new File("destScreenShot.jpg");
            //2、造流
            //2.1 造节点流
            FileInputStream fi = new FileInputStream(srcFile);
            FileOutputStream fo = new FileOutputStream(destFile);
            //2.2 造缓冲流
            bi = new BufferedInputStream(fi);
            bo = new BufferedOutputStream(fo);
            //3、复制的细节：读取、写入
            byte[] buffer = new byte[10];
            int len;
            while ((len = bi.read(buffer)) != -1){
                bo.write(buffer, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //4、资源的关闭
            // 要求：先关闭外层的流，在关闭内层的流
            try {
                if (bi != null)
                    bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bo != null)
                    bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 说明： 在关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略
//            fi.close();
//            fo.close();
        }
    }

    public void copyFileUseBufferedStream(String src, String dest){
        BufferedInputStream bi = null;
        BufferedOutputStream bo = null;
        try{
            //1、造文件
            File srcFile = new File(src);
            File destFile = new File(dest);
            //2、造流
            //2.1 造节点流
            FileInputStream fi = new FileInputStream(srcFile);
            FileOutputStream fo = new FileOutputStream(destFile);
            //2.2 造缓冲流
            bi = new BufferedInputStream(fi);
            bo = new BufferedOutputStream(fo);
            //3、复制的细节：读取、写入
            byte[] buffer = new byte[5];
            int len;
            while ((len = bi.read(buffer)) != -1){
                bo.write(buffer, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //4、资源的关闭
            // 要求：先关闭外层的流，在关闭内层的流
            try {
                if (bi != null)
                    bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bo != null)
                    bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFileUsingBufferedStream(){
        long s = System.currentTimeMillis();
        copyFileUseBufferedStream("/Users/Wangjue/Downloads/jjw.mov","sample.mov");
        long e = System.currentTimeMillis();
        System.out.println(e - s);//70  比没有用处理流的快很多
    }
    /*
    使用BufferedReader  和 BufferedWriter 实现文本文件的复制
     */
    @Test
    public void testBufferedReaderWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{
            File rf = new File("../JavaSenior.iml");
            File wf = new File("sample.txt");
            FileReader fr = new FileReader(rf);
            FileWriter fw = new FileWriter(wf, true);
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);
            char[] buffer = new char[5];
            int len;
            while((len = br.read(buffer)) != -1) {
                bw.write(buffer, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
