package com.java2;
/**
 * 线程通信的应用： 经典例题： 生产者/消费者问题
 *
 * 生产者Producer 将产品交给店员clerk， 而消费者customer从店员出取走产品，
 * 店员一次只能持有固定数量的产品（比如：20）， 如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位
 * 放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果点钟有产品了在通知消费者
 * 来取走产品
 *
 * 分析：
 * 1 是否是多线程问题？ 是 生产者线程和消费者线程
 * 2 是否有共享数据？ 是 店员（或产品）
 * 3 如何解决线程的安全问题？ 同步机制，有三种方法
 * 4 是否涉及到线程的通信？ 是
 */
class Clerk1{
    private int amount = 0;
    public synchronized void consume(){
        if (amount > 0){
            System.out.println(Thread.currentThread().getName()+": 开始消费第"+amount+"个产品");
            amount--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void produce(){
        if ( amount < 20){
            amount++;
            System.out.println(Thread.currentThread().getName()+": 开始生产第"+amount+"个产品");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer1 extends Thread{
    private Clerk1 clerk;
    public Producer1(Clerk1 c){
        this.clerk = c;
    }

    @Override
    public void run() {
        System.out.println(getName()+ ": 开始生产产品.....");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produce();

        }
    }
}
class Consumer1 extends Thread{
    private Clerk1 clerk;
    public Consumer1(Clerk1 c){
        this.clerk =c;
    }

    @Override
    public void run() {
        System.out.println(getName()+ ": 开始消费产品.....");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consume();

        }
    }
}
public class ProductTest {
    public static void main(String[] args) {
        Clerk1 c= new Clerk1();
        Producer1 p = new Producer1(c);
        p.setName("Producer");
        Consumer1 co = new Consumer1(c);
        co.setName("Consumer");
        p.start();
        co.start();
    }
}
