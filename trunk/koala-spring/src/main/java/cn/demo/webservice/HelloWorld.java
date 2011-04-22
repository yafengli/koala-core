/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.demo.webservice;

import cn.demo.pojo.Student;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Administrator
 */
@WebService(targetNamespace="http://www.test.cn/")
public interface HelloWorld {
    public String sayHello(@WebParam(name="name") String name);
    public Student loadStudent(@WebParam(name="student")String student);
}
