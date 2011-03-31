package org.koala.orm.aopi;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ActionAop {

    @After("execution(* org.koala.orm.aop.Action.*(..))")
    public void actionAopAfter() {
        System.out.println("#############AfterII##########");
    }

    @Before("execution(* org.koala.orm.aop.Action.*(..))")
    public void actionAopBefore() {
        System.out.println("#############BeforeII##########");
    }

    @AfterReturning(pointcut = "execution(* org.koala.orm.aop.Action.look*(..)", returning = "retVal")
    public void actionRB(JoinPoint jp, Object retVal) {
        System.out.println(jp);
        System.out.println(retVal);
    }
}
