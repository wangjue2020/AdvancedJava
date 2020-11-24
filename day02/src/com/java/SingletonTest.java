package com.java;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 */

class Bank{
    private Bank(){}
    private static Bank instance = null;
    public static Bank getInstance(){
        //方式一：效率稍差
//        synchronized (Bank.class){
//            if (instance == null){
//                instance = new Bank();
//            }
//            return instance;
//        }
        // 对于已经在等待锁的thread会继续等待锁，但是一旦创建完成，
        // instance不再是null之后，thread就不需要等待锁再来进行判断了，
        // 直接判断然后就直接return了
        if(instance == null){
            synchronized (Bank.class){
                if (instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;

    }
}
public class SingletonTest {
}
