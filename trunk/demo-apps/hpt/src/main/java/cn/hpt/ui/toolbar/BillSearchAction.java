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
public class BillSearchAction extends AbstractAction {

    private static final long serialVersionUID = 6493919264600175500L;
    @Autowired
    private MainFrame mainFrame;

    public BillSearchAction() {
        try {
            putValue(Action.NAME, "票据查询");
            File f = new File(System.getProperty("user.dir"), "img/search.png");
            putValue(Action.SMALL_ICON, new ImageIcon(f.toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.billSearchMenuItem.getText(), mainFrame.billSearchPanel);// 收费查询
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.billSearchPanel);
    }
}
