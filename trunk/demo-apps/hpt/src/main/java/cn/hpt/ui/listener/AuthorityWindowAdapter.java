package cn.hpt.ui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hpt.model.Role;
import cn.hpt.ui.MainFrame;

/**
 * 
 * @author YaFengLi 菜单权限处理
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AuthorityWindowAdapter extends WindowAdapter {
	@Autowired
	private MainFrame mainFrame;
	private List<java.awt.Component> ltc = new ArrayList<java.awt.Component>();

	public void windowActivated(WindowEvent e) {
		Role role = mainFrame.getRole();
		if (role == null
				|| role.getRolename().equalsIgnoreCase(Role.OPERATOR_ROLE)) {
			operator();
		} else if (role != null
				&& role.getRolename().equalsIgnoreCase(Role.ADMIN_ROLE)) {
			admin();
		}
	}

	private List<java.awt.Component> getLtc() {
		if (ltc.size() < 1) {
			/* 控制菜单 */
			ltc.add(mainFrame.stockMenu);
			ltc.add(mainFrame.systemMenu);
			ltc.add(mainFrame.cancelMenuItem);
			ltc.add(mainFrame.outpatientSearchMenuItem);
		}
		return ltc;
	}

	private void operator() {
		for (java.awt.Component c : getLtc()) {
			c.setEnabled(false);
		}		
		int i = mainFrame.actionTabbedPane.getTabCount();
		for (int j = 1; j < i; j++) {
			mainFrame.actionTabbedPane.setEnabledAt(j, false);
		}
	}

	private void admin() {
		for (java.awt.Component c : getLtc()) {
			if (c != null)
				c.setEnabled(true);
		}
		int i = mainFrame.actionTabbedPane.getTabCount();
		for (int j = 0; j < i; j++) {
			mainFrame.actionTabbedPane.setEnabledAt(j, true);
		}
	}
}
