package com.web.programming.java;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 */
public class TCPTest {
    //客户端
    @Test
    public void client() {
        OutputStream os = null;
        Socket socket = null;
        try{
            //1、创建Socket对象，指明服务器端的IP和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            //2、获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3、写出数据的操作
            os.write("Hello, this is client".getBytes());

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //4、 资源的关闭
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //服务端
    @Test
    public void server(){
        InputStream is = null;
        ServerSocket ss = null;
        Socket socket = null;
        ByteArrayOutputStream baos = null;
        try{
            //1、创建服务器端的ServerSocket， 指明自己的端口号
            ss = new ServerSocket(8899);
            //2、调用accept()， 表示接收来自于客户端的Socket
            socket = ss.accept(); //listen for a connection to be made to this socket, block until a connection is made
            //3、获取输入流
            is = socket.getInputStream();
            //不建议这么写，中文占三个字节，那么20可能太小，造成乱码（以为读取到一半就满了，然后直接转换成string了）
//            byte[] buffer = new byte[20];
//            int len;
//            while((len = is.read()) != -1){
//                System.out.println(new String(buffer, 0, len));
//            }
            //4、读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            //这个方法是先全都以字节的方式读取并存下来，最后toString将整体转换成string
            while((len=is.read(buffer)) != -1){
                baos.write(buffer, 0 , len);
            }
            System.out.println(baos.toString());
            System.out.println("Received Message from " + socket.getInetAddress().getHostAddress());
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            //5、资源的关闭
            if(baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
