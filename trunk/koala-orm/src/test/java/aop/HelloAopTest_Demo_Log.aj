package aop;

/**
 * User: phoenixup
 * Date: 11-3-3
 * Time: 上午9:40
 * Desc: //TODO:WRITE YOUR OWN DESCRIPTION.
 */
public aspect HelloAopTest_Demo_Log {
	pointcut log():
		execution(* HelloAopTest.sayHello(..));
	before():log(){
		System.out.println("Log before.");
	}
	after() returning :log(){
		System.out.println("Log after.");
	}
}
