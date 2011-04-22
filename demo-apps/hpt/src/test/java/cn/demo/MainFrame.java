package cn.demo;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

/**
 * Date: 2009-9-2
 * Time: 17:38:32
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class MainFrame extends JFrame {
    private JMenuBar jmenuBar;
    private JMenu jmenu;

    private JDialog jdialog;

    private DemoCloseActionListener closeActionListener;

    public void setJmenuBar(JMenuBar jmenuBar) {
        this.jmenuBar = jmenuBar;
    }

    public void setJmenu(JMenu jmenu) {
        this.jmenu = jmenu;
    }

    public void setJdialog(JDialog jdialog) {
        this.jdialog = jdialog;
    }

    public void setCloseActionListener(DemoCloseActionListener closeActionListener) {
        this.closeActionListener = closeActionListener;
    }

    public void init() {
    	setVisible(false);
        //小图标
        ImageIcon ii=new ImageIcon(MainFrame.class.getResource("/frame.png"));
        setIconImage(ii.getImage());     
        //菜单
        jmenuBar.add(jmenu);
        setJMenuBar(jmenuBar);
        //最大化
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String message = "William Shakespeare was born\n"
                + "on April 23, 1564 in\n" + "Stratford-on-Avon near London.";
        jdialog.setContentPane(new JFrame().getContentPane());
        jdialog.add(new JOptionPane(message));
        jdialog.pack();
        jdialog.setVisible(false);
        jdialog.setAlwaysOnTop(true);
        jdialog.setLocation(200, 200);
        //事件
        addWindowListener(closeActionListener);
        //紧缩、可最改变尺寸
        pack();
        setResizable(true);
                
        System.out.printf("[%s]\n",ii.toString());
    }

    public void view() {
        this.setVisible(true);
    }
}
