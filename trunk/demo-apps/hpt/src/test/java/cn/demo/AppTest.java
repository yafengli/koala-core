package cn.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;


/**
 * Unit test for simple App.
 */
public class AppTest {
    private ApplicationContext ctx;


    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext-common.xml");
        System.out.println("[ctx:]" + ctx);
    }

    @Test
    public void testFrame() {
        try {
            JFrame jf = new JFrame();
            JMenuBar jb = new JMenuBar();
            JMenu jm = new JMenu();
            jm.setText("测试");
            JMenuItem ji = new JMenuItem();
            ji.setText("打开");
            jm.add(ji);
            jb.add(jm);
            jf.setJMenuBar(jb);
            jf.pack();
            jf.setVisible(true);
            Thread.sleep(10000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
