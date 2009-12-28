package cn.hpt.ui.toolbar;

import cn.hpt.ui.MainFrame;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class StatAction extends AbstractAction {

    @Autowired
    private MainFrame mainFrame;

    public StatAction() {
        try {
            File f = new File(System.getProperty("user.dir"), "img/stat.png");
            putValue(Action.NAME, "报表分析");
            putValue(Action.SMALL_ICON, new ImageIcon(f.toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.statMenuItem.getText(), mainFrame.statPanel);// 
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.statPanel);
    }
}
