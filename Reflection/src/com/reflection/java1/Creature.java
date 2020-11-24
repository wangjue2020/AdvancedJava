package com.reflection.java1;

import java.io.Serializable;

public class Creature<T> implements Serializable{
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("breathing");
    }

    public void eat(){
        System.out.println("eating");
    }
}
