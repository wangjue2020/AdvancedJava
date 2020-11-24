package com.reflection.java;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {
    /*
    Properties  读取配置文件
     */
    @Test
    public void test() throws IOException {
        Properties prop = new Properties();
        //此时的文件默认在当前module下
        //读取配置文件的方式之一：
        FileInputStream fs = new FileInputStream("reflectionTest.properties");
        prop.load(fs);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        System.out.println("username = " + username + ", password = " + password);
        //读取配置文件的方式二：使用ClassLoader
        //配置文件默认识别为当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("reflectionTest1.properties");
        prop.load(is);
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        System.out.println("username = " + username + ", password = " + password);

    }
}
