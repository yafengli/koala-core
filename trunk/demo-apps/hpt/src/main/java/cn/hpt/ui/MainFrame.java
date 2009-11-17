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
import cn.hpt.ui.component.BillCancelPanel;
import cn.hpt.ui.component.CategoryPanel;
import cn.hpt.ui.component.MedicinePanel;
import cn.hpt.ui.component.OperatorPanel;
import cn.hpt.ui.component.BillSearchPanel;
import cn.hpt.ui.component.StatPanel;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.listener.AuthorityWindowAdapter;
import cn.hpt.ui.listener.ChangeLoginActionListener;
import cn.hpt.ui.listener.CloseAppActionListener;
import cn.hpt.ui.listener.PanelStatusListener;
import cn.hpt.ui.listener.ShowInfoActionListener;
import cn.hpt.ui.listener.UserActinListener;
import cn.hpt.ui.toolbar.BillCancelAction;
import cn.hpt.ui.toolbar.BillSearchAction;
import cn.hpt.ui.toolbar.MedicineAction;
import cn.hpt.ui.toolbar.MedicineCtAction;
import cn.hpt.ui.toolbar.OutpatientAction;
import cn.hpt.ui.toolbar.StatAction;
import cn.hpt.util.PropertiesLoader;

@Component
public class MainFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = -1428298345760966376L;
    public JMenuBar mainMenuBar;
    public JMenuItem medicineSearcheMenuItem;//药品入库查询
    public JMenuItem infoMenuItem;//信息
    public JMenuItem quitMenuItem;
    public JMenuItem changeloginMenuItem;
    public JMenuItem userMenuItem;
    public JMenuItem configMenuItem;
    public JMenuItem caseTmMenuItem;
    public JMenuItem billMenuItem;
    public JMenuItem roleMenuItem;
    public JMenuItem purchaseMenuItem;
    public JMenuItem operatorMenuItem;
    public JMenuItem dataRstMenuItem;
    public JMenuItem dataBkMenuItem;
    public JMenuItem purchaseSearcheMenuItem;
    public JMenuItem purchaseStMenuItem;
    public JMenuItem purchCtMenuItem;
    public JMenu stockMenu;
    public JMenu systemMenu;
    public JMenu statMenu;
    public JMenuItem cancelMenuItem;
    public JMenuItem warehouseMenuItem;
    public JMenuItem medicineMenuItem;
    public JMenuItem medicineCtMenuItem;
    public JMenuItem priceMenuItem;
    public JMenuItem statMenuItem;
    public JMenu helpMenu;
    public JMenu quitMenu;
    public JMenu personMenu;
    public JMenu outpatientMenu;
    public JToolBar mainToolBar;
    public JScrollPane editPane;
    public JMenuItem outpatientSearchMenuItem;
    public JClosableTabbedPane actionTabbedPane;
    /**
     * **** 华丽的分割线 *****
     */
    @Autowired
    public OutpatientAction outpatientAction;
    @Autowired
    public BillSearchAction billSearchAction;
    @Autowired
    public BillCancelAction billCancelAction;
    @Autowired
    public MedicineCtAction medicineCtAction;
    @Autowired
    public MedicineAction medicineAction;
    @Autowired
    public StatAction statAction;
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
    public BillSearchPanel billSearchPanel;
    @Autowired
    public BillCancelPanel billCancelPanel;
    @Autowired
    public StatPanel statPanel;
    @Autowired
    public HptFont font;
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
                mainToolBar.add(billSearchAction);
                mainToolBar.add(billCancelAction);                
                mainToolBar.add(medicineCtAction);
                mainToolBar.add(medicineAction);
                mainToolBar.add(statAction);
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
                    outpatientMenu.setText(pl.getString("m.outpatient"));
                    {
                        priceMenuItem = new JMenuItem();
                        priceMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_P, KeyEvent.CTRL_MASK, true));
                        priceMenuItem.addActionListener(panelStatusListener);
                        outpatientMenu.add(priceMenuItem);
                        priceMenuItem.setText(pl.getString("m.price"));
                    }
                    {
                        outpatientMenu.add(new JSeparator());
                    }
                    {
                        cancelMenuItem = new JMenuItem();
                        outpatientMenu.add(cancelMenuItem);
                        cancelMenuItem.setText(pl.getString("m.cancel"));
                        cancelMenuItem.addActionListener(panelStatusListener);
                    }
                    {
                        outpatientMenu.add(new JSeparator());
                    }
                    {
                        outpatientSearchMenuItem = new JMenuItem();
                        outpatientSearchMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK, true));
                        outpatientSearchMenuItem.addActionListener(panelStatusListener);
                        outpatientMenu.add(outpatientSearchMenuItem);
                        outpatientSearchMenuItem.setText(pl.getString("m.outpatient.search"));
                    }
                }
                {
                    statMenu = new JMenu();
                    mainMenuBar.add(statMenu);
                    statMenu.setMnemonic(KeyEvent.VK_A);
                    statMenu.setText("统计分析(A)");
                    {
                        statMenuItem = new JMenuItem();
                        statMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK, true));
                        statMenuItem.setText("报表查询");
                        statMenuItem.addActionListener(panelStatusListener);
                        statMenu.add(statMenuItem);
                    }
                }
                {
                    stockMenu = new JMenu();
                    stockMenu.setMnemonic(KeyEvent.VK_S);
                    mainMenuBar.add(stockMenu);
                    stockMenu.setText(pl.getString("m.stock"));
                    {
                        medicineCtMenuItem = new JMenuItem();
                        medicineCtMenuItem.addActionListener(panelStatusListener);
                        medicineCtMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK, true));
                        stockMenu.add(medicineCtMenuItem);
                        medicineCtMenuItem.setText(pl.getString("m.medicine.ct"));
                    }
                    {
                        medicineMenuItem = new JMenuItem();
                        medicineMenuItem.addActionListener(panelStatusListener);
                        medicineMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK, true));
                        stockMenu.add(medicineMenuItem);
                        medicineMenuItem.setText(pl.getString("m.medicine"));
                    }
                    {
                        warehouseMenuItem = new JMenuItem();
                        stockMenu.add(warehouseMenuItem);
                        warehouseMenuItem.setText(pl.getString("m.warehouse"));
                    }
                    {
                        stockMenu.add(new JSeparator());
                    }
                    {
                        medicineSearcheMenuItem = new JMenuItem();
                        stockMenu.add(medicineSearcheMenuItem);
                        medicineSearcheMenuItem.setText(pl.getString("m.medicine.searche"));
                    }
                    {
                        stockMenu.add(new JSeparator());
                    }
                    {
                        purchCtMenuItem = new JMenuItem();
                        stockMenu.add(purchCtMenuItem);
                        purchCtMenuItem.setText(pl.getString("m.purchase.ct"));
                    }
                    {
                        purchaseMenuItem = new JMenuItem();
                        stockMenu.add(purchaseMenuItem);
                        purchaseMenuItem.setText(pl.getString("m.purchase"));
                    }
                    {
                        purchaseStMenuItem = new JMenuItem();
                        stockMenu.add(purchaseStMenuItem);
                        purchaseStMenuItem.setText(pl.getString("m.purchase.st"));
                    }
                    {
                        stockMenu.add(new JSeparator());
                    }
                    {
                        purchaseSearcheMenuItem = new JMenuItem();
                        stockMenu.add(purchaseSearcheMenuItem);
                        purchaseSearcheMenuItem.setText(pl.getString("m.purchase.searche"));
                        purchaseSearcheMenuItem.setBounds(65, 21, 84, 18);
                    }
                }
                {
                    systemMenu = new JMenu();
                    systemMenu.setMnemonic(KeyEvent.VK_M);
                    mainMenuBar.add(systemMenu);
                    systemMenu.setText(pl.getString("m.system"));
                    {
                        dataBkMenuItem = new JMenuItem();
                        systemMenu.add(dataBkMenuItem);
                        dataBkMenuItem.setText(pl.getString("m.data.bk"));
                    }
                    {
                        dataRstMenuItem = new JMenuItem();
                        systemMenu.add(dataRstMenuItem);
                        dataRstMenuItem.setText(pl.getString("m.data.rt"));
                    }
                    {
                        systemMenu.add(new JSeparator());
                    }
                    {
                        operatorMenuItem = new JMenuItem();
                        operatorMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_O, KeyEvent.CTRL_MASK, true));
                        operatorMenuItem.addActionListener(panelStatusListener);
                        systemMenu.add(operatorMenuItem);
                        operatorMenuItem.setText(pl.getString("m.operator"));
                    }
                    {
                        roleMenuItem = new JMenuItem();
                        roleMenuItem.addActionListener(panelStatusListener);
                        systemMenu.add(roleMenuItem);
                        roleMenuItem.setText(pl.getString("m.role"));
                    }
                    {
                        systemMenu.add(new JSeparator());
                    }
                    {
                        billMenuItem = new JMenuItem();
                        systemMenu.add(billMenuItem);
                        billMenuItem.setText(pl.getString("m.bill"));
                    }
                    {
                        caseTmMenuItem = new JMenuItem();
                        systemMenu.add(caseTmMenuItem);
                        caseTmMenuItem.setText(pl.getString("m.case"));
                    }
                    {
                        systemMenu.add(new JSeparator());
                    }
                    {
                        configMenuItem = new JMenuItem();
                        systemMenu.add(configMenuItem);
                        configMenuItem.setText(pl.getString("m.config"));
                    }
                }

                {
                    personMenu = new JMenu();
                    personMenu.setMnemonic(KeyEvent.VK_R);
                    mainMenuBar.add(personMenu);
                    personMenu.setText(pl.getString("m.person"));
                    {
                        userMenuItem = new JMenuItem();
                        userMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_M, KeyEvent.CTRL_MASK, true));
                        userMenuItem.addActionListener(userActinListener);
                        personMenu.add(userMenuItem);
                        userMenuItem.setText(pl.getString("m.user"));
                    }
                }
                {
                    quitMenu = new JMenu();
                    quitMenu.setMnemonic(KeyEvent.VK_Q);
                    mainMenuBar.add(quitMenu);
                    quitMenu.setText(pl.getString("m.quit"));
                    {
                        changeloginMenuItem = new JMenuItem();
                        changeloginMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                                KeyEvent.CTRL_MASK, true));
                        quitMenu.add(changeloginMenuItem);
                        changeloginMenuItem.addActionListener(changeLoginActionListener);
                        changeloginMenuItem.setText(pl.getString("m.change.login"));
                        changeloginMenuItem.setBounds(-53, 37, 72, 18);
                    }
                    {
                        quitMenu.add(new JSeparator());
                    }
                    {
                        quitMenuItem = new JMenuItem();
                        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_Q, KeyEvent.CTRL_MASK, true));
                        quitMenuItem.addActionListener(closeAppActionListener);
                        quitMenu.add(quitMenuItem);
                        quitMenuItem.setText(pl.getString("m.i.quit"));
                    }
                }
                {
                    helpMenu = new JMenu();
                    helpMenu.setMnemonic(KeyEvent.VK_H);
                    mainMenuBar.add(helpMenu);
                    helpMenu.setText(pl.getString("m.help"));
                    {
                        infoMenuItem = new JMenuItem();
                        infoMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                                KeyEvent.VK_F1, 0));
                        infoMenuItem.addActionListener(showInfoActionListener);
                        helpMenu.add(infoMenuItem);
                        infoMenuItem.setText(pl.getString("m.info"));
                    }
                }
                /* tabbedPane */
                {
                    actionTabbedPane = new JClosableTabbedPane();
                    getContentPane().add(actionTabbedPane, BorderLayout.CENTER);
                }
            }
            setIconImage(new ImageIcon(MainFrame.class.getResource(pl.getString("main.frame.ico"))).getImage());
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
        }
        /* init end. */
    }
}
