package cn.hpt.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.ui.MainFrame;
import cn.hpt.ui.model.MedicineTableCellEditor;
import cn.hpt.ui.view.PriceDialog;
import cn.hpt.ui.view.PriceIIDialog;

@Component
public class PanelStatusListener implements ActionListener {

    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private PriceDialog priceDialog;
    @Autowired
    private PriceIIDialog priceIIDialog;
    @Autowired
    private MedicineTableCellEditor cellEditor;

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand != null
                && actionCommand.equalsIgnoreCase(mainFrame.operatorMenuItem.getActionCommand())) {
            mainFrame.actionTabbedPane.addTab(mainFrame.operatorMenuItem.getText(), mainFrame.operatorPanel);// 员工管理
            mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.operatorPanel);

        } else if (actionCommand != null
                && actionCommand.equalsIgnoreCase(mainFrame.medicineCtMenuItem.getActionCommand())) {
            mainFrame.actionTabbedPane.addTab(mainFrame.medicineCtMenuItem.getText(), mainFrame.categoryPanel);// 收费类别
            mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.categoryPanel);

        } else if (actionCommand != null
                && actionCommand.equalsIgnoreCase(mainFrame.medicineMenuItem.getActionCommand())) {
            cellEditor.removeAllItems();
            cellEditor.init();
            mainFrame.actionTabbedPane.addTab(mainFrame.medicineMenuItem.getText(), mainFrame.medicinePanel);// 收费项目信息
            mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.medicinePanel);

        } else if (actionCommand != null
                && actionCommand.equalsIgnoreCase(mainFrame.billSearchMenuItem.getActionCommand())) {
            mainFrame.actionTabbedPane.addTab(mainFrame.billSearchMenuItem.getText(), mainFrame.billSearchPanel);// 收费查询
            mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.billSearchPanel);

        } else if (actionCommand != null
                && actionCommand.equalsIgnoreCase(mainFrame.priceMenuItem.getActionCommand())) {// 划价收费
            priceIIDialog.initData();
            priceIIDialog.setVisible(true);
        } else if (actionCommand != null
                && actionCommand.equalsIgnoreCase(mainFrame.statMenuItem.getActionCommand())) {
            mainFrame.actionTabbedPane.addTab(mainFrame.statMenuItem.getText(), mainFrame.statPanel);// 统计分析
            mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.statPanel);
        } else if (actionCommand != null
                && actionCommand.equalsIgnoreCase(mainFrame.cancelMenuItem.getActionCommand())) {
            mainFrame.actionTabbedPane.addTab(mainFrame.cancelMenuItem.getText(), mainFrame.billCancelPanel);// 票据作废
            mainFrame.actionTabbedPane.setSelectedComponent(mainFrame.billCancelPanel);
        } else {
            //TODO
        }
    }
}
