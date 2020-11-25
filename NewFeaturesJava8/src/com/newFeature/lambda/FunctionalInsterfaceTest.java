package com.newFeature.lambda;

/**
 * 函数式接口
 * 1、只包含一个抽象方法的接口，就称为函数式接口
 * 2、你可以通过Lambda表达式来创建该接口的对象。
 *  （若Lambda表达式抛出一个受检异常（即：非运行时异常）， 那么该异常需要在目标接口的抽象方法上进行声明）。
 * 3、我们可以在一个接口上使用@FunctionalInterface 注解， 这样做可以检查它是否是一个函数式接口。
 *      同时javac 也会包含一条声明，说明这个接口是一个函数式接口
 */
@FunctionalInterface
public interface FunctionalInsterfaceTest {
    void method1();
}
