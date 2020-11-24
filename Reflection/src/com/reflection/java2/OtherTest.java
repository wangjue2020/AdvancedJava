package com.reflection.java2;

import com.reflection.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
获取构造器结构
 */
public class OtherTest {
    @Test
    public void test() throws NoSuchMethodException {
        Class c  = Person.class;
        //getConstructors()：获取当前运行时类中声明为public的构造器
        Constructor[] constructors = c.getConstructors();
        for(Constructor constructor : constructors){
            System.out.println(constructor);
        }

        System.out.println("===================================");
        //getDeclaredConstructors() ： 获取当前运行时类中声明所有的构造器
        Constructor[] declaredConstructors = c.getDeclaredConstructors();
        for ( Constructor constructor : declaredConstructors){
            System.out.println(constructor);
        }
    }
    /*
        获取运行时类的父类
         */
    @Test
    public void test1(){
        Class c = Person.class;
        Class superclass = c.getSuperclass();
        System.out.println(superclass);
    }

    /*
    获取运行时类的带泛型父类
     */
    @Test
    public void test2(){
        Class c = Person.class;
        Type genericSuperclassType = c.getGenericSuperclass();
        System.out.println(genericSuperclassType);
    }
    /*
    获取运行时类的带泛型父类的泛型
     */
    @Test
    public void test3(){
        Class c = Person.class;
        Type genericSuperclassType = c.getGenericSuperclass();
        ParameterizedType paramType  = (ParameterizedType) genericSuperclassType;
        //获取泛型参数
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0]);
    }
}
