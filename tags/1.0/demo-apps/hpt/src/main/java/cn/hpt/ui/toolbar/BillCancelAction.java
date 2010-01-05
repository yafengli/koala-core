package cn.hpt.ui.toolbar;

import cn.hpt.ui.MainFrame;
import java.awt.event.ActionEvent;

import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillCancelAction extends AbstractAction {

    private static final long serialVersionUID = 6493919264600175500L;
    @Autowired
    private MainFrame mainFrame;

    public BillCancelAction() {
        try {
            putValue(Action.NAME, "票据作废");
            File f = new File(System.getProperty("user.dir"), "img/cancel.png");
            putValue(Action.SMALL_ICON, new ImageIcon(f.toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.cancelMenuItem.getText(), mainFrame.billCancelPanel);// 票据作废
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.billCancelPanel);
    }
}