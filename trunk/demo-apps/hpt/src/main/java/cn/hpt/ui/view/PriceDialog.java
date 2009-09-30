package cn.hpt.ui.view;

import cn.hpt.dao.IBillDao;
import cn.hpt.dao.IBillRecordDao;
import cn.hpt.model.Bill;
import cn.hpt.model.BillRecord;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.component.PricePanel;
import cn.hpt.ui.component.PrintPanel;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.util.PropertiesLoader;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;

@Service
public class PriceDialog extends javax.swing.JDialog {
    private static final long serialVersionUID = -4932293243415672088L;

    public JButton printButton;
    public JLabel priceLabel;
    public JPanel actionPanel;
    public PrintPanel printPanel;
    public JLabel itemnameLabel;
    public JPanel itemnamePanel;
    public JPanel itemPanel;
    public JLabel priceField;
    public JLabel operatorField;
    public JLabel operatorLabel;
    public JPanel pricePanel;
    public JTextField userField;
    public JButton addButton;
    public JLabel nameLabel;
    public JPanel namePanel;
    public JButton closeButton;
    public JTextField socialField;
    public JLabel medicalField;
    public JLabel medicalLabel;
    public JLabel socialLabel;
    public JLabel dateField;
    public JLabel dateLabel;
    public JLabel userLabel;
    public JPanel lablePanel;
    public JPanel contentPanel;
    private JButton rmButton;

    @Autowired
    private PricePanel panel;
    @Autowired
    private HptFont font;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private IBillDao billDao;
    @Autowired
    private IBillRecordDao billRecordDao;
    @Autowired
    private MainFrame mainFrame;
    private Bill bill;

    public PriceDialog(JFrame frame) {
        super(frame);
        initGUI();
    }

    public PriceDialog() {
        super();

    }

    @PostConstruct
    private void initGUI() {
        final JDialog dialog = this;
        {
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }
        try {
            BorderLayout frameLayout = new BorderLayout();
            getContentPane().setLayout(frameLayout);
            {
                printPanel = new PrintPanel();
                BorderLayout printPanelLayout = new BorderLayout();
                getContentPane().add(printPanel, BorderLayout.CENTER);
                printPanel.setLayout(printPanelLayout);
                {
                    contentPanel = new JPanel();
                    printPanel.add(contentPanel);
                    BorderLayout contentPanelLayout = new BorderLayout();
                    contentPanel.setLayout(contentPanelLayout);
                    {
                        lablePanel = new JPanel();
                        FormLayout lablePanelLayout = new FormLayout(
                                "max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)",
                                "max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
                        contentPanel.add(lablePanel, BorderLayout.NORTH);
                        lablePanel.setLayout(lablePanelLayout);
                        {
                            socialLabel = new JLabel();
                            lablePanel.add(socialLabel, new CellConstraints(
                                    "3, 2, 1, 1, default, default"));
                            socialLabel
                                    .setText("\u793e\u4fdd\u8d26\u53f7\uff1a");
                        }
                        {
                            socialField = new JTextField();
                            lablePanel.add(socialField, new CellConstraints(
                                    "4, 2, 1, 1, default, default"));
                            socialField.setColumns(30);
                            socialField
                                    .setPreferredSize(new java.awt.Dimension(
                                            30, 22));
                        }
                        {
                            medicalLabel = new JLabel();
                            lablePanel.add(medicalLabel, new CellConstraints(
                                    "1, 2, 1, 1, default, default"));
                            medicalLabel
                                    .setText("\u533b\u7597\u673a\u6784\uff1a");
                        }
                        {
                            medicalField = new JLabel();
                            medicalField.setText(pl
                                    .getString("pricedialog.medical.name"));
                            lablePanel.add(medicalField, new CellConstraints(
                                    "2, 2, 1, 1, default, default"));
                            medicalField
                                    .setPreferredSize(new java.awt.Dimension(
                                            30, 22));
                        }
                        {
                            userLabel = new JLabel();
                            lablePanel.add(userLabel, new CellConstraints(
                                    "1, 1, 1, 1, default, default"));
                            userLabel.setText("\u59d3\u540d\uff1a");
                        }
                        {
                            userField = new JTextField();
                            lablePanel.add(userField, new CellConstraints(
                                    "2, 1, 1, 1, default, default"));
                            userField.setColumns(20);
                            userField.setPreferredSize(new java.awt.Dimension(
                                    20, 22));
                        }
                        {
                            dateLabel = new JLabel();
                            lablePanel.add(dateLabel, new CellConstraints(
                                    "3, 1, 1, 1, default, default"));
                            dateLabel.setText("\u65e5\u671f\uff1a");
                        }
                        {
                            dateField = new JLabel();
                            dateField.setBackground(Color.WHITE);
                            dateField.setText(DateFormatUtils
                                    .format(System.currentTimeMillis(),
                                    "yyyy-MM-dd HH:mm:ss"));
                            lablePanel.add(dateField, new CellConstraints(
                                    "4, 1, 1, 1, default, default"));
                            dateField.setPreferredSize(new java.awt.Dimension(
                                    30, 22));
                        }
                    }
                    {
                        pricePanel = new JPanel();
                        contentPanel.add(pricePanel, BorderLayout.SOUTH);
                        {
                            operatorLabel = new JLabel();
                            pricePanel.add(operatorLabel);
                            operatorLabel.setText("\u6536\u8d39\u5458\uff1a");
                        }
                        {
                            operatorField = new JLabel();
                            operatorField.setBackground(Color.WHITE);
                            pricePanel.add(operatorField);
                        }
                        {
                            priceLabel = new JLabel();
                            pricePanel.add(priceLabel);
                            priceLabel
                                    .setText("\u5408\u8ba1\u8d39\u7528\uff1a");
                        }
                        {
                            priceField = new JLabel();
                            priceField.setBackground(Color.WHITE);
                            pricePanel.add(priceField);
                            priceField.setText("0");
                        }
                    }
                    {
                        itemPanel = new JPanel();
                        BorderLayout itemPanelLayout = new BorderLayout();
                        contentPanel.add(itemPanel, BorderLayout.CENTER);
                        itemPanel.setLayout(itemPanelLayout);
                        {
                            itemPanel.add(panel);
                        }
                        {
                            itemnamePanel = new JPanel();
                            FlowLayout itemnamePanelLayout = new FlowLayout();
                            itemnamePanelLayout.setVgap(16);
                            itemnamePanel.setLayout(itemnamePanelLayout);
                            itemPanel.add(itemnamePanel, BorderLayout.NORTH);
                            {
                                itemnameLabel = new JLabel();
                                itemnameLabel.setFont(font.PRICE_LABEL);
                                itemnamePanel.add(itemnameLabel);
                                itemnameLabel
                                        .setText("\u6536\u8d39\u9879\u76ee\u6e05\u5355");
                            }
                        }
                    }
                }
                {
                    namePanel = new JPanel();
                    FlowLayout namePanelLayout = new FlowLayout();
                    namePanelLayout.setVgap(20);
                    namePanel.setLayout(namePanelLayout);
                    printPanel.add(namePanel, BorderLayout.NORTH);
                    {
                        nameLabel = new JLabel();
                        nameLabel.setFont(font.PRICE_NAME);
                        namePanel.add(nameLabel);
                        nameLabel
                                .setText("\u95e8\uff08\u6025\uff09\u8bca\u4e13\u7528\u6536\u8d39\u5355\u636e");
                    }
                }
            }
            {
                actionPanel = new JPanel();
                getContentPane().add(actionPanel, BorderLayout.SOUTH);
                {
                    addButton = new JButton();
                    actionPanel.add(addButton);

                    addButton.setText("\u589e\u52a0");
                }
                {
                    rmButton = new JButton();

                    actionPanel.add(rmButton);
                    rmButton.setText("\u5220\u9664");
                }
                {
                    closeButton = new JButton();
                    actionPanel.add(closeButton);
                    closeButton.setText("\u53d6\u6d88");
                }
                {
                    printButton = new JButton();
                    actionPanel.add(printButton);
                    printButton.setText("\u6253\u5370");
                }
            }
            pack();
            setSize(Integer.valueOf(pl.getString("price.dialog.width")),
                    Integer.valueOf(pl.getString("price.dialog.height")));
            setResizable(false);
            {
                {
                    addButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            /* create new bill */
                            if (bill == null) {
                                bill = new Bill();
                                bill.setRcreatedate(new Timestamp(System
                                        .currentTimeMillis()));
                                billDao.save(bill);
                            }
                            BillRecord br = new BillRecord();
                            br.setBill(bill);
                            br.setMedicine(null);
                            br.setBnumber(1L);
                            panel.tabelModel.getItem().add(br);
                            panel.hptTable.revalidate();
                        }
                    });
                }
                {
                    printButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int option = JOptionPane.showConfirmDialog(null, pl
                                    .getString("price.dialog.print.msg"),
                                    printButton.getText(),
                                    JOptionPane.YES_NO_OPTION);
                            switch (option) {
                                case JOptionPane.YES_OPTION: {

                                    try {
                                        /*终止编辑状态*/
                                        if (panel.hptTable.isEditing()) {
                                            int row = panel.hptTable.getSelectedRow();
                                            int col = panel.hptTable.getSelectedColumn();
                                            panel.hptTable.getCellEditor(row, col).stopCellEditing();
                                        }
                                        /*
                                        PrinterJob job = PrinterJob.getPrinterJob();
                                        PageFormat format = job.pageDialog(job.defaultPage());
                                        job.setPrintable(printPanel, format);
                                        if (job.printDialog())
                                            job.print();
                                        */
                                        //TODO
                                        testImagePrint();

                                        bill.setUsername(userField.getText());
                                        bill.setResult(Float.parseFloat(priceField.getText()));
                                        billDao.update(bill);
                                        for (BillRecord br : panel.tabelModel.getItem()) {
                                            billRecordDao.save(br);
                                        }
                                        close();
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        JOptionPane
                                                .showMessageDialog(
                                                        dialog,
                                                        pl
                                                                .getString("price.dialo.print.err"),
                                                        dialog.getTitle(),
                                                        JOptionPane.CLOSED_OPTION);
                                    }
                                }
                                break;
                                default:
                                    billDao.remove(bill);
                                    break;
                            }
                        }
                    });
                }
                {
                    rmButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (panel.hptTable.isEditing()) {
                                return;
                            } else {
                                int selectRow = panel.hptTable.getSelectedRow();
                                if (selectRow >= 0
                                        && selectRow < panel.tabelModel.getItem()
                                        .size()) {
                                    panel.tabelModel.getItem().remove(selectRow);
                                }
                                panel.hptTable.revalidate();
                            }
                        }
                    });
                }
                {
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            close();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        setModal(true);
        setLocationRelativeTo(mainFrame);
        setVisible(true);
    }

    public void close() {
        bill = null;
        userField.setText("");
        socialField.setText("");
        priceField.setText("0");
        panel.tabelModel.getItem().clear();
        panel.hptTable.revalidate();
        this.setVisible(false);
    }

    /* test print image */
    private void testImagePrint() {
        BufferedImage image = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setFont(font.PRICE_LABEL);
        //打印患者姓名
        g2.drawString(userField.getText(), Float.parseFloat(pl.getString("print.user.x")), Float.parseFloat(pl.getString("print.user.y")));
        //打印日期
        g2.drawString(dateField.getText(), Float.parseFloat(pl.getString("print.date.x")), Float.parseFloat(pl.getString("print.date.y")));
        //打印收款员
        g2.drawString(operatorField.getText(), Float.parseFloat(pl.getString("print.operator.x")), Float.parseFloat(pl.getString("print.operator.y")));
        //打印费用
        g2.drawString(priceField.getText(), Float.parseFloat(pl.getString("print.price.x")), Float.parseFloat(pl.getString("print.price.y")));
        //打印药物清单
        float itemx = Float.parseFloat(pl.getString("print.medicine.x"));
        float itemy = Float.parseFloat(pl.getString("print.medicine.y"));
        java.util.List<BillRecord> lbr = panel.tabelModel.getItem();
        for (BillRecord item : lbr) {
            g2.drawString(String.format("[%s  %s  %s]", item.getMedicine() != null ? item.getMedicine().getMname() : "",
                    item.getMedicine() != null ? item.getMedicine().getPrice() : "", item.getBnumber()), itemx, itemy);
            itemy += g2.getFont().getSize() + 1;
        }
        try {
            ImageIO.write(image, "gif", new File("f:/tmp/hello.gif"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
