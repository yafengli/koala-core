package cn.demo.webservice;

import cn.demo.pojo.Student;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author YaFengLi
 */
@WebService(endpointInterface = "cn.demo.webservice.HelloWorld", serviceName = "HelloWorldService")
public class HelloWorldImpl implements HelloWorld {

    public String sayHello(String name) {
        return String.format("Hello Wolrd ,[%s]!\n", name);
    }

    public Student loadStudent(@WebParam(name = "student") String student) {
        Student stu = new Student();
        stu.setLastName("LastName");
        stu.setFirstName("FirstName");
        return stu;
    }
}
