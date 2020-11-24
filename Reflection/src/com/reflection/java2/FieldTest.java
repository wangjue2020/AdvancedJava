package com.reflection.java2;

import com.reflection.java1.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性结构
 */
public class FieldTest {
    @Test
    public void test(){
        Class cla = Person.class;
        //获取属性结构
        //getFields(): 获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = cla.getFields();
        for(Field f: fields){
            System.out.println(f);
        }
        System.out.println("===============");
        //getDeclaredFields(): 获取当前运行时类中声明的所有属性。（不包括父类中声明的属性）
        Field[] fields1 = cla.getDeclaredFields();
        for(Field f: fields1){
            System.out.println(f);
        }
    }
    //权限修饰符 数据类型 变量名
    @Test
    public void test1(){
        Class c = Person.class;
        Field[] declaredFields = c.getDeclaredFields();
        for(Field f: declaredFields){
            //1、权限修饰符
            // 0-default 1-public 2-private
            int modifiers = f.getModifiers();
            System.out.println(modifiers);
            System.out.println(Modifier.toString(modifiers));
            //2、数据类型
            Class type = f.getType();
            System.out.println(type);
            //3、变量名
            String name = f.getName();
            System.out.println(name);
            System.out.println("==================");

        }
    }
}
