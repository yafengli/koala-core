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
            /* 普通用户不可以使用的控制菜单 */
            ltc.add(mainFrame.stockMenu);
            ltc.add(mainFrame.systemMenu);
            ltc.add(mainFrame.statMenu);
            ltc.add(mainFrame.cancelMenuItem);
        }
        return ltc;
    }

    private void operator() {
        for (java.awt.Component c : getLtc()) {
            c.setEnabled(false);
        }
        //toolbar
        {
            mainFrame.billCancelAction.setEnabled(false);
            mainFrame.medicineCtAction.setEnabled(false);
            mainFrame.medicineAction.setEnabled(false);
            mainFrame.statAction.setEnabled(false);
        }
        int i = mainFrame.actionTabbedPane.getTabCount();
        for (int j = 1; j < i; j++) {
            mainFrame.actionTabbedPane.setEnabledAt(j, false);
        }
    }

    private void admin() {
        for (java.awt.Component c : getLtc()) {
            if (c != null) {
                c.setEnabled(true);
            }
        }
        int i = mainFrame.actionTabbedPane.getTabCount();
        for (int j = 0; j < i; j++) {
            mainFrame.actionTabbedPane.setEnabledAt(j, true);
        }
        /* 为完成的功能 */
        {
            mainFrame.warehouseMenuItem.setEnabled(false);
            mainFrame.dataRstMenuItem.setEnabled(false);
            mainFrame.dataBkMenuItem.setEnabled(false);
            mainFrame.purchaseSearcheMenuItem.setEnabled(false);
            mainFrame.purchaseStMenuItem.setEnabled(false);
            mainFrame.purchCtMenuItem.setEnabled(false);
            mainFrame.roleMenuItem.setEnabled(false);
            mainFrame.purchaseMenuItem.setEnabled(false);
            mainFrame.configMenuItem.setEnabled(false);
            mainFrame.caseTmMenuItem.setEnabled(false);
        }
    }
}
