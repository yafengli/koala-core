package aop;

import org.junit.Test;

/**
 * User: phoenixup
 * Date: 11-3-3
 * Time: 上午9:39
 * Desc: //TODO:WRITE YOUR OWN DESCRIPTION.
 */
public class HelloAopTest {
	private void sayHello() {
		System.out.println("Hello World!");
	}

	@Test
	public void testSay() {
		HelloAopTest test = new HelloAopTest();
		test.sayHello();
	}
}
