package org.koala.orm.aop;


public aspect HelloAop_Demo_Log {
	pointcut log():
		execution(* HelloAop.sayHello(..));
	before():log(){
		System.out.println("Aop before.");
	}
	after() returning :log(){
		System.out.println("Aop after.");
	}
	public void HelloAop.sayHello2(){
		System.out.println("#Aop Add@Hello World!");
	}
}
