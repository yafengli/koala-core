package cn.hpt.ui.toolbar;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class StatAction extends AbstractAction {

    public StatAction() {
        super("", new ImageIcon(OutpatientAction.class.getResource("/logo/7.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(null, "Test", "fuck", JOptionPane.YES_NO_OPTION);
    }
}
