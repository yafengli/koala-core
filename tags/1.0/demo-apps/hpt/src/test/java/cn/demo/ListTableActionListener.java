package cn.demo;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 * Date: 2009-9-4
 * Time: 15:57:07
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public abstract class ListTableActionListener implements ActionListener {
    protected JTable jTable;
    protected List<Object> list;
    protected JDialog jdialog;
    protected JFrame mainFrame;

    public void setJTable(JTable jTable) {
        this.jTable = jTable;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public void setJdialog(JDialog jdialog) {
        this.jdialog = jdialog;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}

