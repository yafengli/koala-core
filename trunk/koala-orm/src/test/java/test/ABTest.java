package test;


import org.koala.orm.config.A;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.koala.orm.config.B;

public class ABTest {
	private ApplicationContext ctx;
	private A a;
	private B b;

	@BeforeTest
	public void init() {
		ctx = new ClassPathXmlApplicationContext(
				new String[]{"META-INF/spring/applicationContext-bean.xml"});
		b = ctx.getBean("b", B.class);
		a = ctx.getBean("a", A.class);
	}

	@Test
	public void testAB() {
		System.out.printf("Msg:%s@%s@%s\n", a.getaInject().getInjectName(), b.getA().getaInject().getInjectName(),a.getMessage());
		a.getaInject().sayHello();
	}
}
