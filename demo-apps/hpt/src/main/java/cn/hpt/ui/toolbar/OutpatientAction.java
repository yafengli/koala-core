package cn.hpt.ui.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.ui.view.PriceDialog;
import cn.hpt.ui.view.PriceIIDialog;
import java.io.File;
import javax.swing.Action;

@Component
public class OutpatientAction extends AbstractAction {

    private static final long serialVersionUID = 6493919264600175500L;
    @Autowired
    private PriceDialog priceDialog;
    @Autowired
    private PriceIIDialog priceIIDialog;

    public OutpatientAction() {
        try {
            putValue(Action.NAME, "划价收费");
            File f = new File(System.getProperty("user.dir"), "img/price.png");
            putValue(Action.SMALL_ICON, new ImageIcon(f.toURI().toURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 划价收费
        // priceDialog.reload();
        priceIIDialog.initData();
        priceIIDialog.setVisible(true);
    }
}
