package cn.hpt.ui.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.ui.view.PriceDialog;
import cn.hpt.ui.view.PriceIIDialog;

@Component
public class OutpatientAction extends AbstractAction {

    private static final long serialVersionUID = 6493919264600175500L;
    @Autowired
    private PriceDialog priceDialog;
    @Autowired
    private PriceIIDialog priceIIDialog;

    public OutpatientAction() {
        super("划价收费", new ImageIcon(OutpatientAction.class.getResource("/logo/outp.png")));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 划价收费
        // priceDialog.reload();
        priceIIDialog.initData();
        priceIIDialog.setVisible(true);
    }
}
