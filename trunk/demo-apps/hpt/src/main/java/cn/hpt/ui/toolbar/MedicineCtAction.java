package cn.hpt.ui.toolbar;

import cn.hpt.ui.MainFrame;
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
public class MedicineCtAction extends AbstractAction {
    @Autowired
    private MainFrame mainFrame;

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.medicineCtMenuItem.getText(), mainFrame.categoryPanel);// 药品类别
            mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.categoryPanel);
    }

    public MedicineCtAction() {
        super("收费类别", new ImageIcon(OutpatientAction.class.getResource("/logo/mdct.png")));
    }
}
