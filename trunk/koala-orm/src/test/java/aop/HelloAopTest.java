package aop;

import org.koala.orm.aop.HelloAop;
import org.testng.annotations.Test;

public class HelloAopTest {

	@Test
	public void testSay() {
		HelloAop test = new HelloAop();
		test.sayHello();
		test.sayHello2();
	}
}
