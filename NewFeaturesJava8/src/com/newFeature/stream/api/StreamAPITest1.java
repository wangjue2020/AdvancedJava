package com.newFeature.stream.api;

import com.newFeature.method.reference.Employee;
import com.newFeature.method.reference.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 */
public class StreamAPITest1 {
    //1-筛选与切片
    @Test
    public void test(){
        List<Employee> list = EmployeeData.getEmployees();
        //filter(Predicate p) --接受Lambda， 从流中排除某些元素
        Stream<Employee> stream = list.stream();
        //Predicate<Employee> p = e->e.getSalary()>7000;
        stream.filter(e->e.getSalary() > 7000).forEach(System.out::println);
        //limit(n)  -- 截断流， 使其元素不超过给定数量
        list.stream().limit(3).forEach(System.out::println);
        //skip(n)   -- 跳过元素，返回一个扔掉了前n个元素的流。若六中元素不足n个，则返回一个空流。 与limit(n)  互补
        list.stream().skip(3).forEach(System.out::println);
        //distinct()    -- 筛选，通过流所生产元素的hashCode() 和 equals() 去除重复的元素
        System.out.println("==========================");
        list.add(new Employee(1010, "Tom",40, 8000));
        list.add(new Employee(1010, "Tom",40, 8000));
        list.add(new Employee(1010, "Tom",40, 8000));
        list.add(new Employee(1010, "Tom",40, 8000));
        list.stream().distinct().forEach(System.out::println);
    }
    // 映射
    @Test
    public void test1(){
        // map(Function f) -- 接受一个函数作为参数，将元素转换成其他形式或提取信息，
        //                      该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<String> list = Arrays.asList("AA", "BB", "CC", "DD");
        list.stream().map(s->s.toLowerCase()).forEach(System.out::println);
        // 练习L获取员工姓名长度大于3 的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(s->s.length()>3).forEach(System.out::println);
        //练习：
        //从Stream of String ，将其中每个string 都进行fromStringToStream 操作，每一个string返回一个stream，
        // 所以是Stream<String> --> Stream<Stream<Character>>
        Stream<Stream<Character>> streamStream = list.stream().map(this::fromStringToStream);
        streamStream.forEach(e->e.forEach(System.out::println));
        System.out.println("=================");
        // flatMap(Function f) --接收一个函数作为参数，将流中的每个值都换成另一个流，
        //                      然后把所有流连接成一个流
        Stream<Character> characterStream = list.stream().flatMap(this::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    //将字符串中的多个字符构成的集合转换为对应的Stream实例
    public Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for(Character c: str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test2(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);
//        list1.add(list2); 共4个元素
        list1.addAll(list2); //共6个元素
        System.out.println(list1);
    }
    //排序 sorting
    @Test
    public void test3(){
        // sorted() --自然排序
        List<Integer> list = Arrays.asList(1,12,34,14,5,6);
        list.stream().sorted().forEach(System.out::println);
        //sorted(Comparator com) --定制排序
        List<String> list1 = Arrays.asList("a","dfsd", "wrwsd","asfsdfe");
        list1.stream().sorted(String::compareTo).forEach(System.out::println);
    }
}
