package com.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现Callable 接口 -----JDK5.0 新增
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式要强大？
 * 1. call() 可以有返回值
 * 2. call() 可以抛出异常被外面的操作捕获获取异常的信息
 * 3. Callable 是支持泛型
 */
//1. 创建一个实现Callable的实现类
class NumThread implements Callable{
    //2. 实现call方法，将此线程需要执行的操作声明在call() 中
    @Override
    public Object call() {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 ==0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
public class ThreadNew {
    public static void main(String[] args) {
        // 3. 创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4. 将此Callable实现类的对象作为参数传递到FutureTask构造器中
        FutureTask futureTask = new FutureTask(numThread);
        //5. 将FutureTask类的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start(); //FutureTask其实也是implement Runnable的
        try{
            //6. 获取Callable中的call() 方法的返回值 ----optional
            //get()  返回值即为FutureTask构造器参数Callable实现类重写call()返回的值
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
