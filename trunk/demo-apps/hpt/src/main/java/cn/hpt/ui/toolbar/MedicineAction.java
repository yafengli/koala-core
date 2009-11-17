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
public class MedicineAction extends AbstractAction {

    @Autowired
    private MainFrame mainFrame;

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.actionTabbedPane.addTab(mainFrame.medicineMenuItem.getText(), mainFrame.medicinePanel);// 药品信息
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.medicinePanel);
    }

    public MedicineAction() {
        super("收费项目", new ImageIcon(OutpatientAction.class.getResource("/logo/medi.png")));
    }
}
