package com.newFeature.method.reference;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *      和方法引用类似，函数式接口的抽象方法和形参列表和构造器的形参列表一致。
 *      抽象方法的返回值类型即为构造器所属的类的类型。
 * 二、数组引用
 *      大家可以把数组看作是一个特殊的类，则写法与构造器引用一致
 *
 * Created by shkstart
 */
public class ConstructorRefTest {
	//构造器引用
    //Supplier中的T get()
    @Test
    public void test1(){
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        sup.get();
        System.out.println("*****************");
        Supplier<Employee> sup1 = ()-> new Employee();
        sup1.get();
        System.out.println("*****************");
        Supplier<Employee> sup2 = Employee::new;
        sup2.get();

	}

	//Function中的R apply(T t)
    @Test
    public void test2(){
        Function<Integer, Employee> f = Employee::new;
        Employee e = f.apply(1001);
        System.out.println(e);

    }

	//BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        BiFunction<Integer, String, Employee> bi = Employee::new;
        Employee tom = bi.apply(1001, "Tom");
        System.out.println(tom);
    }

	//数组引用
    //Function中的R apply(T t)
    @Test
    public void test4(){
        Function<Integer, String[]> func1 = length -> new String[length];
        String[] arra1 = func1.apply(5);
        System.out.println(arra1.length);
        System.out.println("+++++++++++++++++");
        Function<Integer,String[]> func2 = String[]::new;
        String[] arr2 = func2.apply(10);
        System.out.println(arr2.length);
    }
}
