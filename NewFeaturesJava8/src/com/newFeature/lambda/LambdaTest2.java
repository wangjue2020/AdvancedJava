package com.newFeature.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java 内置的四大核心函数式接口
 *
 * 消费性接口 Consumer<T>    void accept(T t)    对类型为T的对象应用操作
 * 供给型接口 Supplier<T>    T get()             返回类型为T的对象
 * 函数型接口 Function<T,R>  R apply(T t)        对类型为T的对象应用操作， 并返回结果。结果是R类型的对象
 * 断定型接口 Predicate<T>   boolean test(T t)   确定类型为T的对象是否满足某约束，并返回boolean值
 */
public class LambdaTest2 {
    @Test
    public void test(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println(aDouble);
            }
        });
        System.out.println("===========================");
        happyTime(400, aDouble -> System.out.println(aDouble));
    }
    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void test1(){
        List<String> strings = Arrays.asList("Lucy-1", "John", "123-1", "456");
        List<String> filteredList = filterString(strings, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.endsWith("-1");
            }
        });
        System.out.println(filteredList);
        System.out.println("====================");
        List<String> filteredList1 = filterString(strings, s-> s.endsWith("-1"));
        System.out.println(filteredList1);

    }
    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s: list){
            if (pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }
}
