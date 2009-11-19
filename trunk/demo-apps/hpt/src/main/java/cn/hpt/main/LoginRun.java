package cn.hpt.main;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hpt.ui.LoginWindow;
import cn.hpt.ui.extend.HptFont;

/**
 * Hello world!
 */
public class LoginRun {

    private static ApplicationContext ctx;

    public static void main(String[] args) {
        try {
            init();
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    LoginWindow lw = (LoginWindow) ctx.getBean(LoginWindow.BEAN_NAME);
                    lw.setLocationRelativeTo(null);
                    lw.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        try {
            ctx = new ClassPathXmlApplicationContext(new String[]{
                        "META-INF/applicationContext-common.xml",
                        "META-INF/applicationContext-swing.xml",
                        "META-INF/applicationContext-jpa.xml"});
            initLookAndFeel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initLookAndFeel() {
        try {
            HptFont font = (HptFont) ctx.getBean("hptFont");
            font.getSize_12();
//            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.put("ToolTip.font", font.getSize_12());
            UIManager.put("Table.font", font.getSize_12());
            UIManager.put("TableHeader.font", font.getSize_12());
            UIManager.put("TextField.font", font.getSize_12());
            UIManager.put("ComboBox.font", font.getSize_12());
            UIManager.put("TextField.font", font.getSize_12());
            UIManager.put("PasswordField.font", font.getSize_12());
            UIManager.put("TextArea.font", font.getSize_12());
            UIManager.put("TextPane.font", font.getSize_12());
            UIManager.put("EditorPane.font", font.getSize_12());
            UIManager.put("FormattedTextField.font", font.getSize_12());
            UIManager.put("Button.font", font.getSize_12());
            UIManager.put("CheckBox.font", font.getSize_12());
            UIManager.put("RadioButton.font", font.getSize_12());
            UIManager.put("ToggleButton.font", font.getSize_12());
            UIManager.put("ProgressBar.font", font.getSize_12());
            UIManager.put("DesktopIcon.font", font.getSize_12());
            UIManager.put("TitledBorder.font", font.getSize_12());
            UIManager.put("Label.font", font.getSize_12());
            UIManager.put("List.font", font.getSize_12());
            UIManager.put("TabbedPane.font", font.getSize_12());
            UIManager.put("MenuBar.font", font.getSize_12());
            UIManager.put("Menu.font", font.getSize_12());
            UIManager.put("MenuItem.font", font.getSize_12());
            UIManager.put("PopupMenu.font", font.getSize_12());
            UIManager.put("CheckBoxMenuItem.font", font.getSize_12());
            UIManager.put("RadioButtonMenuItem.font", font.getSize_12());
            UIManager.put("Spinner.font", font.getSize_12());
            UIManager.put("Tree.font", font.getSize_12());
            UIManager.put("ToolBar.font", font.getSize_12());
            UIManager.put("OptionPane.messageFont", font.getSize_12());
            UIManager.put("OptionPane.buttonFont", font.getSize_12());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
