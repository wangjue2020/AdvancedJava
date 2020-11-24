package com.IO.java;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class CombineFiles {
    public static void main(String[] args) {
        Scanner s = null;
        BufferedOutputStream bo = null;
        try {
            s = new Scanner(new File("/Users/Wangjue/IdeaProjects/JavaSenior/IO/hello.txt"));
            String destination =  s.nextLine();
            System.out.println(destination);
            File wf = new File(destination+File.separator+"result.pdf");
            FileOutputStream fo = new FileOutputStream(wf, true);
            bo = new BufferedOutputStream(fo);
            File rf;
            while (s.hasNextLine()){
                String src = s.nextLine();
                System.out.println(src);
                rf = new File(src);
                copyFile(bo, rf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            s.close();
        }

    }
    public static void copyFile(BufferedOutputStream bo, File rf){
        BufferedInputStream bi = null;
        try{
            FileInputStream fi = new FileInputStream(rf);
            bi = new BufferedInputStream(fi);
            byte[] buf = new byte[10];
            int len;
            while((len = bi.read(buf)) != -1) {
                bo.write(buf, 0, len);
            }
            bo.write('\f');
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(bi != null){
                try {
                    bi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test(){
        copy("hello.txt", "result.txt");
        copy("hello.txt", "result.txt");
//        copy("test.txt", "test.pdf");

    }

    public void copy(String src, String dest){
        File srcF = new File(src);
        File destF = new File(dest);
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try{
            fi = new FileInputStream(srcF);
            fo = new FileOutputStream(destF, true);
            byte[] buf = new byte[20];
            int len;
            while((len = fi.read(buf)) != -1){
                fo.write(buf, 0, len);
            }
//            fo.write('\f');
        } catch ( IOException e){
            e.printStackTrace();
        } finally {
            if(fi != null){
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fo != null){
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
