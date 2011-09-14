package cn.demo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "service")
@SessionScoped
public class ServiceBean {
    private String name="SERVICE";
    public ServiceBean() {
        System.out.println("ServiceBean init");
    }

    @PostConstruct
    public void init() {
        System.out.println("@@init@@");
    }

    public String getName() {
        return name;
    }
}
