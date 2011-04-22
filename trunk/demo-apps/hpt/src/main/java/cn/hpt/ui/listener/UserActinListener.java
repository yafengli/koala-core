package cn.hpt.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.ui.view.ModifyInfoDialog;

@Component
public class UserActinListener implements ActionListener {

	@Autowired
	private ModifyInfoDialog modifyInfoDialog;

	@Override
	public void actionPerformed(ActionEvent e) {
		modifyInfoDialog.reload();
	}
}
