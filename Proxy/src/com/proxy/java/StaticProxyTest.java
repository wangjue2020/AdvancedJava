package com.proxy.java;

/**
 * 静态代理举例
 *
 * 特点：代理类和被代理类在编译期间就确定下来
 */
interface ClothFactory{
    void produceCloth();
}
//代理类
class ProxyClothFactory implements  ClothFactory{
    private ClothFactory factory;//用被代理类对象进行实例化
    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }
    @Override
    public void produceCloth() {
        System.out.println("proxy factory is preparing");
        factory.produceCloth();
        System.out.println("proxy factory is completing other works");
    }
}
//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("Producing sports clothing");
    }
}
public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        NikeClothFactory ncf = new NikeClothFactory();
        //创建代理类的对象
        ProxyClothFactory pcf = new ProxyClothFactory(ncf);
        pcf.produceCloth();
    }
}
