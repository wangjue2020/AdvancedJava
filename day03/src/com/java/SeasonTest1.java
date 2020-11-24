package com.java;

/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承于java.lang.Enum类
 * Enum 类的常用方法
 */
interface display{
    void show();
}
//使用enum关键字枚举类
enum Season1 implements display{
    // 1. 提供当前枚举类的对象，多个对象之间用"，"隔开，末尾对象"；"结束
    SPRING("Spring", "first season of a year"){
        @Override
        public void show() {
            System.out.println("Spring is coming");
        }
    },
    SUMMER ("Summer", "Second season of a year"),
    FALL ("Fall", "Third season of a year"),
    WINTER ("Winter", "Last season of a year");
    //2. 声明Season对象的属性 private final 修饰
    private final String seasonName;
    private final String seasonDesc;
    //2. 私有化类的构造器
    private Season1(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    @Override
    public void show() {
        System.out.println("this is a season");
    }
}
public class SeasonTest1 {
    public static void main(String[] args) {
        //toString()
        System.out.println(Season1.SPRING.toString());
//        System.out.println(Season1.class.getSuperclass());
        //values() 用于查看这个enum一共有几个有限的对象
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        //valueOf(String objName)  返回枚举类中对象名是objName的对象
        //如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
        Season1 s =Season1.valueOf("WINTER");
        System.out.println(s);
    }
}