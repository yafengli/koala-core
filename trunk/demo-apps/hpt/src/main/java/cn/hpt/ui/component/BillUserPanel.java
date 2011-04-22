package cn.hpt.ui.component;

import cn.hpt.model.Bill;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.model.BillTabelModel;
import cn.hpt.ui.model.SelectColorTableCellRenderer;
import cn.hpt.ui.model.TableHeaderRenderer;
import cn.hpt.ui.view.BillRecordDialog;
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
import java.util.List;

@Service
public class BillUserPanel extends JPanel {

    private static final long serialVersionUID = 4591912718436452499L;
    public static final Logger logger = LoggerFactory.getLogger(BillUserPanel.class);
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private BillTabelModel tabelModel;
    @Autowired
    private SelectColorTableCellRenderer cellRenderer;
    @Autowired
    private IDao baseDao;
    @Autowired
    private HptFont font;
    @Autowired
    private TableHeaderRenderer tableHeaderRenderer;
    @Autowired
    private BillRecordDialog billRecordDialog;
    private JScrollPane contentbp = new JScrollPane();
    private JPanel buttonbp = new JPanel();
    private JTable hptTable = new JTable();
    private JButton view = new JButton("查看");
    private JButton print = new JButton("打印");
    private JPanel searchp = new JPanel();
    private JLabel userLabel = new JLabel("患者姓名：");
    private JTextField userField = new JTextField();
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
            buttonbp.add(print);
        }
        {
        }
        searchp.add(userLabel);
        searchp.add(userField);
        searchp.add(search);
        {
            search.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        if (userField.getText() == null || userField.getText().length() < 1) {
                            JOptionPane.showMessageDialog(mainFrame, "必须选择开始时间与结束时间！");
                        } else {
                            List<Bill> lb = baseDao.find(
                                    "bill.find.by.username", new String[]{
                                        "username"}, new Object[]{
                                        userField.getText()});
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
