package com.javabasic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

public class PropertiesTest {
    //Properties: 常用来处理配置文件，key和value都是String 类型
    public static void main(String[] args)  {
        LinkedHashMap m = new LinkedHashMap();
        FileInputStream f = null;
        try{
            Properties pros = new Properties();
            f = new FileInputStream("propertiesTest.properties");
            pros.load(f);//加载对应流文件
            String university = pros.getProperty("university");
            String department = pros.getProperty("department");
            String name = pros.getProperty("name");
            //university=University of Toronto, department=Computer Science, name=null
            System.out.println("university="+university+", department="+department+", name="+name);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
