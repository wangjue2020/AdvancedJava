package com.java;
/**
 * 自定义泛型类
 */
public class Order<T> {
    String orderName;
    int orderId;

    // 类的内部结构就可以使用类的泛型
    T orderT;
    public Order(){};
    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }
    public T getOderT(){
        return orderT;
    }
    public void setOrderT(T orderT){
        this.orderT = orderT;
    }
}
class SubOrder extends Order<Integer>{
}
class SubOrder1<T> extends Order<T>{//SubOrder1<T>仍然是泛型类
}
