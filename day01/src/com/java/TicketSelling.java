package com.java;
/*
例子： 创建三个窗口买票，总票数100 张
使用继承Thread类的方式
 */
class Ticket {
    private static Ticket ticket;
    private static int count;
    private Ticket(int count){this.count = count;}
    public static Ticket getTicket(){
        if(ticket == null){
            ticket = new Ticket(100);
        }
        return ticket;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }
}

class Window extends Thread{
    private Ticket ticket = Ticket.getTicket();
    @Override
    public void run() {
        while ( ticket.getCount() > 0){

            synchronized (ticket){
                ticket.notify();
                if ( ticket.getCount() > 0) {
                    System.out.println(getName() + ": 卖票， 票号为： " + ticket.getCount());
                    ticket.setCount(ticket.getCount()-1);
                }
                try {
                    ticket.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        stop();
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
