package com.java;
@MyAnnotation(value="hello")
public class MyAnnotationTest {
    public static void main(String[] args) {
        //...
    }
}


@MyAnnotation(value="hello")
class Person{
    private String name;
    private int age;

    public Person(){};
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
