package com.java.generic.experiment.view;

import com.java.generic.experiment.bean.Employee;
import com.java.generic.experiment.bean.MyDate;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class GenericTest {
    public static void main(String[] args) {


        Employee e1 = new Employee("Linda", 20, new MyDate(12,22,2020));
        Employee e2 = new Employee("Lucy", 21, new MyDate(4,26,2020));
        Employee e3 = new Employee("John", 23, new MyDate(1,20,2020));
        Employee e4 = new Employee("Frank", 25, new MyDate(4,12,2020));
        Employee e5 = new Employee("Jane", 10, new MyDate(10,14,2020));
        //Using implements Comparable interface in Employee class
        TreeSet<Employee> ts = new TreeSet<>();
        ts.add(e1);
        ts.add(e2);
        ts.add(e3);
        ts.add(e4);
        ts.add(e5);

        Iterator<Employee> iterator = ts.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("=================");
        //Using customized Comparator
        TreeSet<Employee>  ts1 = new TreeSet<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                MyDate d1 = o1.getBirthday();
                MyDate d2 = o2.getBirthday();
                if (d1.getYear() < d2.getYear()){
                    return -1;
                }else if (d1.getYear() == d2.getYear()){
                    if ( d1.getMonth() < d2.getMonth()){
                        return -1;
                    }else if (d1.getMonth() == d2.getMonth()){
                        if (d1.getDay() < d2.getDay()){
                            return -1;
                        }else if (d1.getDay() == d2.getDay()){
                            return 0;
                        }else{
                            return 1;
                        }
                    }else{
                        return 1;
                    }
                }else{
                    return 1;
                }
            }
        });
        ts1.add(e1);
        ts1.add(e2);
        ts1.add(e3);
        ts1.add(e4);
        ts1.add(e5);
        Iterator<Employee> iterator1 = ts1.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
    }
}
