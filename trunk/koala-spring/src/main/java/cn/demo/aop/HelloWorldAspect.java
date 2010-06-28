package cn.demo.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: YaFengLi
 * Date: 2010-6-28
 * Time: 14:42:37
 * To change this template use File | Settings | File Templates.
 */
@Aspect
public class HelloWorldAspect {
    @Pointcut("execution(*  *.testp(..))")
    public void tesp() {
    }

    @Before("tesp() &&" + "args(req,session,model)")
    public void sayHello(HttpServletRequest req, HttpSession session, ModelMap model) {
        System.out.println("********Hello World.*********");
        System.out.printf("[args:][%s,%s,%s]\n", req, session, model);
    }

    @After("tesp()")
    public void sayGoodBye() {
        System.out.println("********Good Bye.*********");
    }
}
