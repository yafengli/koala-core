package org.koala.orm.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ActionAop {

	@Pointcut("execution(* org.koala.orm.aop.*.*(..))")
	public void action() {
	}

	@After("action()")
	public void actionAopAfter() {
		System.out.println("#############After##########");
	}

	@Before("action()")
	public void actionAopBefore() {
		System.out.println("#############Before##########");
	}
}
