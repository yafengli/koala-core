package cn.hpt.ui.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.ui.view.PriceDialog;

@Component
public class OutpatientAction extends AbstractAction {
	private static final long serialVersionUID = 6493919264600175500L;

	@Autowired
	private PriceDialog priceDialog;
    
	public OutpatientAction() {
		super("", new ImageIcon(OutpatientAction.class
				.getResource("/logo/6.png")));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 划价收费
		priceDialog.reload();
	}
}
