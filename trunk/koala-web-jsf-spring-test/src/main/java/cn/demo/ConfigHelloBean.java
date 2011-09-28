package cn.demo;

/**
 *
 * @author Administrator
 */
public class ConfigHelloBean {

    private String message = "Hello World!";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String sayHello() {
        System.out.println(this.getMessage());
        return "hello";
    }
}
