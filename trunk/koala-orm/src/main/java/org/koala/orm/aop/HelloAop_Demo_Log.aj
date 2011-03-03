package org.koala.orm.aop;

/**
 * User: phoenixup
 * Date: 11-3-3
 * Time: 上午9:40
 * Desc: //TODO:WRITE YOUR OWN DESCRIPTION.
 */
public aspect HelloAop_Demo_Log {
	pointcut log():
		execution(* HelloAop.sayHello(..));
	before():log(){
		System.out.println("Log before.");
	}
	after() returning :log(){
		System.out.println("Log after.");
	}
	public void HelloAop.sayHello2(){
		System.out.println("#Hello World!");
	}
}
