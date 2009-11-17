package cn.hpt.ui.toolbar;

import cn.hpt.ui.MainFrame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BillCancelAction extends AbstractAction {

    private static final long serialVersionUID = 6493919264600175500L;
    @Autowired
    private MainFrame mainFrame;

    public BillCancelAction() {
        super("划价收费", new ImageIcon(BillCancelAction.class.getResource("/logo/6.png")));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.cancelMenuItem.getText(), mainFrame.billCancelPanel);// 票据作废
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.billCancelPanel);
    }
}
