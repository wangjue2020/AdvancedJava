package com.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁
 * 1. 面试题： synchronized 与 lock的异同？
 *      相同点：都可以解决线程安全的问题
 *      不同： synchronize 机制在执行完相应的同步代码以后，自动的释放同步监视器
 *            Lock 需要手动的启动同步(lock()), 同时结束同步也需要手动的实现(unlock())
 *            Lock 只有代码块锁，synchronized 有代码块锁和方法锁
 *            使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）
 * 2. 优先使用顺序： Lock-》同步代码块（已经进入了方法体，分配了相应资源） -》 同步方法（在方法体之外）
 *
 * 3. 面试题： 如何解决线程安全问题？有几种方式
 *          同步代码块、同步方法、Lock锁
 */
class Window implements Runnable{
    private int ticket = 100;
    // 1 实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            try{
                lock.lock();
                if (ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+": 售票， 票号为： "+ ticket);
                    ticket --;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }

        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();
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
