package cn.hpt.ui.toolbar;

import cn.hpt.ui.MainFrame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillSearchAction extends AbstractAction {

    private static final long serialVersionUID = 6493919264600175500L;
    @Autowired
    private MainFrame mainFrame;

    public BillSearchAction() {
        super("划价收费", new ImageIcon(BillSearchAction.class.getResource("/logo/6.png")));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.outpatientSearchMenuItem.getText(), mainFrame.billSearchPanel);// 收费查询
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.billSearchPanel);
    }
}
