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
    @Pointcut("execution(* *.testp(..))")
    public void tesp() {
    }

    @Pointcut("execution(* *.setName(..)) || execution(* *.*.this.setName(..))")
    public void setTest() {
    }

    @Pointcut("execution(* *.findForObject(..))")
    public void findForObject() {

    }

    @Before("tesp() &&" + "args(req,session,model)")
    public void sayHello(HttpServletRequest req, HttpSession session, ModelMap model) {
        System.out.println("********Hello World.*********");
        System.out.printf("[args:][%s,%s,%s,%s]\n", req, session, model, session.getId());
    }

    @After("tesp()")
    public void sayGoodBye() {
        System.out.println("********Good Bye.*********");
    }

    @Before("setTest() &&" + "args(name)")
    public void before(String name) {
        System.out.printf("********before[set=%s]**************\n", name);
    }

    @After("setTest() &&" + "args(name)")
    public void after(String name) {
        System.out.printf("****************after[set=%s]*****************\n", name);
    }

    @Before("findForObject()")
    public void beforefindForObject() {
        System.out.println("********beforfindForObject**************");
    }

    @After("findForObject()")
    public void afterfindForObject() {
        System.out.println("********afterfindForObject**************");
    }
}
