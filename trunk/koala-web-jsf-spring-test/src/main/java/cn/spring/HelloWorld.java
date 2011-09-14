package cn.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * User: Administrator
 * Date: 11-9-14
 * Time: 下午3:07
 */
@Component("hello")
@Scope("session")
public class HelloWorld {
    private String message = "HELLO WORLD!";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @PostConstruct
    public void init() {
        System.out.println("**HelloWorld @PostConstruct**");
    }

    public void sayHello() {
        System.out.println(this.getMessage());
    }
}
