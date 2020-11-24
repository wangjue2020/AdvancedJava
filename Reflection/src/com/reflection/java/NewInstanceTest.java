package com.reflection.java;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 *
 */
public class NewInstanceTest {
    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        Class c = Person.class;
        /*
        newInstance() 调用此方法， 创建对应的运行时类的对象
        内部调用了运行时类的空参构造器， 所以在创建对象的时候还是需要用到类的构造器的
        要想此方法正常的创建运行时类的对象，要求：
         1、运行时类必须提供空参的构造器
         2、空参的构造器的访问权限需要reachable。 通常，设置为public
        在java bean中要求有一个public空参构造器，原因：
            1、便于通过反射，创建运行时类的对象
            2、便于子类继承此运行时类时，默认调用super() 时，保证父类有此构造器
         */

        Person obj = (Person) c.newInstance();
        System.out.println(obj);

        Class<Person> cp = Person.class;
        Person p = cp.newInstance();
        System.out.println(p);
    }
    /*
    在程序运行前不知道到底要create 什么样的对象，实现反射的动态性
     */
    @Test
    public void test1() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int num = new Random().nextInt(3);
        String classPath = "";
        switch(num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "com.reflection.java.Person";
                break;
        }
        Object obj = getInstance(classPath);
        System.out.println(obj);
    }
    /*
    创建一个指定类的对象。
    classPath：指定类的full class name
     */
    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(classPath);
        return c.newInstance();
    }
}
