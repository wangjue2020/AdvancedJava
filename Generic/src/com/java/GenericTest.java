package com.java;

import org.junit.Test;

/**
 * 如何自定义泛型结构：泛型类、泛型接口、泛型方法
 * 1。 关于自定义泛型类、泛型接口
 */
public class GenericTest {
    public static void main(String[] args) {
        //如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
        //要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型
        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("ABC");

        //建议：实例化指明类的泛型
        Order<String> order1 = new Order<String>("orderAA", 123, "orderGeneric");
        order1.setOrderT("test1");


    }
    @Test
    public void test(){
        SubOrder sub = new SubOrder();
        //由于子类在继承泛型的父类时，指明了泛型类型。则在实例化子类对象时，不再需要指明泛型。
        sub.setOrderT(1122);

        SubOrder1<String> sub1 = new SubOrder1<>();
        sub1.setOrderT("hello");
    }
}

