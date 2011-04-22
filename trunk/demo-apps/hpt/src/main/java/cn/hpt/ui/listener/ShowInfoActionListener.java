package cn.hpt.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.ui.MainFrame;
import cn.hpt.util.PropertiesLoader;

@Component
public class ShowInfoActionListener implements ActionListener {
	public static final String KEY_INFO_MSG = "main.info.msg";

	@Autowired
	private PropertiesLoader propertiesLoader;
	@Autowired
	private MainFrame mainFrame;

	@Override
	public void actionPerformed(ActionEvent e) {

		JOptionPane.showMessageDialog(null, propertiesLoader
				.getString(KEY_INFO_MSG), mainFrame.helpMenu.getText(),
				JOptionPane.CLOSED_OPTION);

	}

}
