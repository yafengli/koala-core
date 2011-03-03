package aop;

import org.junit.Test;
import org.koala.orm.aop.HelloAop;

/**
 * User: phoenixup
 * Date: 11-3-3
 * Time: 上午9:39
 * Desc: //TODO:WRITE YOUR OWN DESCRIPTION.
 */
public class HelloAopTest {

	@Test
	public void testSay() {
		HelloAop test = new HelloAop();
		test.sayHello();
		test.sayHello2();
	}
}
