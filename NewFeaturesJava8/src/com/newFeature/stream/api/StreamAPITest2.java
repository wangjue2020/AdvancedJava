package com.newFeature.stream.api;

import com.newFeature.method.reference.Employee;
import com.newFeature.method.reference.EmployeeData;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * 测试Stream的终止操作
 */
public class StreamAPITest2 {
    //1-匹配与查找
    @Test
    public void test(){
        List<Employee> employees = EmployeeData.getEmployees();
        //allMatch(Predicate p) --检查是否匹配所有元素
        //练习： 是否所有的员工的的年龄都大于18
        boolean b = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println("是否所有的员工的的年龄都大于18:   " +b);
        // anyMatch(Predicate p) -- 检查是否至少匹配一个元素。
        // 练习： 是否存在员工的工资大于10000
        boolean b1 = employees.stream().anyMatch(e -> e.getSalary() > 8000);
        System.out.println("是否存在员工的工资大于8000:  " + b1);
        // noneMatch(Predicate p) -- 检查是否没有匹配的元素
        // 练习：是否存在员工姓"雷"
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println("是否存在员工姓\"雷\":  " + !noneMatch);
        //findFirst -- 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println("返回第一个元素:   " + first);
        //findAny --返回当前流中的任意元素
        Optional<Employee> any = employees.stream().findAny();
        System.out.println("返回当前流中的任意元素:   " + any);
        //count --返回当前流中的元素个数
        System.out.println("返回当前流中的元素个数:    "+employees.stream().filter(e->e.getSalary()>9000).count());
        // max(Comparator com)  --返回流中最大值
        Optional<Double> maxSalary  = employees.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println("max salary:    " + maxSalary);
        //min(Comparator com)  --返回流中最小值
        Optional<Employee> minSalary = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println("min salary:    " + minSalary);
        //foreach(Consumer c) --内部迭代
        employees.stream().forEach(e->System.out.println(e.getName()));
    }
}
