package cn.hpt.ui;


import cn.hpt.model.Operator;
import cn.hpt.ui.extend.HptInitData;
import cn.hpt.ui.listener.CloseAppActionListener;
import cn.hpt.ui.listener.LoginActionListener;
import cn.hpt.util.PropertiesLoader;
import org.koala.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component(LoginWindow.BEAN_NAME)
public class LoginWindow extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 8291320816086041316L;

    {
        // Set Look & Feel
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    private JPanel loginPanel;
    private JPanel imagePanel;
    private ImageIcon imageIcon;
    private ImageIcon frameIco;
    private JPanel namePanel;
    private JLabel messageLabel;
    private JPanel messagePanel;
    private JComboBox nameComboBox;
    private JPanel passwordPanel;
    private JButton closeButton;
    private JPasswordField passwordTextField;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JLabel nameLabel;

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public JPanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(JPanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ImageIcon getFrameIco() {
        return frameIco;
    }

    public void setFrameIco(ImageIcon frameIco) {
        this.frameIco = frameIco;
    }

    public JPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(JPanel namePanel) {
        this.namePanel = namePanel;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(JLabel messageLabel) {
        this.messageLabel = messageLabel;
    }

    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public void setMessagePanel(JPanel messagePanel) {
        this.messagePanel = messagePanel;
    }

    public JComboBox getNameComboBox() {
        return nameComboBox;
    }

    public void setNameComboBox(JComboBox nameComboBox) {
        this.nameComboBox = nameComboBox;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public void setPasswordPanel(JPanel passwordPanel) {
        this.passwordPanel = passwordPanel;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(JButton closeButton) {
        this.closeButton = closeButton;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JPasswordField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

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
                loginPanel = new JPanel();
                imagePanel = new JPanel();
                imageIcon = new ImageIcon(LoginWindow.class.getResource(pl
                        .getString("login.left.ico")));
                frameIco = new ImageIcon(LoginWindow.class.getResource(pl
                        .getString("login.frame.ico")));
                BorderLayout imagePanelLayout = new BorderLayout();
                imagePanel.setLayout(imagePanelLayout);
                imagePanel.add(new JLabel(imageIcon), BorderLayout.CENTER);
                passwordPanel = new JPanel();
                passwordLabel = new JLabel();
                loginButton = new JButton();
                getContentPane().add(loginPanel, BorderLayout.CENTER);
                getContentPane().add(imagePanel, BorderLayout.WEST);
                BoxLayout loginPanelLayout = new BoxLayout(loginPanel,
                        javax.swing.BoxLayout.Y_AXIS);

                loginPanel.setLayout(loginPanelLayout);
                {
                    namePanel = new JPanel();
                    FlowLayout namePanelLayout = new FlowLayout();
                    namePanelLayout.setAlignment(FlowLayout.RIGHT);
                    namePanelLayout.setAlignOnBaseline(true);
                    namePanel.setLayout(namePanelLayout);
                    loginPanel.add(namePanel);
                    namePanel.setPreferredSize(new java.awt.Dimension(216, 42));
                    {
                        nameLabel = new JLabel();
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
                        closeButton = new JButton();
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
                        passwordTextField = new JPasswordField();
                        passwordTextField
                                .setPreferredSize(new Dimension(80, 20));
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
                messagePanel = new JPanel();
                getContentPane().add(messagePanel, BorderLayout.SOUTH);
                {
                    messageLabel = new JLabel();
                    messageLabel.setForeground(new Color(118, 61, 194));
                    messagePanel.add(messageLabel);
                    messageLabel.setText(pl.getString("login.label.msg"));
                }
            }
            setIconImage(frameIco.getImage());
            focusTravel();
            setResizable(false);
            setAlwaysOnTop(true);
            pack();

            // setAlwaysOnTop(true);
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
                return (java.awt.Component) comList.get((index - 1 + comList
                        .size())
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
