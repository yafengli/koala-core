package cn.demo.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User: YaFengLi
 * Date: 2010-6-28
 * Time: 14:42:37
 */
@Aspect
public class HelloWorldAspect {
    @Pointcut("execution(*  *.testp(..))")
    public void tesp() {
    }

    @Before("tesp() &&" + "args(req,session,model)")
    public void sayHello(HttpServletRequest req, HttpSession session, ModelMap model) {
        System.out.println("********Hello World.*********");
        System.out.printf("[args:][%s,%s,%s,%s]\n", req, session, model, session.getId());
    }

    @After(value = "tesp()")
    public void sayGoodBye() {
        System.out.println("********Good Bye.*********");
    }

    @Around("set(* *.UploadContorller.name)" + "&&args(name)")
    public void invoke(String name) {
        System.out.printf("[set=%s]\n", name);
    }
}
