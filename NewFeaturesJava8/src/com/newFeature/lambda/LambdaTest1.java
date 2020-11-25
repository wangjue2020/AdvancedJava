package com.newFeature.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda 表达式的使用
 * 1、举例：(o1, o2) -> Integer.compare(o1,o2);
 * 2、格式：
 *      -> :Lambda 操作符 或 箭头操作符
 *      -> 左边： Lambda 形参列表 （ 其实就是接口中的抽象方法的形参列表）
 *      -> 右边： Lambda体 （其实就是重写的抽象方法的方法体）
 * 3、Lambda表达式的使用：（分为6中情况）
 *    总结：
 *      ->左边： Lambda形参列表的参数类型可以省略（类型推断）； 如果lambda形参列表只有一个参数，其一对() 也可以省略
 *      ->右边： Lambda体应该使用一堆{} 包裹； 如果lambda体只有一条执行语句（可能是return 语句）， 省略这一对{} 和return关键字
 * 4、 Lambda表达式的本质：作为函数时接口的实例对象
 * 5、如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口
 *
 */
public class LambdaTest1 {
    //语法格式一： 无参，无返回值
    @Test
    public void test(){
        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();
        System.out.println("****************************************");
        Runnable r2 = () -> System.out.println("我爱故宫");
        r2.run();
    }
    //语法格式二： Lambda 需要一个参数，但是没有返回值
    @Test
    public void test1(){
        Consumer<String> con = new Consumer<String>(){
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("To be or not to be, that's a question");
        System.out.println("=================================");
        Consumer<String> con1 = (String s)-> {
            System.out.println(s);
        };
        con1.accept("hello world");
    }
    //语法格式三： 数据类型可以省略，因为可由编译器推断得出， 称为"类型维护"
    // 数据类型因为在泛型的时候就定义好了，所以可以直接进行类型推断
    @Test
    public void test2(){
        Consumer<String> con1 = (s)-> {
            System.out.println(s);
        };
        con1.accept("hello world");
    }
    //语法格式四： Lambda若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test3(){
        Consumer<String> con1 = s-> {
            System.out.println(s);
        };
        con1.accept("hello world");
    }
    //语法格式五：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test4(){
        Comparator<Integer> com1 = new Comparator<Integer> (){
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = com1.compare(12,21);
        System.out.println(compare1);
        System.out.println("=======================================");
        //lambda
        Comparator<Integer> com2 = (o1,o2) ->  {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1,o2);
        };
        int compare2 = com2.compare(32,23);
        System.out.println(compare2);
        System.out.println("=======================================");
    }
    //语法格式六： 当Lambda 体只有一条语句时，return与大括号若有，都可以省略
    @Test
    public void test5(){
        Comparator<Integer> com1 = (o1,o2) ->  {
            return o1.compareTo(o2);
        };
        int compare1 = com1.compare(32,23);
        System.out.println(compare1);
        System.out.println("=======================================");
        Comparator<Integer> com2 = (o1,o2) ->  o1.compareTo(o2);
        int compare2 = com2.compare(32,23);
        System.out.println(compare2);
    }
}
