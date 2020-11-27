package com.newFeature.stream.api;

import com.newFeature.method.reference.Employee;
import com.newFeature.method.reference.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1、Stream 关注的是对数据的运算，与CPU打交道
 *      Collection(集合) 关注的是数据的存储， 与内存打交道
 *
 * 2、
 *      Stream 自己不会存储元素。
 *      Stream 不会改变源对象。 相反，他们会返回一个持有结果的新的Stream
 *      Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
 *
 * 3、Stream 执行流程
 *      # Stream 的实例化
 *      # 一系列的中间操作（过滤、映射。。。）
 *      # 终止操作
 *
 * 4、说明：
 *      # 一个中间操作链，对数据源的数据进行处理
 *      # 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 */
public class StreamAPITest {
    // 创建Stream方式一：通过集合
    @Test
    public void test(){
        List<Employee> employees = EmployeeData.getEmployees();
        // default Stream<E> stream(): 返回一个顺序流
        Stream<Employee> stream = employees.stream();
        // default Stream<E> parallelStream(): 返回一个并行流
        Stream<Employee> stream1 = employees.parallelStream();
    }
    //创建Stream方式二： 通过数组
    @Test
    public void test1(){
        int[] arr =new int[]{1,2,3,4,5,6};
        // 调用Arrays类的static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream = Arrays.stream(arr);
        Employee e1 = new Employee(1001,"Tom");
        Employee e2 = new Employee(1002,"Tam");
        Employee[] arr1 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);
    }
    // 创建Stream方式三：通过Stream的of()
    @Test
    public void test2(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }

}
