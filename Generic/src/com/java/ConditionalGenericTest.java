package com.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * 有限制条件的通配符的使用
 * ? extends A: G<? extends A> 可以作为G<A> 和 G<B> 的父类，其中B是A的子类 （ 任何A类或者A的子类都通用）
 * ? super A: G<? super A> 可以作为G<A> 和 G<B> 的父类，其中B是A的父类 （任何A类或者A的父类都通用）
 */
class Person {
}
class Student extends Person{
}
public class ConditionalGenericTest {
    @Test
    public void test1(){
        List<? extends Person> l1 = null;
        List<? super Person> l2 = null;

        List<Student> l3 = new ArrayList<>();
        List<Person> l4 = new ArrayList<>();
        List<Object> l5 = new ArrayList<>();

        l1 = l3;
        l1 = l4;
//        l1 = l5;

//        l2 = l3;
        l2 = l4;
        l2 = l5;
        //读取数据
        Person person = l1.get(0); // Person 是List<? extends Person> 最大的父类， 从Person开始无限向下
//        Student stu = l1.get(0); // l1可以被List<Person> 赋值，不适用于Student
        Object object = l2.get(0);// List<? super Person> 从Person 开始一直向上直到Object， 所以Object是最大的父类
//        Person obj = l2.get(0); //l2 可以被List<Object> 赋值， 不可以自动向下转型
        //写入数据
//        l1.add(new Student()); //从Person开始无限向下， l1的元素类型可能是student的子类，于是就不能自动向下转型
        l2.add(new Student()); //因为student extends person， 所以student 一定可以是person， 可以是其向上每一层父类的对象

    }
}
