package cn.hpt.main;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hpt.ui.LoginWindow;

/**
 * Hello world!
 */
public class LoginRun {

    private static ApplicationContext ctx;

    public static void main(String[] args) {
        try {

//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            init();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    LoginWindow lw = (LoginWindow) ctx
                            .getBean(LoginWindow.BEAN_NAME);
                    lw.setLocationRelativeTo(null);
                    lw.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{
                "META-INF/applicationContext-common.xml",
                "META-INF/applicationContext-swing.xml",
                "META-INF/applicationContext-jpa.xml"});
    }
}
