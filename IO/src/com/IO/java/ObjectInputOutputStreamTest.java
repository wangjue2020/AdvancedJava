package com.IO.java;

import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 * 1、 ObjectInputStream 和 ObjectOutputStream
 * 2、作用：用于存储和读取基本数据类型数据或对象的处理流。
 *        它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来
 * 3、要想一个java对象是可序列化的，这个java对象所在的类需要满足相应的要求。
 *      # 需要实现接口Serializable
 *      # 当前类提供一个全局常量serialVersionUID， 可以自己定义value
 *      ！！！如果类没有显示的定义这个静态常量，他的值是Java运行时环境根据类的内部细节自动生成的。
 *           若类的实例变量做了修改，serialVersionUID 可能发生变化。故建议，显示声明
 *           就是比如说当我们没有声明的时候，JRE会自动生成一个serialVersionUID，
 *           然后我们将其对象序列化到某文件或用于网络传输后，如果这个类的内部被修改了，
 *           那么会导致JRE自动生成的SerialVersionUID可能发生了改变，这时如果想反序列化，
 *           就可能发现serialVersionUID前后不一致
 *      # 除了当前的类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的。（默认情况下，基本数据类型可序列化）
 *
 * 补充： ObjectInputStream 和 ObjectOutputStream 不能序列化static和transient修饰的成员
 *
 */
public class ObjectInputOutputStreamTest {
    /*
    序列化的过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现
     */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            oos.writeObject(new String("I love Beijing"));
            oos.flush();//刷新操作
            oos.writeObject(new Person(23, "wendy"));
            oos.flush();//刷新操作
            oos.writeObject(new Person(12, "charlie", new Account(1000)));
            oos.flush();
        } catch ( IOException e){
            e.printStackTrace();
        } finally{
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    反序列化： 将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream
     */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Object o;
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class Person implements Serializable{
    public static final long serialVersionUID = 42324234324545L;
    public int age;
    public String name;
    private Account account;

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", account=" + account +
                '}';
    }

    public Person(int age, String name, Account account) {
        this.age = age;
        this.name = name;
        this.account = account;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

class Account implements Serializable{

    public static final long serialVersionUID = 42324234324543L;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
