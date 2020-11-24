package com.java;
/*
测试Thread中的常用方法：
1 start() : 启动当前线程，调用当前线程的run();
2 run() :  通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
3 currentThread(): 静态方法， 返回执行当前代码的线程
4 getName(): 获取当前线程的名字
5 setName(): 设置当前线程的名字
6 yield() : 释放当前cpu的执行权
7 join() :  在线程A中调用线程B的join()， 此时线程A就进入阻塞状态，
            直到线程B完全执行完以后，线程A才结束阻塞状态
8 stop() : 强制终止当前线程， 已过时
9 sleep(long millitime): 当前线程阻塞millitime的时间， 在指定的millitime 毫秒内都属于阻塞状态
10 isAlive() : 判断当前线程是否存活

线程的优先级：
1 MAX_PRIORITY: 10
 MIN_PRIORITY: 1
 NORM_PRIORITY: 5
 2 如何获取和设置当前线程的优先级
    getPriority() : 获取线程的优先级
    setPriority(int p): 设置线程的优先级
    说明： 高优先级的线程要抢占低优先级线程cpu的执行权， 但是只是从概率上讲，高优先级的线程高概率的情况下，
            低优先级的线程也有可能被执行，并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行

 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread h1 = new HelloThread();
        //也可以通过给thread子类定义一个constructor(String name) 里面写super(name);
        h1.setName("线程一");
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();

        Thread.currentThread().setName("主线程");
        for ( int i =0; i< 100; i++){
            if ( i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+':'+Thread.currentThread().getPriority()+':' +i);
            }
            if(i == 20){
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}

class HelloThread extends Thread{
    @Override
    public void run() {
        for ( int i = 0; i< 100 ; i++){
            if ( i % 2 == 0) {
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName() + ':' + getPriority() +':' + i);
            }

//            if ( i % 2 == 0){
//                this.yield();
//            }
        }
    }
}
