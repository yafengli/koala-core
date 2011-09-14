package cn.demo;

import cn.spring.HelloWorld;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "welcome")
@SessionScoped
public class WelcomeBean {
    @ManagedProperty(value = "#{service}")
    private ServiceBean service;
    @ManagedProperty(value = "#{hello}")
    private HelloWorld helloWorld;

    public ServiceBean getService() {
        return service;
    }

    public void setService(ServiceBean service) {
        this.service = service;
    }

    public HelloWorld getHelloWorld() {
        return helloWorld;
    }

    public void setHelloWorld(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public WelcomeBean() {
        System.out.println("WelcomeBean instantiated");
    }

    @PostConstruct
    public void init() {
        System.out.println("@@instantiated@@");
    }

    public String getMessage() {
        service.init();
        helloWorld.sayHello();
        return "I'm alive!";
    }

    public String action() {
        helloWorld.setMessage("*This is a new Hello World Message!*");
        return "test";
    }
}

