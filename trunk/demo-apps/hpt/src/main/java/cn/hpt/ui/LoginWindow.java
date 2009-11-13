package cn.hpt.ui;

import cn.hpt.model.Operator;
import cn.hpt.ui.extend.HptInitData;
import cn.hpt.ui.listener.CloseAppActionListener;
import cn.hpt.ui.listener.LoginActionListener;
import cn.hpt.util.PropertiesLoader;
import java.awt.event.KeyEvent;
import org.koala.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component(LoginWindow.BEAN_NAME)
public class LoginWindow extends javax.swing.JFrame {

    public static final String BEAN_NAME = "loginWindow";
    @Autowired
    private IDao baseDao;
    @Autowired
    private CloseAppActionListener closeAppActionListener;
    @Autowired
    private LoginActionListener loginActionListener;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private HptInitData hptInitData;
    private JPanel loginPanel = new JPanel();
    private JPanel imagePanel = new JPanel();
    private ImageIcon imageIcon;
    private ImageIcon frameIco;
    private JPanel namePanel = new JPanel();
    private JLabel messageLabel = new JLabel();
    private JPanel messagePanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JButton closeButton = new JButton();
    private JPasswordField passwordTextField = new JPasswordField();
    private JLabel passwordLabel = new JLabel();
    private JButton loginButton = new JButton();
    private JLabel nameLabel = new JLabel();
    //#############33
    private Operator operator;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public JComboBox getNameComboBox() {
        return nameComboBox;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }
    private JComboBox nameComboBox;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                LoginWindow inst = new LoginWindow(true);
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    /* spring constructor */
    public LoginWindow() {
        super();
    }

    public LoginWindow(boolean flag) {
        super();
        initGUI();
    }

    @PostConstruct
    public void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setLayout(thisLayout);
            {
                imageIcon = new ImageIcon(LoginWindow.class.getResource(pl.getString("login.left.ico")));
                frameIco = new ImageIcon(LoginWindow.class.getResource(pl.getString("login.frame.ico")));
                BorderLayout imagePanelLayout = new BorderLayout();
                imagePanel.setLayout(imagePanelLayout);
                imagePanel.add(new JLabel(imageIcon), BorderLayout.CENTER);
                getContentPane().add(loginPanel, BorderLayout.CENTER);
                getContentPane().add(imagePanel, BorderLayout.WEST);
                BoxLayout loginPanelLayout = new BoxLayout(loginPanel,
                        javax.swing.BoxLayout.Y_AXIS);

                loginPanel.setLayout(loginPanelLayout);
                {
                    FlowLayout namePanelLayout = new FlowLayout();
                    namePanelLayout.setAlignment(FlowLayout.RIGHT);
                    namePanelLayout.setAlignOnBaseline(true);
                    namePanel.setLayout(namePanelLayout);
                    loginPanel.add(namePanel);
                    namePanel.setPreferredSize(new java.awt.Dimension(216, 42));
                    {
                        namePanel.add(nameLabel);
                        nameLabel.setText(pl.getString("name"));
                    }
                    {
                        Vector<String> names = new Vector<String>();
                        // init the operator
                        List<Operator> items = baseDao.findAll(Operator.class);
                        if (items == null || items.size() < 1) {
                            hptInitData.init();
                        }
                        items = baseDao.findAll(Operator.class);
                        for (Operator op : items) {
                            names.add(op.getLoginname());
                        }
                        nameComboBox = new JComboBox(names);
                        nameComboBox.setPreferredSize(new Dimension(80, 20));

                        namePanel.add(nameComboBox);
                    }
                    {
                        namePanel.add(closeButton);
                        closeButton.setText(pl.getString("close"));
                        closeButton.addActionListener(closeAppActionListener);
                        closeButton.addKeyListener(closeAppActionListener);
                    }
                }
                {
                    FlowLayout passwordPanelLayout = new FlowLayout();
                    passwordPanelLayout.setAlignment(FlowLayout.RIGHT);
                    passwordPanelLayout.setAlignOnBaseline(true);
                    passwordPanel.setLayout(passwordPanelLayout);
                    loginPanel.add(passwordPanel);
                    {
                        passwordPanel.add(passwordLabel);
                        passwordLabel.setText(pl.getString("password") + pl.getString("colon"));
                    }
                    {
                        passwordTextField.setPreferredSize(new Dimension(80, 20));
                        passwordTextField.addKeyListener(loginActionListener);
                        passwordPanel.add(passwordTextField);
                    }
                    {

                        passwordPanel.add(loginButton);
                        loginButton.addActionListener(loginActionListener);
                        loginButton.addKeyListener(loginActionListener);
                        loginButton.setText(pl.getString("login"));
                    }
                }
            }
            {
                getContentPane().add(messagePanel, BorderLayout.SOUTH);
                {
                    messageLabel.setForeground(new Color(118, 61, 194));
                    messagePanel.add(messageLabel);
                    messageLabel.setText(pl.getString("login.label.msg"));
                }
            }
            passwordTextField.grabFocus();
            setIconImage(frameIco.getImage());
            focusTravel();
            setResizable(false);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            pack();            
//            setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void focusTravel() {
        List<java.awt.Component> list = new ArrayList<java.awt.Component>();
        list.add(nameComboBox);
        list.add(passwordTextField);
        list.add(loginButton);
        list.add(closeButton);
        final List<java.awt.Component> comList = list;
        FocusTraversalPolicy policy = new FocusTraversalPolicy() {

            public java.awt.Component getFirstComponent(Container focusCycleRoot) {
                return (java.awt.Component) comList.get(0);
            }

            public java.awt.Component getLastComponent(Container focusCycleRoot) {
                return (java.awt.Component) comList.get(comList.size() - 1);
            }

            public java.awt.Component getComponentAfter(
                    Container focusCycleRoot, java.awt.Component aComponent) {
                int index = comList.indexOf(aComponent);

                return (java.awt.Component) comList.get((index + 1)
                        % comList.size());
            }

            public java.awt.Component getComponentBefore(
                    Container focusCycleRoot, java.awt.Component aComponent) {
                int index = comList.indexOf(aComponent);
                return (java.awt.Component) comList.get((index - 1 + comList.size())
                        % comList.size());
            }

            public java.awt.Component getDefaultComponent(
                    Container focusCycleRoot) {
                return (java.awt.Component) comList.get(0);
            }
        };
        setFocusTraversalPolicy(policy);
    }
}
