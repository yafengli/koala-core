package cn.hpt.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.extend.JClosableTabbedPane;
import cn.hpt.model.Operator;
import cn.hpt.model.Role;
import cn.hpt.ui.component.CategoryPanel;
import cn.hpt.ui.component.MedicinePanel;
import cn.hpt.ui.component.OperatorPanel;
import cn.hpt.ui.component.BillPanel;
import cn.hpt.ui.listener.AuthorityWindowAdapter;
import cn.hpt.ui.listener.ChangeLoginActionListener;
import cn.hpt.ui.listener.CloseAppActionListener;
import cn.hpt.ui.listener.PanelStatusListener;
import cn.hpt.ui.listener.ShowInfoActionListener;
import cn.hpt.ui.listener.UserActinListener;
import cn.hpt.ui.toolbar.OutpatientAction;
import cn.hpt.util.PropertiesLoader;

@Component
public class MainFrame extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -1428298345760966376L;

    public JMenuBar mainMenuBar;
    public JMenuItem medicineSearcheMenuItem;
    public JMenuItem infoMenuItem;
    public JSeparator quitOneSeparator;
    public JMenuItem quitMenuItem;
    public JMenuItem changeloginMenuItem;
    public JMenuItem userMenuItem;
    public JMenuItem configMenuItem;
    public JSeparator sysThreeSeparator;
    public JMenuItem caseTmMenuItem;
    public JMenuItem billMenuItem;
    public JSeparator sysTwoSeparator;
    public JMenuItem roleMenuItem;
    public JMenuItem purchaseMenuItem;
    public JMenuItem operatorMenuItem;
    public JSeparator sysOneSeparator;
    public JMenuItem dataRstMenuItem;
    public JMenuItem dataBkMenuItem;
    public JMenuItem purchaseSearcheMenuItem;
    public JSeparator purchaseSeparator;
    public JMenuItem purchaseStMenuItem;
    public JMenuItem purchCtMenuItem;
    public JSeparator medicineSearcheSeparator;
    public JSeparator medicineSeparator;
    public JMenu stockMenu;
    public JMenu systemMenu;
    public JMenuItem cancelMenuItem;
    public JMenuItem warehouseMenuItem;
    public JMenuItem medicineMenuItem;
    public JMenuItem medicineCtMenuItem;
    public JSeparator outpatientSeparator;
    public JMenuItem priceMenuItem;
    public JMenu helpMenu;
    public JMenu quitMenu;
    public JMenu personMenu;
    public JMenu outpatientMenu;
    public JToolBar mainToolBar;
    public JPanel buttonPanel;
    public JButton printButton;
    public JButton addButton;
    public JScrollPane editPane;
    public JMenuItem outpatientSearchMenuItem;
    public JSeparator outpatienSeparator_2;
    public JButton delButton;
    public JButton excelButton;
    public JClosableTabbedPane actionTabbedPane;

    /**
     * **** 华丽的分割线 *****
     */
    @Autowired
    public OutpatientAction outpatientAction;
    @Autowired
    public CloseAppActionListener closeAppActionListener;
    @Autowired
    public ChangeLoginActionListener changeLoginActionListener;
    @Autowired
    public AuthorityWindowAdapter authorityWindowAdapter;
    @Autowired
    public ShowInfoActionListener showInfoActionListener;
    @Autowired
    public UserActinListener userActinListener;
    @Autowired
    public PanelStatusListener panelStatusListener;
    @Autowired
    public PropertiesLoader pl;
    @Autowired
    public CategoryPanel categoryPanel;
    @Autowired
    public OperatorPanel operatorPanel;
    @Autowired
    public MedicinePanel medicinePanel;
    @Autowired
    public BillPanel billPanel;


    /**
     * **** 华丽的分割线 *****
     */
    private Operator operator;
    private Role role;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame inst = new MainFrame(false);
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public MainFrame() {
        super();

    }

    public MainFrame(boolean flag) {
        super();
        initGUI();
    }

    @PostConstruct
    public void initGUI() {
        try {
            init();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            {
                mainToolBar = new JToolBar();
                mainToolBar.add(outpatientAction);
                getContentPane().add(mainToolBar, BorderLayout.NORTH);
                this.addWindowListener(authorityWindowAdapter);
            }
            {
                mainMenuBar = new JMenuBar();
                setJMenuBar(mainMenuBar);
                {
                    outpatientMenu = new JMenu();
                    outpatientMenu.setMnemonic(KeyEvent.VK_F);
                    mainMenuBar.add(outpatientMenu);
                    outpatientMenu.setText("\u95e8\u8bca\u6536\u8d39(F)");
                    {
                        priceMenuItem = new JMenuItem();
                        priceMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_P, KeyEvent.CTRL_MASK, true));
                        priceMenuItem.addActionListener(panelStatusListener);
                        outpatientMenu.add(priceMenuItem);
                        priceMenuItem.setText("\u5212\u4ef7\u6536\u8d39");
                    }
                    {
                        outpatientSeparator = new JSeparator();
                        outpatientMenu.add(outpatientSeparator);
                    }
                    {
                        cancelMenuItem = new JMenuItem();
                        outpatientMenu.add(cancelMenuItem);
                        cancelMenuItem.setText("\u7968\u636e\u4f5c\u5e9f");
                    }
                    {
                        outpatienSeparator_2 = new JSeparator();
                        outpatientMenu.add(outpatienSeparator_2);
                    }
                    {
                        outpatientSearchMenuItem = new JMenuItem();
                        outpatientSearchMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK, true));
                        outpatientSearchMenuItem.addActionListener(panelStatusListener);
                        outpatientMenu.add(outpatientSearchMenuItem);
                        outpatientSearchMenuItem
                                .setText("\u6536\u8d39\u67e5\u8be2");
                    }
                }
                {
                    stockMenu = new JMenu();
                    stockMenu.setMnemonic(KeyEvent.VK_S);
                    mainMenuBar.add(stockMenu);
                    stockMenu.setText("\u5e93\u5b58\u7ba1\u7406(S)");
                    {
                        medicineCtMenuItem = new JMenuItem();
                        medicineCtMenuItem
                                .addActionListener(panelStatusListener);
                        medicineCtMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,KeyEvent.CTRL_MASK,true));
                        stockMenu.add(medicineCtMenuItem);
                        medicineCtMenuItem.setText("\u836f\u54c1\u7c7b\u522b");
                    }
                    {
                        medicineMenuItem = new JMenuItem();
                        medicineMenuItem.addActionListener(panelStatusListener);
                        medicineMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_MASK,true));
                        stockMenu.add(medicineMenuItem);
                        medicineMenuItem.setText("\u836f\u54c1\u4fe1\u606f");
                    }
                    {
                        warehouseMenuItem = new JMenuItem();
                        stockMenu.add(warehouseMenuItem);
                        warehouseMenuItem.setText("\u836f\u54c1\u5165\u5e93");
                    }
                    {
                        medicineSeparator = new JSeparator();
                        stockMenu.add(medicineSeparator);
                    }
                    {
                        medicineSearcheMenuItem = new JMenuItem();
                        stockMenu.add(medicineSearcheMenuItem);
                        medicineSearcheMenuItem
                                .setText("\u836f\u54c1\u5165\u5e93\u67e5\u8be2");
                    }
                    {
                        medicineSearcheSeparator = new JSeparator();
                        stockMenu.add(medicineSearcheSeparator);
                    }
                    {
                        purchCtMenuItem = new JMenuItem();
                        stockMenu.add(purchCtMenuItem);
                        purchCtMenuItem.setText("\u7269\u8d44\u7c7b\u522b");
                    }
                    {
                        purchaseMenuItem = new JMenuItem();
                        stockMenu.add(purchaseMenuItem);
                        purchaseMenuItem.setText("\u7269\u8d44\u4fe1\u606f");
                    }
                    {
                        purchaseStMenuItem = new JMenuItem();
                        stockMenu.add(purchaseStMenuItem);
                        purchaseStMenuItem.setText("\u7269\u8d44\u5165\u5e93");
                    }
                    {
                        purchaseSeparator = new JSeparator();
                        stockMenu.add(purchaseSeparator);
                    }
                    {
                        purchaseSearcheMenuItem = new JMenuItem();
                        stockMenu.add(purchaseSearcheMenuItem);
                        purchaseSearcheMenuItem
                                .setText("\u7269\u8d44\u5165\u5e93\u67e5\u8be2");
                        purchaseSearcheMenuItem.setBounds(65, 21, 84, 18);
                    }
                }
                {
                    systemMenu = new JMenu();
                    systemMenu.setMnemonic(KeyEvent.VK_M);
                    mainMenuBar.add(systemMenu);
                    systemMenu.setText("\u7cfb\u7edf\u7ba1\u7406(M)");
                    {
                        dataBkMenuItem = new JMenuItem();
                        systemMenu.add(dataBkMenuItem);
                        dataBkMenuItem.setText("\u6570\u636e\u5907\u4efd");
                    }
                    {
                        dataRstMenuItem = new JMenuItem();
                        systemMenu.add(dataRstMenuItem);
                        dataRstMenuItem.setText("\u6570\u636e\u6062\u590d");
                    }
                    {
                        sysOneSeparator = new JSeparator();
                        systemMenu.add(sysOneSeparator);
                    }
                    {
                        operatorMenuItem = new JMenuItem();
                        operatorMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_O, KeyEvent.CTRL_MASK, true));
                        operatorMenuItem.addActionListener(panelStatusListener);
                        systemMenu.add(operatorMenuItem);
                        operatorMenuItem.setText("\u5458\u5de5\u7ba1\u7406");
                    }
                    {
                        roleMenuItem = new JMenuItem();
                        roleMenuItem.addActionListener(panelStatusListener);
                        systemMenu.add(roleMenuItem);
                        roleMenuItem.setText("\u6743\u9650\u7ba1\u7406");
                    }
                    {
                        sysTwoSeparator = new JSeparator();
                        systemMenu.add(sysTwoSeparator);
                    }
                    {
                        billMenuItem = new JMenuItem();
                        systemMenu.add(billMenuItem);
                        billMenuItem.setText("\u53d1\u7968\u5f00\u5355");
                    }
                    {
                        caseTmMenuItem = new JMenuItem();
                        systemMenu.add(caseTmMenuItem);
                        caseTmMenuItem.setText("\u75c5\u4f8b\u8303\u672c");
                    }
                    {
                        sysThreeSeparator = new JSeparator();
                        systemMenu.add(sysThreeSeparator);
                    }
                    {
                        configMenuItem = new JMenuItem();
                        systemMenu.add(configMenuItem);
                        configMenuItem.setText("\u7cfb\u7edf\u8bbe\u7f6e");
                    }
                }
                {
                    personMenu = new JMenu();
                    personMenu.setMnemonic(KeyEvent.VK_R);
                    mainMenuBar.add(personMenu);
                    personMenu.setText("\u4e2a\u4eba\u7ba1\u7406(R)");
                    {
                        userMenuItem = new JMenuItem();
                        userMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_M, KeyEvent.CTRL_MASK, true));
                        userMenuItem.addActionListener(userActinListener);
                        personMenu.add(userMenuItem);
                        userMenuItem.setText("\u4fee\u6539\u4fe1\u606f");
                    }
                }
                {
                    quitMenu = new JMenu();
                    quitMenu.setMnemonic(KeyEvent.VK_Q);
                    mainMenuBar.add(quitMenu);
                    quitMenu.setText("\u9000\u51fa(Q)");
                    {
                        changeloginMenuItem = new JMenuItem();
                        changeloginMenuItem.setAccelerator(KeyStroke
                                .getKeyStroke(KeyEvent.VK_C,
                                KeyEvent.CTRL_MASK, true));
                        quitMenu.add(changeloginMenuItem);
                        changeloginMenuItem
                                .addActionListener(changeLoginActionListener);
                        changeloginMenuItem
                                .setText("\u66f4\u6362\u64cd\u4f5c\u5458");
                        changeloginMenuItem.setBounds(-53, 37, 72, 18);
                    }
                    {
                        quitOneSeparator = new JSeparator();
                        quitMenu.add(quitOneSeparator);
                    }
                    {
                        quitMenuItem = new JMenuItem();
                        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_Q, KeyEvent.CTRL_MASK, true));
                        quitMenuItem.addActionListener(closeAppActionListener);
                        quitMenu.add(quitMenuItem);
                        quitMenuItem.setText("\u9000\u51fa");
                    }
                }
                {
                    helpMenu = new JMenu();
                    helpMenu.setMnemonic(KeyEvent.VK_H);
                    mainMenuBar.add(helpMenu);
                    helpMenu.setText("\u5e2e\u52a9(H)");
                    {
                        infoMenuItem = new JMenuItem();
                        infoMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_F1, 0));
                        infoMenuItem.addActionListener(showInfoActionListener);
                        helpMenu.add(infoMenuItem);
                        infoMenuItem.setText("\u4fe1\u606f");
                    }
                }
                /* tabbedPane */
                {
                    actionTabbedPane = new JClosableTabbedPane();
                    getContentPane().add(actionTabbedPane, BorderLayout.CENTER);
                }
            }
            setIconImage(new ImageIcon(MainFrame.class.getResource(pl
                    .getString("main.frame.ico"))).getImage());
            pack();
            setSize(600, 400);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        /* init starting.. */
        {
            if (outpatientAction == null) {
                outpatientAction = new OutpatientAction();
            }
            if (closeAppActionListener == null) {
                closeAppActionListener = new CloseAppActionListener();
            }
            if (changeLoginActionListener == null) {
                changeLoginActionListener = new ChangeLoginActionListener();
            }
            if (authorityWindowAdapter == null) {
                authorityWindowAdapter = new AuthorityWindowAdapter();
            }
            if (showInfoActionListener == null) {
                showInfoActionListener = new ShowInfoActionListener();
            }
            if (pl == null) {
                pl = new PropertiesLoader();
                pl.setResource("/setting.xml");
                pl.init();
            }
        }
        /* init end. */
	}
}
