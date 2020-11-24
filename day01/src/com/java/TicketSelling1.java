package com.java;

/**
 * 例子： 创建三个窗口卖票，总票数为100 张，使用实现Runnable 接口的方式
 * 不使用static的情况下共用一个100 张票，因为只创建了一个window的对象，所以三个thread 共用这一个window对象
 * 依旧存在线程安全问题
 */

class Window1 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":卖票， 票号为： " + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}

public class TicketSelling1 {
    public static void main(String[] args) {
        Window1 w = new Window1();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
