package cn.demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-10-9
 * Time: 16:23:45
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@RequestScoped
public class HelloWorld {
    @ManagedProperty(name = "name", value = "@FUCK@")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String action() {
        System.out.println("Hello World!");
        return "hello";
    }

    public String action2() {
        System.out.println("Hello World2!");
        this.setName("#FUCK#");
        return "hello";
    }   
}
