package com.reflection.java1;
@MyAnnotation(value="hi")
public class Person extends Creature<String> implements Comparable<String>, MyInterface{
    private String name;
    int age;
    public int id;

    public Person() {
    }
    @MyAnnotation(value="private constructor")
    private Person(String name) {
        this.name = name;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @MyAnnotation
    private String show(String nation){
        System.out.println("I am from " + nation);
        return nation;
    }
    public String display(String interest, int age) throws  NullPointerException, ClassCastException{
        return interest +age;
    }

    @Override
    public void info() {
        System.out.println("I am a person");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", weight=" + weight +
                '}';
    }

    private static void showDesc(){
        System.out.println("I am a cute girl");
    }
}