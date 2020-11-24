package com.reflection.java2;

import com.reflection.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
调用运行时类中指定的结构：属性、方法、构造器
 */
public class ReflectionTest {
    /*

     */
    @Test
    public void testField() throws Exception{
        Class c = Person.class;
        //创建运行时类的对象
        Person p = (Person) c.newInstance();
        //获取指定的属性: 要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = c.getField("age");
        //设置当前属性的值
        /*
        set() ：参数1：指明设置哪个对象的属性 参数2：将此属性值设置为多少
         */
        id.set(p,1001);
        /*
        获取当前属性的值
        get()： 参数1： 获取哪个对象的当前属性值
         */
        int pId = (int) id.get(p);
        System.out.println(pId);
    }
    /*
    如何操作运行时类中指定的属性
     */
    @Test
    public void test1() throws Exception {
        Class c = Person.class;

        //创建运行时类的对象
        Person p = (Person) c.newInstance();
        //1、getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = c.getDeclaredField("name");
        //2、保证当前属性是可以访问的
        name.setAccessible(true);
        //3、获取、设置指定对象的此属性值
        name.set(p,"Tom");
        System.out.println(name.get(p));
    }
    /*
    如何操作运行时类中指定的方法
     */
    @Test
    public void test2() throws Exception{
        Class c = Person.class;
        //创建运行时类的对象
        Person p = (Person) c.newInstance();
        //1、获取指定的某个方法
        /*
        getDeclaredMethod(): 参数1： 指明获取的方法的名称 参数2：指明获取的方法的形参列表
         */
        Method show = c.getDeclaredMethod("show", String.class);
        //2、保证当前方法时可访问的
        show.setAccessible(true);
        /*
        invoke(): 参数1： 方法的调用者 参数2：给方法形参赋值的实参
        invoke() 的返回值即为对应类中调用的方法的返回值
         */
        String obj = (String) show.invoke(p,"CHN");
        System.out.println(obj);
        // 静态方法的调用
        Method showDesc = c.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        showDesc.invoke(Person.class);
        showDesc.invoke(null);
    }
    /*
    如何操作运行时类中指定的构造器
     */
    @Test
    public void test3() throws Exception{
        Class c = Person.class;
        /*
        1、获取指定的构造器
        getDeclaredConstructor(): 参数： 指明构造器的参数列表
         */
        Constructor declaredConstructor = c.getDeclaredConstructor(String.class);
        //2、保证此构造器是可访问的
        declaredConstructor.setAccessible(true);
        //3、 调用从构造器创建运行时类的对象
        Person p = (Person) declaredConstructor.newInstance("Tom");
        System.out.println(p.toString());
    }
}
