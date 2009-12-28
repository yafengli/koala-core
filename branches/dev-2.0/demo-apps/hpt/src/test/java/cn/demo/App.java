package cn.demo;

import javax.swing.UIManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.demo.dao.IDemoDao;
import cn.demo.model.Demo;

/**
 * Hello world!
 */
public class App {

	private static ApplicationContext ctx;

	public static void main(String[] args) {
		try {
			 init();
			// splash
			UIManager
					.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
			frame();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void init() {
		ctx = new ClassPathXmlApplicationContext(
				new String[] {
						"META-INF/demo-common.xml",
						"META-INF/demo-swing.xml",
						"META-INF/demo-jpa.xml" });
	}

	private static void db() {
		IDemoDao demoDao = (IDemoDao) ctx.getBean("demoDao");
		Demo demo = new Demo();
		demo.setName("内容");
		demo.setDesc("asdfafasfasdf");
		demoDao.save(demo);
	}

	private static void frame() {
		MainFrame mf = (MainFrame) ctx.getBean("testBean");
		mf.view();
	}
}
