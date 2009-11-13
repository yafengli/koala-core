package cn.hpt.ui.view;

import cn.hpt.model.Bill;
import cn.hpt.model.BillRecord;
import cn.hpt.model.Medicine;
import cn.hpt.ui.LoginWindow;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.model.ItemsListModel;
import cn.hpt.ui.model.PriceIITabelModel;
import cn.hpt.util.DateUtil;
import cn.hpt.util.HelperUtil;
import cn.hpt.util.PropertiesLoader;
import cn.hpt.util.WindowUtil;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.koala.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.DefaultListModel;

/**
 *
 * @author Administrator
 */
@Component
public class PriceIIDialog extends javax.swing.JDialog {

    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private LoginWindow loginWindow;
    @Autowired
    private IDao baseDao;
    @Autowired
    private PropertiesLoader propertiesLoader;
    @Autowired
    private PriceIITabelModel tabelModel;
    @Autowired
    private ItemsListModel itemsListModel;
    //
    private Bill bill;

    public PriceIIDialog() {
        super();
        initComponents();
    }

    /** Creates new form PriceIIDialog */
    public PriceIIDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initSettings();
        initData();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoPanel = new javax.swing.JPanel();
        operatorLabel = new javax.swing.JLabel();
        operatorField = new javax.swing.JTextField();
        itemNameLabel = new javax.swing.JLabel();
        itemNameField = new javax.swing.JTextField();
        itemPriceLabel = new javax.swing.JLabel();
        itemPriceField = new javax.swing.JTextField();
        itemSizeField = new javax.swing.JTextField();
        itemSizeLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        headerPanel = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        idNumLabel = new javax.swing.JLabel();
        idNumField = new javax.swing.JTextField();
        headerTitleLabel = new javax.swing.JLabel();
        idDateLabel = new javax.swing.JLabel();
        idDateField = new javax.swing.JTextField();
        headerSeparator = new javax.swing.JSeparator();
        listPanel = new javax.swing.JScrollPane();
        itemsList = new javax.swing.JList();
        itemsPanel = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        toolPanel = new javax.swing.JPanel();
        cancelTool = new javax.swing.JButton();
        printTool = new javax.swing.JButton();
        oughtaccLabel = new javax.swing.JLabel();
        discountaccLabel = new javax.swing.JLabel();
        realaccLabel = new javax.swing.JLabel();
        payLabel = new javax.swing.JLabel();
        oughtaccField = new javax.swing.JTextField();
        discountaccField = new javax.swing.JTextField();
        realaccField = new javax.swing.JTextField();
        payField = new javax.swing.JTextField();
        changeLabel = new javax.swing.JLabel();
        changeField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dialogClose(evt);
            }
        });

        infoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        operatorLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        operatorLabel.setText("收费员：");

        operatorField.setColumns(10);
        operatorField.setEditable(false);
        operatorField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        itemNameLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        itemNameLabel.setText("项目名称：");

        itemNameField.setColumns(10);
        itemNameField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        itemPriceLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        itemPriceLabel.setText("项目单价：");

        itemPriceField.setColumns(10);
        itemPriceField.setEditable(false);
        itemPriceField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        itemSizeField.setColumns(10);
        itemSizeField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        itemSizeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        itemSizeLabel.setText("项目次数：");

        addButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        addButton.setText("增加");

        removeButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        removeButton.setText("删除");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemNameLabel)
                    .addComponent(itemSizeLabel))
                .addGap(4, 4, 4)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itemSizeField)
                    .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton)
                    .addComponent(removeButton))
                .addGap(18, 18, 18)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operatorLabel)
                    .addComponent(itemPriceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itemPriceField)
                    .addComponent(operatorField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(148, 148, 148))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(infoPanelLayout.createSequentialGroup()
                            .addComponent(operatorLabel)
                            .addGap(14, 14, 14)
                            .addComponent(itemPriceLabel))
                        .addGroup(infoPanelLayout.createSequentialGroup()
                            .addComponent(operatorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(itemPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addButton)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(removeButton)))
                        .addGroup(infoPanelLayout.createSequentialGroup()
                            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(itemNameLabel)
                                .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(itemSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(itemSizeLabel)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        headerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        userNameLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        userNameLabel.setText("患者姓名：");

        userNameField.setColumns(6);
        userNameField.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        idNumLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        idNumLabel.setText("收费单号：");

        idNumField.setColumns(10);
        idNumField.setEditable(false);
        idNumField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10)); // NOI18N

        headerTitleLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        headerTitleLabel.setText("门（急）诊划价、收费专用单据");

        idDateLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        idDateLabel.setText("收费日期：");

        idDateField.setColumns(10);
        idDateField.setEditable(false);
        idDateField.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        idDateField.setText("200911050607-001");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(userNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(idNumLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(idDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addComponent(headerTitleLabel)
                .addGap(226, 226, 226))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idDateLabel)
                    .addComponent(idDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idNumLabel)
                    .addComponent(idNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameLabel)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        itemsList.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        itemsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listPanel.setViewportView(itemsList);

        itemsTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        itemsPanel.setViewportView(itemsTable);

        toolPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cancelTool.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        cancelTool.setText("取消收费");
        cancelTool.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        printTool.setFont(new java.awt.Font("Microsoft YaHei", 0, 12));
        printTool.setText("收费打印");
        printTool.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        oughtaccLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));
        oughtaccLabel.setText("应收金额：");

        discountaccLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));
        discountaccLabel.setText("优惠金额：");

        realaccLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));
        realaccLabel.setText("实收金额：");

        payLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));
        payLabel.setText("支付金额：");

        oughtaccField.setColumns(6);
        oughtaccField.setEditable(false);
        oughtaccField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        discountaccField.setColumns(6);
        discountaccField.setEditable(false);
        discountaccField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10)); // NOI18N

        realaccField.setColumns(6);
        realaccField.setEditable(false);
        realaccField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        payField.setColumns(6);
        payField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        changeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));
        changeLabel.setText("找零：");

        changeField.setColumns(6);
        changeField.setEditable(false);
        changeField.setFont(new java.awt.Font("Microsoft YaHei", 0, 10));

        javax.swing.GroupLayout toolPanelLayout = new javax.swing.GroupLayout(toolPanel);
        toolPanel.setLayout(toolPanelLayout);
        toolPanelLayout.setHorizontalGroup(
            toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, toolPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(toolPanelLayout.createSequentialGroup()
                        .addComponent(oughtaccLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oughtaccField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(discountaccLabel)
                        .addGap(4, 4, 4)
                        .addComponent(discountaccField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(realaccLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(realaccField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(payLabel)
                        .addGap(12, 12, 12)
                        .addComponent(payField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cancelTool, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(toolPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeLabel)
                        .addGap(18, 18, 18)
                        .addComponent(changeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, toolPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(printTool, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        toolPanelLayout.setVerticalGroup(
            toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolPanelLayout.createSequentialGroup()
                .addGroup(toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oughtaccLabel)
                    .addComponent(oughtaccField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discountaccLabel)
                    .addComponent(discountaccField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(realaccLabel)
                    .addComponent(realaccField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payLabel)
                    .addComponent(payField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeLabel)
                    .addComponent(changeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printTool, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(cancelTool, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(toolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                    .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dialogClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogClose
        JDialog dia = (JDialog) evt.getComponent();
        dia.setVisible(false);
        //TODO
        //clean somethings
    }//GEN-LAST:event_dialogClose

    /**
     *
     */
    @PostConstruct
    public void initSettings() {
        this.setModal(true);
        this.pack();
        this.setResizable(false);
        setLocationRelativeTo(mainFrame != null ? mainFrame : new JFrame());
        setLocation(WindowUtil.center(this));
    }

    /**
     *
     */
    @PostConstruct
    public void initEvent() {
        itemNameField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                String text = itemNameField.getText();
                int length = text.length();
                if (length >= 1) {
                    String actionChar = text.substring(length - 1, length);
                    //remove the action char in [0-9]
                    if (HelperUtil.isMatch(actionChar, "[0-9]")) {
                        itemNameField.setText(text.substring(0, length - 1));
                        int index = Integer.valueOf(actionChar);
                        if (index >= 0 && index < itemsList.getModel().getSize()) {
                            ItemsListModel ilModel = (ItemsListModel) itemsList.getModel();
                            Medicine item = (Medicine) ilModel.getItems().get(index);
                            itemsList.setSelectedIndex(index);
                            itemSizeField.grabFocus();
                            itemPriceField.setText(String.valueOf(item.getPrice()));
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyTyped(e);
            }
        });
        itemSizeField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    itemNameField.grabFocus();
                    int index = itemsList.getSelectedIndex();
                    if (index >= 0) {
                        List<Medicine> items = ((ItemsListModel) itemsList.getModel()).getItems();
                        createNewBill(items.get(index));
                    } else {
                    }
                } else {
                    String str = itemSizeField.getText();
                    if (str.length() >= 1) {
                        if (!HelperUtil.isMatch(str, "[0-9]*")) {
                            getToolkit().beep();
                            e.consume();
                            JOptionPane.showMessageDialog(null, propertiesLoader.getString("price.dialog.only.number"));
                            str = str.substring(0, str.length() - 1);
                            itemSizeField.setText(str);
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Release");
                keyTyped(e);
            }
        });
        itemNameField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                action(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                action(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                action(e);
            }

            private void action(DocumentEvent documentEvent) {
                DocumentEvent.EventType type = documentEvent.getType();
                Document source = documentEvent.getDocument();
                int length = source.getLength();
                System.out.printf("[type=%s]", type.toString());
                if (type.equals(DocumentEvent.EventType.INSERT)) {
                    if (length >= 1) {
                        try {
                            String text = source.getText(0, length);
                            String actionChar = text.substring(length - 1, length);
                            if (!HelperUtil.isMatch(actionChar, "[0-9]")) {
                                //更新收费项目选择
                                List<Medicine> items = baseDao.find("medicine.find.like.byshortcut",
                                        new String[]{"mshortcut"}, new Object[]{text + "%"});
                                itemsListModel.removeAll();
                                itemsListModel.addAll(items.toArray());
                            }
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                            System.out.println("Contents: Unknown");
                        }
                    }
                } else {
                    System.out.println("Remove");
                }
            }
        });
    }

    /**
     *
     */
    @PostConstruct
    public void initData() {
        String initvalu = "0.00";
        Date date = new Date();
        changeField.setText(initvalu);
        discountaccField.setText(initvalu);
        idDateField.setText(DateUtil.format(date, DateUtil.yyyy_MM_dd_HH_mm));
        idNumField.setText(String.format("%s-%s", DateUtil.format(date, DateUtil.yyyyMMddHHmm), HelperUtil.createRandomString(3)));
        itemSizeField.setText("1");
        operatorField.setText(loginWindow != null && loginWindow.getOperator() != null ? loginWindow.getOperator().getLoginname() : "");
        oughtaccField.setText(initvalu);
        payField.setText(initvalu);
        realaccField.setText(initvalu);

        //TODO
        itemsTable.setModel(tabelModel);
        itemsList.setModel(itemsListModel);
    }

    /**
     * 增加收费项目
     */
    private void createNewBill(Medicine medicine) {
        /* create new bill */
        if (bill == null) {
            bill = new Bill();
            bill.setRcreatedate(new Timestamp(System.currentTimeMillis()));
            bill.setIdnumber(idNumField.getText());
            baseDao.save(bill);
        }
        BillRecord br = new BillRecord();
        br.setBill(bill);
        br.setMedicine(medicine);
        br.setBnumber(Long.valueOf(itemSizeField.getText()));
        tabelModel.getItems().add(br);
        itemsTable.revalidate();
    }

    /**
     *
     */
    private void priceChange() {
        float oughtaccn = 0;
        for (BillRecord item : tabelModel.getItems()) {
            oughtaccn += item.getBnumber() * item.getMedicine().getPrice();
        }
        float realaccn = oughtaccn - Float.valueOf(discountaccField.getText());
        float changen = Float.valueOf(payField.getText()) - realaccn;
        oughtaccField.setText(HelperUtil.format(oughtaccn, "0.##"));
        realaccField.setText(HelperUtil.format(realaccn, "0.##"));
        changeField.setText(HelperUtil.format(changen, "0.##"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");

        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PriceIIDialog dialog = new PriceIIDialog(new JFrame(), true);

                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelTool;
    private javax.swing.JTextField changeField;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JTextField discountaccField;
    private javax.swing.JLabel discountaccLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JSeparator headerSeparator;
    private javax.swing.JLabel headerTitleLabel;
    private javax.swing.JTextField idDateField;
    private javax.swing.JLabel idDateLabel;
    private javax.swing.JTextField idNumField;
    private javax.swing.JLabel idNumLabel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JLabel itemNameLabel;
    private javax.swing.JTextField itemPriceField;
    private javax.swing.JLabel itemPriceLabel;
    private javax.swing.JTextField itemSizeField;
    private javax.swing.JLabel itemSizeLabel;
    private javax.swing.JList itemsList;
    private javax.swing.JScrollPane itemsPanel;
    private javax.swing.JTable itemsTable;
    private javax.swing.JScrollPane listPanel;
    private javax.swing.JTextField operatorField;
    private javax.swing.JLabel operatorLabel;
    private javax.swing.JTextField oughtaccField;
    private javax.swing.JLabel oughtaccLabel;
    private javax.swing.JTextField payField;
    private javax.swing.JLabel payLabel;
    private javax.swing.JButton printTool;
    private javax.swing.JTextField realaccField;
    private javax.swing.JLabel realaccLabel;
    private javax.swing.JButton removeButton;
    private javax.swing.JPanel toolPanel;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
