package cn.hpt.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.koala.dao.IDao;

import cn.hpt.model.Operator;
import cn.hpt.ui.LoginWindow;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.view.PriceDialog;
import cn.hpt.util.PropertiesLoader;

@Component(ListenerDefinition.LOGIN_BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LoginActionListener extends KeyAdapter implements ActionListener {

    @Autowired
    private LoginWindow loginWindow;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private IDao baseDao;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private PriceDialog priceDialog;

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = false;
        String name = (String) loginWindow.getNameComboBox().getSelectedItem();
        String password = new String(loginWindow.getPasswordTextField()
                .getPassword());

        List<Operator> lo = baseDao.find("find.by.loginname",
                new String[]{"loginname"}, new Object[]{name});
        Operator op = null;
        if (lo != null && lo.size() == 1) {
            op = lo.get(0);
            if (op.getPassword().trim().equalsIgnoreCase(password)) {
                op.setLastlogin(new Timestamp(System.currentTimeMillis()));
                baseDao.update(op);
                flag = true;
            }
        }
        if (flag) {
            loginWindow.setVisible(false);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setOperator(op);
            mainFrame.setRole(op.getRole());
            mainFrame.actionTabbedPane.removeAll();
            mainFrame.setVisible(true);
            priceDialog.operatorField.setText(op.getLoginname());
        } else {
            JOptionPane.showMessageDialog(null, String.format(pl.getString("login.error"), op.getLoginname()), pl.getString("login.msg"), JOptionPane.CLOSED_OPTION);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            actionPerformed(null);
        }
    }

}
