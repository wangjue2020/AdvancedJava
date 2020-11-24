package com.java2;

/**
 * 线程通信的应用： 经典例题： 生产者/消费者问题
 *
 * 生产者Producer 将产品交给店员clerk， 而消费者customer从店员出取走产品，
 * 店员一次只能持有固定数量的产品（比如：20）， 如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位
 * 放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果点钟有产品了在通知消费者
 * 来取走产品
 */
class Clerk{
    private int max_amount;
    private int amount;
    public Clerk(int max_amount){
        this.max_amount = max_amount;
    }
    public synchronized void consume(){
//        if(amount >0){
//            amount--;
//            System.out.println(Thread.currentThread().getName()+ " 买出去了一份， 还剩 "+amount);
//        }
//        if (amount< max_amount){
//            notify();
//            if (amount <= 0){
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        notify();
        if ( amount <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            amount--;
            System.out.println(Thread.currentThread().getName()+ " 买出去了一份， 还剩 "+amount);
        }


    }
    public synchronized void produce(){
//        if (amount < max_amount){
//            amount++;
//            System.out.println(Thread.currentThread().getName()+" 生产了一份， 还剩 "+amount);
//        }
//        if ( amount > 0){
//            notify();
//            if (amount >= max_amount){
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        notify();
        if ( amount >= max_amount){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            amount++;
            System.out.println(Thread.currentThread().getName()+" 生产了一份， 还剩 "+amount);
        }

    }

    public void setMax_amount(int max_amount){
        this.max_amount = max_amount;
    }
    public int getMax_amount(){
        return max_amount;
    }
}
class Producer extends Thread{
    private Clerk c;
    public Producer(Clerk c){
        this.c = c;
    }
    @Override
    public void run() {
        int i = 0;
        while(true){
            System.out.println(getName()+ ": 开始生产产品.....");
            c.produce();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
//            if ( i == 1000){
//                break;
//            }
        }
    }
}

class Customer extends Thread{
    private Clerk c;
    public Customer(Clerk c){
        this.c = c;
    }
    @Override
    public void run() {
        int i =0;
        while(true){
            System.out.println(getName()+ ": 开始消费产品.....");
            c.consume();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
//            if (i==100){
//                break;
//            }
        }
    }
}

public class CustomerProductorTest {
    public static void main(String[] args) {
        Clerk c = new Clerk(50);
        Customer cu = new Customer(c);
        Producer p = new Producer(c);
        Customer cu1 = new Customer(c);
        cu1.setName("Consumer1");
        cu.setName("Consumer");
        p.setName("Producer");
        cu.start();
        cu1.start();
        p.start();
    }
}
