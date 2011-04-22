package cn.hpt.ui.toolbar;

import cn.hpt.ui.MainFrame;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.hpt.ui.model.MedicineTableCellEditor;

/**
 *
 * @author Administrator
 */
@Component
public class MedicineAction extends AbstractAction {

    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private MedicineTableCellEditor cellEditor;

    @Override
    public void actionPerformed(ActionEvent e) {
        cellEditor.removeAllItems();
        cellEditor.init();
        mainFrame.actionTabbedPane.addTab(mainFrame.medicineMenuItem.getText(), mainFrame.medicinePanel);// 药品信息
        mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.medicinePanel);
    }

    public MedicineAction() {
        try {
            File f = new File(System.getProperty("user.dir"), "img/item.png");
            putValue(Action.NAME, "收费项目");
            putValue(Action.SMALL_ICON, new ImageIcon(f.toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
