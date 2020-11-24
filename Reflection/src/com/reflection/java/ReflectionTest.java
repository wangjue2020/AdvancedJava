package com.reflection.java;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 疑问：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用哪个？
 *      -->  建议：直接new的方式
 *      --> 什么时候会使用反射的方式， 反射的特征：动态性, 当我们无法在运行前得知要创建哪一个类的object时，
 *      --> 比如就是在登录页面，可以既用于登录，也可以用于注册，此时整个程序试运行着的，
 *      --> 只有在用户做出选择之后才能知道是要创建哪一类的对象，此时就可以用到反射机制去创建对象
 *
 * 疑问：反射机制与面向对象中的封装性是不是矛盾？如何看待两个技术
 *      不矛盾。封装性体现的时那些私有的方法、构造器、属性不建议被调用，
 *      可能在提供的公共的方法中会call到那些私有的方法、属性设置等，
 *      但是反射机制下的访问是说，非得使用私有的，也还是可以用
 *
 * 关于java.lang.Class 类的理解
 * 1、类的加载过程：
 *      程序经过javac.exe 命令以后，会生成一个或多个字节码文件(.class 结尾)
 *      接着我们使用java.exe 命令对某个字节码文件进行解释运行，相当于将某个字节码文件加载到内存中。此过程就称为类的加载。
 *      加载到内存中的类，我们就称为运行时类， 此运行时类，就作为Class的一个实例
 *
 * 2、 换句话说，Class的实例就对应着一个运行时类
 * 3、 加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式类获取此运行时类
 */
public class ReflectionTest {
    //反射之前，对于Person类的操作
    @Test
    public void test(){
        //1、创建Person类的对象
        Person p = new Person("Tom", 12);
        //2、通过对象，调用其内部的属性、方法
        p.age = 10;
        System.out.println(p.toString());
        p.show();
        //在Person类外部，不可以通过Person类的对象调用其内部私有的结构
        //比如 name、showNationality()以及私有的构造器
    }

    //反射之后，对于Person的操作
    @Test
    public void test1() throws Exception {
        Class c = Person.class;
        Constructor cons = c.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());
        //2、通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = c.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());
        //调用方法
        Method show = c.getDeclaredMethod("show");
        show.invoke(p);
        //通过反射，可以调用Person类的私有结构。比如私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons1 = c.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Lucy");
        System.out.println(p1.toString());
        //调用私有的属性
        Field  name = c.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "Judy");
        System.out.println(p1.toString());
        //调用私有方法
        Method showNationality = c.getDeclaredMethod("showNationality", String.class);
        showNationality.setAccessible(true);
        Object chn = showNationality.invoke(p1, "CHN");
        System.out.println(chn.toString());
    }
    //获取Class的实例的方式
    @Test
    public void test2() throws ClassNotFoundException {
        //方式一：调用运行时类的属性 .class
        Class cla = Person.class;
        System.out.println(cla);
        //方式二：通过运行时类的对象，调用getClass()
        Person p1 = new Person();
        Class cla1 = p1.getClass();
        System.out.println(cla1);
        //方式三：调用Class的静态方法 forName(String classPath)
        Class cla2 = Class.forName("com.reflection.java.Person");
        System.out.println(cla2);
        System.out.println(Class.forName("java.lang.String"));
        System.out.println(cla == cla1);
        System.out.println(cla == cla2);
        //方式四： 使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class cla4 = classLoader.loadClass("com.reflection.java.Person");
        System.out.println(cla4 == cla);
    }

}

class Person{
    private String name;
    public int age;

    public Person() {
    }
    private Person(String name) {
        this.name = name;
    }
    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }
    public void show(){
        System.out.println("Hi there, I am a person");
    }
    private String showNationality(String nation){
        System.out.println("My nationality is " + nation);
        return nation;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
