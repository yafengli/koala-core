package cn.hpt.ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.koala.dao.IDao;

import cn.hpt.ui.MainFrame;
import cn.hpt.ui.component.ModifyInfoPanel;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.util.PropertiesLoader;
import cn.hpt.util.WindowUtil;

@Component
public class ModifyInfoDialog extends JDialog {

    private static final long serialVersionUID = -1547042069151210354L;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private IDao baseDao;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private HptFont font;
    @Autowired
    private ModifyInfoPanel operatorForm;
    private JPanel actionp = new JPanel();
    private JButton submit = new JButton("修改");
    private JButton close = new JButton("关闭");

    @PostConstruct
    public void init() {
        this.setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().add(operatorForm, BorderLayout.CENTER);



        submit.setFocusable(true);
        close.setFocusable(true);
        final JDialog f = this;
        submit.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_ENTER) {
                    submit();
                }
            }
        });
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });
        close.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_ENTER) {
                    f.dispose();
//                    f.setVisible(false);
                }
            }
        });
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
//                f.setVisible(false);
            }
        });
        actionp.add(submit);
        actionp.add(close);
        //font
        {
            submit.setFont(font.getSize_12());
            close.setFont(font.getSize_12());
        }
        pack();
        getContentPane().add(actionp, BorderLayout.SOUTH);
    }

    public void reload() {
        operatorForm.load(mainFrame.getOperator());
        pack();
        setLocation(WindowUtil.center(this));
        setVisible(true);
    }

    private void submit() {
        int option = JOptionPane.showConfirmDialog(mainFrame, pl.getString("useraction.listener.confirm"),
                mainFrame.userMenuItem.getText(), JOptionPane.YES_NO_OPTION);
        switch (option) {
            case JOptionPane.YES_OPTION:
                try {
                    System.out.println(operatorForm + "||");
                    String cpw = new String(((JPasswordField) ModifyInfoPanel.psl.get(
                            2).getField()).getPassword());
                    String npw = new String(((JPasswordField) ModifyInfoPanel.psl.get(
                            3).getField()).getPassword());
                    String mpw = new String(((JPasswordField) ModifyInfoPanel.psl.get(
                            4).getField()).getPassword());
                    if (mainFrame.getOperator().getPassword().equalsIgnoreCase(cpw)
                            && ((mpw == null && npw == null) || (npw.equalsIgnoreCase(mpw)
                            && npw.length() >= 5 && npw.length() <= 10))) {
                        if (npw != null) {
                            mainFrame.getOperator().setPassword(npw);
                        }
                        baseDao.update(mainFrame.getOperator());
                        this.setVisible(false);
                        JOptionPane.showMessageDialog(mainFrame, pl.getString("useraction.listener.success"),
                                mainFrame.userMenuItem.getText(),
                                JOptionPane.CLOSED_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, pl.getString("useraction.listener.error"),
                                mainFrame.userMenuItem.getText(),
                                JOptionPane.CLOSED_OPTION);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;
            case JOptionPane.NO_OPTION:
                System.out.println(operatorForm + "::");
                break;
        }
    }
}
