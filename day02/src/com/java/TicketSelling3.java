package com.java;

/**
 * 使用同步方法解决继承Thread类的线程安全问题
 */


class Window3 extends Thread{
    private static int ticket = 100;
    private static Object obj = new Object();
    @Override
    public void run() {
        while (true){
            show();
        }
    }
    private static synchronized void show(){
        //private synchronized void show()  是错误的
        //同步监视器是this， 而对于继承thread类的解决方法，this代表着t1, t2, t3, 三个对象也就是三个不同的同步监视器
        //所以为确保是唯一且共用的，要在方法声明的时候声明为static的方法， 有了static之后同步监视器为当前类 即 Window3.class
        //因为static的成员是在类加载的时候进入内存的，所以同步监视器就是这个类
        if ( ticket> 0) {
            System.out.println(Thread.currentThread().getName() + ": 卖票， 票号为： " + ticket);
            ticket--;
        }
    }
}
public class TicketSelling3 {
    public static void main(String[] args) {
        Window3 w1 = new Window3();
        Window3 w2 = new Window3();
        Window3 w3 = new Window3();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }

}
