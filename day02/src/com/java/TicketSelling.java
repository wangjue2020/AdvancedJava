package com.java;

/*
例子： 创建三个窗口买票，总票数100 张
使用继承Thread类的方式
使用同步代码块解决Thread类的方式的线程安全问题
说明： 在继承Thread类创建多线程的方式中，慎用this充当同步监视器 （具体情况具体分析，主要是确保同步监视器是唯一的共用的）
        可以考虑使用当前类作为同步监视器 比如： Window.class
 */
class Window extends Thread{

    private static int ticket = 100;
    private static Object obj = new Object();
    @Override
    public void run() {
        while (true){
            //这里不能用this，因为每一个thread都有一个独立widnow对象，this不是唯一的对象，this代表t1,t2,t3三个对象
            //可以用Synchronized（Window.class） 因为类也是个对象，是唯一的，类只会加载一次
            synchronized (obj){
                if ( ticket> 0) {
                    System.out.println(getName() + ": 卖票， 票号为： " + ticket);
                    ticket--;
                }else{
                    break;
                }
            }

        }
    }
}

public class TicketSelling {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}
