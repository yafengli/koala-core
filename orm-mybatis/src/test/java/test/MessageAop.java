package test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MessageAop {
    @Pointcut("execution (* DemoMapper.*(..))")
    public void sayHello() {
    }

    @Before("sayHello()")
    public void sayHelloBefore() {
        System.out.printf("#before#,arg=%s\n");
    }

}
