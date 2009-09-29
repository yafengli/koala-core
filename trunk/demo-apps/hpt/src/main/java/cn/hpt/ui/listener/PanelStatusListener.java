package cn.hpt.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.ui.MainFrame;
import cn.hpt.ui.view.PriceDialog;

@Component
public class PanelStatusListener implements ActionListener {
	@Autowired
	private MainFrame mainFrame;
	@Autowired
	private PriceDialog priceDialog;

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand != null
				&& actionCommand.equalsIgnoreCase(mainFrame.operatorMenuItem
						.getActionCommand())) {
			mainFrame.actionTabbedPane.addTab(mainFrame.operatorMenuItem
					.getText(), mainFrame.operatorPanel);// 员工管理
			mainFrame.actionTabbedPane
					.setSelectedComponent(mainFrame.operatorPanel);

		} else if (actionCommand != null
				&& actionCommand.equalsIgnoreCase(mainFrame.medicineCtMenuItem
						.getActionCommand())) {
			mainFrame.actionTabbedPane.addTab(mainFrame.medicineCtMenuItem
					.getText(), mainFrame.categoryPanel);// 药品类别
			mainFrame.actionTabbedPane
					.setSelectedComponent(mainFrame.categoryPanel);

		} else if (actionCommand != null
				&& actionCommand.equalsIgnoreCase(mainFrame.medicineMenuItem
						.getActionCommand())) {
			mainFrame.actionTabbedPane.addTab(mainFrame.medicineMenuItem
					.getText(), mainFrame.medicinePanel);// 药品信息
			mainFrame.actionTabbedPane
					.setSelectedComponent(mainFrame.medicinePanel);

		} else if (actionCommand != null
				&& actionCommand
						.equalsIgnoreCase(mainFrame.outpatientSearchMenuItem
								.getActionCommand())) {
			mainFrame.actionTabbedPane.addTab(mainFrame.outpatientSearchMenuItem
					.getText(), mainFrame.billPanel);// 收费查询
			mainFrame.actionTabbedPane
					.setSelectedComponent(mainFrame.billPanel);

		} else {
			// 划价收费
			priceDialog.setLocationRelativeTo(null);
			priceDialog.setVisible(true);
		}
	}
}
