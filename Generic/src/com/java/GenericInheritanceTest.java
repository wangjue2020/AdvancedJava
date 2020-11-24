package com.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericInheritanceTest {
    /*
    1. 泛型在继承方面的体现
        类A是类B的父类， G<A> 和 G<B> 二者不具备子父类关系，二者是并列关系
        补充：类A和类B的父类， A<G> 是 B<G>  的父类
    */
    @Test
    public void Test1(){
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        List<Object> list1 = null;
        List<String> list2 = null;
        //编译不通过
//        list1 = list2; //List<Object> 和 List<String>不具备子父类关系

        /*
        反证法：
        假设list1 = list2；
        list1.add(123); 导致混入非String的数据，从而出错
         */
        show(list1);
//        show(list2) 出错

    }
    public void show(List<Object> list){

    }
    @Test
    public void test2(){
        List<String> list = null;
        ArrayList<String> list1 = null;
        list = list1;
    }

    /*
    2. 通配符的使用
        通配符：？
        类A 是类B的父类，G<A> 和 G<B> 是没有关系的，二者共同的父类是G<?>
     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;
        list = list1;
        list = list2;
        iterate(list1);
        iterate(list2);
    }

    public void iterate(List<?> l){
        Iterator<?> iterator = l.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
