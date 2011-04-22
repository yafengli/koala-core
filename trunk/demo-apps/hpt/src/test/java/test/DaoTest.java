package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.demo.dao.IDemoDao;
import cn.demo.model.Demo;

public class DaoTest {
	private ApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"META-INF/demo-common.xml", "META-INF/demo-swing.xml",
				"META-INF/demo-jpa.xml" });
	}

	@Test
	public void testInsert() {
		IDemoDao ddao = (IDemoDao) ctx.getBean("demoDao");
		Demo d = new Demo();
		d.setDesc("One");
		d.setName("一");
		ddao.save(d);

		for (Demo item : ddao.findAll()) {
			System.out.printf("[%s,%s]\n", item.getName(), item.getDesc());
		}
	}	
}
