package com.IO.java;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
对图片加解密
 */
public class EncoderDecoder {
    /*
    对图片加密
     */
    public void encode(String src){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try{
            fi = new FileInputStream(src);
            fo = new FileOutputStream("encoded"+src);
            byte[] buffer = new byte[10];
            int len;
            while((len = fi.read(buffer)) != -1){
                for(int i = 0; i < len; i++){
                    buffer[i] =(byte) (buffer[i] ^ 5);
                }
                fo.write(buffer, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fi != null){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    对图片加密
     */
    public void decode(String src){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try{
            fi = new FileInputStream(src);
            fo = new FileOutputStream("decoded"+src);
            byte[] buffer = new byte[10];
            int len;
            while((len = fi.read(buffer)) != -1){
                for(int i = 0; i < len; i++){
                    buffer[i] =(byte) (buffer[i] ^ 5);
                }
                fo.write(buffer, 0, len);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fi != null){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testEncode(){
        encode("screenshot.jpg");
    }
    @Test
    public void testDecode(){
        encode("encodedscreenshot.jpg");
    }

}
