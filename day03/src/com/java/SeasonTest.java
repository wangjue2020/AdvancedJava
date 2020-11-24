package com.java;

/**
 * 枚举类的应用
 */
//自定义枚举类
class Season{
    //1. 声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;
    //2. 私有化类的构造器
    private Season(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //3. 提供当前枚举类的多个对象
    public static final Season SPRING = new Season("Spring", "first season of a year");
    public static final Season SUMMER = new Season("Summer", "Second season of a year");
    public static final Season FALL = new Season("Fall", "Thrid season of a year");
    public static final Season WINTER = new Season("Winter", "Last season of a year");
    // 4. 其他诉求 提供toString（）
    public String toString(){
        return "Season { seasonName:"+seasonName+", seasonDesc: "+ seasonDesc+"}";
    }

}
public class SeasonTest {
    public static void main(String[] args) {
        System.out.println(Season.SPRING);
    }
}
