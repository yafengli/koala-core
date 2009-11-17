package cn.hpt.ui.toolbar;

import cn.hpt.ui.MainFrame;
import cn.hpt.ui.listener.PanelStatusListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
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
        super("报表分析", new ImageIcon(OutpatientAction.class.getResource("/logo/7.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.statMenuItem.getText(), mainFrame.statPanel);// 
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.statPanel);
    }
}
