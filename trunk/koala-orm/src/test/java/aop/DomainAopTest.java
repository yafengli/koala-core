package aop;

import org.koala.orm.aop.DomainA;
import org.testng.annotations.Test;

public class DomainAopTest {
	@Test
	public void testSay() {
		DomainA a = new DomainA();
		a.action();
	}
}
