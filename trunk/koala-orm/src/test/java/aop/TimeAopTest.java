package aop;

import org.koala.orm.aopi.Target;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

public class TimeAopTest {
	@Test
	public void testSay() {
		ApplicationContext factory = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/spring.xml"});
//		Resource resource = new ClassPathResource("META-INF/spring/spring.xml");
//		BeanFactory factory = new XmlBeanFactory(resource);
		Target targeta = (Target) factory.getBean("targetA");
		Target targetb = (Target) factory.getBean("targetB");
		targeta.display();
		targetb.display();
	}
}
