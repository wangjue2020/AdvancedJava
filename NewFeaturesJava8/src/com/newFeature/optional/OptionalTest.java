package com.newFeature.optional;

import com.sun.tracing.dtrace.ArgsAttributes;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional类： 为了在程序中避免出现空指针异常而创建
 * Optional.of(T t) --创建一个Optional实例，t必须非空
 * Optional.empty() --创建一个空的Optional实例
 * Optional.ofNullable(T t) -- t可以为null
 * orElse(T t)
 */
public class OptionalTest {
    @Test
    public void test(){
        Girl girl = new Girl();
        // Optional.of(T t) --创建一个Optional实例，t必须非空
        Optional<Girl> optionalGirl = Optional.of(girl);
    }
    @Test
    public void test1(){
        Girl girl = new Girl();
        girl = null;
        //Optional.ofNullable(T t) -- t可以为null
        //源码call 了Optional.empty() 应对t为null的情况
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
    }
    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }
    @Test
    public void test2(){
        Boy boy = new Boy();
//        String girlname = getGirlName(boy);
        String girlname = getGirlName2(null);
        System.out.println(girlname);
    }
    public String getGirlName1(Boy boy){
        if(boy != null){
            Girl g = boy.getGirl();
            if(g != null){
                return g.getName();
            }
        }
        return null;
    }
    //使用Optional类的getGirlName()
    public String getGirlName2(Boy boy){
        Optional<Boy> o_boy = Optional.ofNullable(boy);
        //orElse(T t): 如果当前的Optional内部封装的t是非空的，则返回内部的t。
        //如果内部的t是空的，则返回orElse()方法中的参数
        //此时boy1一定非空
        Boy boy1 = o_boy.orElse(new Boy());
        Girl girl = boy1.getGirl();
        Optional<Girl> o_girl = Optional.ofNullable(girl);
        //此时girl1一定非空
        Girl girl1 = o_girl.orElse(new Girl("Jenny"));
        return girl1.getName();
    }
}

