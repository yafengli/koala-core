package cn.hpt.ui.component;

import cn.hpt.model.Bill;
import cn.hpt.model.BillRecord;
import cn.hpt.ui.LoginWindow;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.extend.ObservingTextField;
import cn.hpt.ui.model.BillTabelModel;
import cn.hpt.ui.model.SelectColorTableCellRenderer;
import cn.hpt.ui.model.TableHeaderRenderer;
import cn.hpt.ui.view.BillRecordDialog;
import cn.hpt.util.DateUtil;
import cn.hpt.util.PropertiesLoader;
import com.qt.datapicker.DatePicker;
import org.koala.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

@Service
public class BillCancelPanel extends JPanel {

    private static final long serialVersionUID = 4591912718436452499L;
    public static final Logger logger = LoggerFactory.getLogger(BillCancelPanel.class);
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private BillTabelModel tabelModel;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private SelectColorTableCellRenderer cellRenderer;
    @Autowired
    private IDao baseDao;
    @Autowired
    private HptFont font;
    @Autowired
    private TableHeaderRenderer tableHeaderRenderer;
    @Autowired
    private LoginWindow loginWindow;
    @Autowired
    private BillRecordDialog billRecordDialog;
    private JScrollPane contentbp = new JScrollPane();
    private JPanel buttonbp = new JPanel();
    private JTable hptTable = new JTable();
    private JButton view = new JButton("查看");
    private JButton delete = new JButton("删除");
    private JButton print = new JButton("打印");
    private JPanel searchp = new JPanel();
    private JLabel startLabel = new JLabel("开始时间");
    private ObservingTextField startField = new ObservingTextField();
    private JButton startButton = new JButton();
    private JLabel endLabel = new JLabel("开始时间");
    private JButton endButton = new JButton();
    private ObservingTextField endField = new ObservingTextField();
    private JButton search = new JButton("查询");

    @PostConstruct
    public void init() {
        setLayout(new BorderLayout());
        add(searchp, BorderLayout.NORTH);
        add(contentbp, BorderLayout.CENTER);
        add(buttonbp, BorderLayout.SOUTH);
        {

            hptTable.setModel(tabelModel);
            hptTable.setRowSorter(
                    new TableRowSorter<BillTabelModel>(tabelModel));
            int columnIndex = hptTable.getColumnModel().getColumnCount();
            for (int i = 0; i < columnIndex; i++) {
                TableColumn tc = hptTable.getColumnModel().getColumn(i);
                tc.setHeaderRenderer(tableHeaderRenderer);
                tc.setCellRenderer(cellRenderer);
            }
            contentbp.setViewportView(hptTable);
            {
                hptTable.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() >= 2) {
                            int viewid = hptTable.getSelectedRow();
                            if (viewid >= 0 & viewid < tabelModel.getItem().size()) {
                                Bill bill = tabelModel.getItem().get(viewid);
                                billRecordDialog.view(bill);
                            }
                        }
                    }
                });
            }
        }
        {
            buttonbp.setLayout(new FlowLayout());
            buttonbp.add(view);
            buttonbp.add(delete);
            buttonbp.add(print);
        }
        {
            {
                startField.setEditable(false);
                startField.setColumns(20);
                endField.setEditable(false);
                endField.setColumns(20);
                ImageIcon imageIcon = new ImageIcon(BillCancelPanel.class.getResource("/logo/datePicker.gif"));
                startButton.setIcon(imageIcon);
                endButton.setIcon(imageIcon);
                /* set size */
                startButton.setMargin(new Insets(0, 0, 0, 0));
                endButton.setMargin(new Insets(0, 0, 0, 0));
                startButton.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
                endButton.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            }
            {
                startButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // instantiate the DatePicker
                        DatePicker dp = new DatePicker(startField, Locale.CHINESE);
                        // previously selected date
                        Date selectedDate = dp.parseDate(startField.getText());
                        dp.setSelectedDate(selectedDate);
                        dp.start(startField);
                    }
                });
                endButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DatePicker dp = new DatePicker(endField, Locale.CHINESE);
                        // previously selected date
                        Date selectedDate = dp.parseDate(endField.getText());
                        dp.setSelectedDate(selectedDate);
                        dp.start(endField);
                    }
                });
            }
            searchp.add(startLabel);
            searchp.add(startField);
            searchp.add(startButton);
            searchp.add(endLabel);
            searchp.add(endField);
            searchp.add(endButton);
            searchp.add(search);

            {
                search.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try {
                            if (startField.getText() == null || endField.getText() == null
                                    || startField.getText().length() < 1 || endField.getText().length() < 1) {

                                JOptionPane.showMessageDialog(mainFrame, "必须选择开始时间与结束时间！");
                            } else {
                                Timestamp st = new Timestamp(DateUtil.parse(
                                        startField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                                Timestamp et = new Timestamp(DateUtil.parse(
                                        endField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                                List<Bill> lb = baseDao.find(
                                        "bill.find.by.time.operator", new String[]{
                                            "stime", "etime", "operator"}, new Object[]{
                                            st, et, loginWindow.getOperator()});
                                System.out.printf("[%s,%s][%s,%s,%s]\n", startField.getText(), endField.getText(), st, et, lb.size());
                                tabelModel.setItem(lb);
                                hptTable.revalidate();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        }
        {
            view.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int viewid = hptTable.getSelectedRow();
                    if (viewid >= 0 & viewid < tabelModel.getItem().size()) {
                        Bill bill = tabelModel.getItem().get(viewid);
                        billRecordDialog.view(bill);
                    }
                }
            });
        }
        {
            delete.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectRow = hptTable.getSelectedRow();
                    if (hptTable.isEditing()) {
                        return;
                    } else if (selectRow >= 0
                            && selectRow <= tabelModel.getItem().size()) {
                        int option = JOptionPane.showConfirmDialog(mainFrame,
                                pl.getString("del.operator.msg"), null,
                                JOptionPane.YES_NO_OPTION);
                        switch (option) {
                            case JOptionPane.YES_OPTION:
                                Bill item = tabelModel.getItem().get(selectRow);
                                List<BillRecord> lbr = baseDao.find("billrecord.find.by.bill",
                                        new String[]{"bill"},
                                        new Object[]{item});
                                for (BillRecord bd : lbr) {
                                    baseDao.remove(bd);
                                }
                                baseDao.remove(item);
                                tabelModel.getItem().remove(selectRow);
                                hptTable.revalidate();
                                break;
                            case JOptionPane.NO_OPTION:
                                break;
                        }
                    }
                }
            });
        }
        {
            print.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean flag = false;
                    try {
                        int op = JOptionPane.showConfirmDialog(null, "您确认打印吗？",
                                "打印", JOptionPane.YES_NO_OPTION);
                        switch (op) {
                            case 0:
                                flag = hptTable.print();
                                if (!flag) {
                                    JOptionPane.showMessageDialog(null,
                                            "打印失败，请检查打印机是否正常工作！");
                                }
                                break;
                            case 1:
                                break;
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "请检查打印机，可能没有正常工作！");
                        ex.printStackTrace();
                    }
                }
            });
        }
    }
}
