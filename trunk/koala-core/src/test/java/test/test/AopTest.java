/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.test;

import java.lang.reflect.Proxy;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.aop.HelloWorldImpl;
import test.aop.HelloWorld;
import test.aop.HelloWorldHandler;

/**
 *
 * @author YaFengLi
 */
public class AopTest {
    private AbstractApplicationContext ctx=null;

    @Before
	public void init(){
        ctx=new ClassPathXmlApplicationContext("applicationContext-aop_proxy.xml");
    }

    @Test
	public void testAop() {
        HelloWorld hw = new HelloWorldImpl();
        HelloWorldHandler hwh = new HelloWorldHandler(hw);
        HelloWorld hwinstane = (HelloWorld) Proxy.newProxyInstance(hw.getClass().getClassLoader(), 
								   hw.getClass().getInterfaces(), hwh);
        System.out.println(hwinstane.sayHello("Fuck"));
    }
    @Test
	public void testProxy(){
        HelloWorld hw=(HelloWorld)ctx.getBean("helloWorldProxy");
        System.out.println(hw.sayHello("Test"));
    }
}
