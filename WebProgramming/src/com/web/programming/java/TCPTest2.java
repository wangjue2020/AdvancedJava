package com.web.programming.java;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题2： 客户端发送文件到服务端，服务端将文件保存到本地
 */
public class TCPTest2 {
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fi = null;
        try{
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            outputStream = socket.getOutputStream();
            fi = new FileInputStream(new File("lib/test.txt"));
            byte[] buffer = new byte[20];
            int len;
            while((len = fi.read(buffer)) != -1){
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if( socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void server(){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        FileOutputStream fo = null;
        InputStream is = null;
        try{
            serverSocket = new ServerSocket(8899);
            clientSocket = serverSocket.accept();
            fo = new FileOutputStream("lib/received_file.txt");
            is = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fo.write(buffer,0,len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if ( fo != null) {
                try {
                    fo.close();
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
            if (clientSocket != null){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
