package com.exercise;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行有一个账户，有两个储户分别向同一个账户存3000元，每次存1000， 存3次，每次存完打印账户余额
 *
 * 分析：
 * 1 是否是多线程问题？ 是， 两个储户线程
 * 2 是否有共享数据？ 有，账户（或者账户余额）
 * 3 是否有线程安全问题？ 有
 * 4 需要考虑如何解决线程安全问题？ 同步机制： 有三种方式。
 */
class Account implements Runnable{
    public int balance;
    ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            deposit();
        }
    }
    public void deposit(){
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance += 1;
        System.out.println(Thread.currentThread().getName()+" 存入账户1000元");
        System.out.println("余额： "+balance);
        lock.unlock();
    }
}
public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();
        Thread t1 = new Thread(account);
        Thread t2 = new Thread(account);

        t1.setName("储户A：");
        t2.setName("储户B：");

        t1.start();
        t2.start();
    }

}
