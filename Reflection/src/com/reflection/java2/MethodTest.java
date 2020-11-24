package com.reflection.java2;

import com.reflection.java1.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 运行时类的方法结构
 */
public class MethodTest {
    @Test
    public void test(){
        Class c = Person.class;
        //getMethods(): 获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = c.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        //getDeclaredMethods(): 获取当前运行时类中所声明的所有方法（不包含父类中声明的方法）
        Method[] declaredMethods = c.getDeclaredMethods();
        for(Method m : declaredMethods){
            System.out.println(m);
        }
    }
    /*
    权限修饰符 返回值类型 方法名（参数类型1 形参名1，。。。）throws Exception{}
     */
    @Test
    public void test2(){
        Class c = Person.class;
        Method[] declareMethods = c.getDeclaredMethods();
        for(Method m : declareMethods){
            //1、获取Annotation
            Annotation[] annotations = m.getAnnotations();
            for(Annotation a : annotations){
                System.out.println(a);
            }
            //2、权限修饰符
            int modifiers = m.getModifiers();
            System.out.print(Modifier.toString(modifiers) + '\t');
            //3、返回值类型
            System.out.print(m.getReturnType().getName()+ '\t');
            //4、方法名
            System.out.print(m.getName()+ '(');
            //5、 形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)){
                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getName() + " args_" + i);
                    if(i != parameterTypes.length-1)
                        System.out.print(",");
                }
            }
            System.out.print(")");
            //6、抛出的异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if(!(exceptionTypes == null && exceptionTypes.length == 0)){
                if(exceptionTypes.length != 0)
                    System.out.print(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    System.out.print(exceptionTypes[i].getName());
                    if(i != exceptionTypes.length-1)
                        System.out.print(", ");
                }
            }

            System.out.println();
            System.out.println();
        }
    }
}
