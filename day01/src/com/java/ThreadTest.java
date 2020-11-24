package com.java;
/*
多线程的创建， 方式一： 继承于Thread类
1. 创建一个继承于Thread类的子类
2. 重写Thread类的run（） --》 将此线程执行的操作声明在run() 中
3. 创建Thread类的子类的对象
4. 通过此对象调用start()
例子：遍历100 以内的所有的偶数
 */
//1. 创建一个继承于Thread类的子类
class MyThread extends Thread{
    // 重写Thread类的run()
    @Override
    public void run(){
        for(int i =0; i < 100; i++ ){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+':'+i);
            }
        }
    }
}

public class ThreadTest
{
    public static void main(String[] args ){
        //3. 创建Thread类的子类的对象
        MyThread t1 = new MyThread();

        //4. 通过此对象调用start() 4.1 启动当前线程 4.2 调用当前线程的run()
        t1.start();
        // 问题一：不能直接使用 t1.run(); 直接使用run则不是新创建一个thread，而是只是在call一个对象的方法而已
        // t1.run();
        //问题二： 再启动一个线程，遍历100 以内的偶数，不可以还让已经start() 的线程去执行，会报IllegalThreadStatusException的异常
        // t1.start();
        //我们需要重新创建一个线程的对象
        MyThread t2 = new MyThread();
        t2.start();

        //会发现出来的结果是偶数和hello不是按顺序的，因为多个线程的同时运行，所以没有特定顺序
        for(int i =0 ; i < 100; i++){
            if ( i %2 != 0){
                System.out.println("Hello");
            }
        }
    }
}
