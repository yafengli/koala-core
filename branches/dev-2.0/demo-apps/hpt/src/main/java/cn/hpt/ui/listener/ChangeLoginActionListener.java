package cn.hpt.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hpt.ui.LoginWindow;
import cn.hpt.ui.MainFrame;

@Component(ListenerDefinition.CHANGE_LOGIN_BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ChangeLoginActionListener implements ActionListener {

	@Autowired
	private LoginWindow loginWindow;
	@Autowired
	private MainFrame mainFrame;

	public void actionPerformed(ActionEvent e) {
		mainFrame.setVisible(false);

		loginWindow.getPasswordTextField().setText(null);
		loginWindow.getPasswordTextField().updateUI();
		loginWindow.setLocationRelativeTo(null);
		loginWindow.setVisible(true);
	}

}
