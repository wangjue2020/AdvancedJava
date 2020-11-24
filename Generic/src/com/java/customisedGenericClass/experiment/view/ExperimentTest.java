package com.java.customisedGenericClass.experiment.view;

import com.java.customisedGenericClass.experiment.bean.DAO;
import com.java.customisedGenericClass.experiment.bean.User;
import org.junit.Test;

public class ExperimentTest {
    @Test
    public void Test1(){
        DAO d = new DAO();
        d.save("1", new User(1,21,"Lucy"));
        System.out.println(d.getMap());
        System.out.println(d.get("0"));
        System.out.println(d.get("1"));
        d.update("1", new User(2,22,"Lucy"));
        System.out.println(d.getMap());
        System.out.println(d.list());
        d.delete("1");
        System.out.println(d.getMap());
    }
}
