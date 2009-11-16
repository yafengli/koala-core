package cn.hpt.ui.component;

import cn.hpt.model.Bill;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.extend.ObservingTextField;
import cn.hpt.ui.model.BillTabelModel;
import cn.hpt.ui.model.SelectColorTableCellRenderer;
import cn.hpt.ui.view.BillRecordDialog;
import cn.hpt.util.DateUtil;
import cn.hpt.util.HelperUtil;
import cn.hpt.util.PropertiesLoader;
import com.qt.datapicker.DatePicker;
import java.io.File;
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
import javax.swing.filechooser.FileFilter;

@Service
public class StatPanel extends JPanel {

    private static final long serialVersionUID = 4591912718436452499L;
    public static final Logger logger = LoggerFactory.getLogger(StatPanel.class);
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
    private BillRecordDialog billRecordDialog;
    private JScrollPane contentbp = new JScrollPane();
    private JPanel buttonbp = new JPanel();
    private JTable hptTable = new JTable();
    private JButton bydoctor = new JButton("按医生统计");
    private JButton byitem = new JButton("按项目统计");
    private JButton byuser = new JButton("按患者统计");
    private JPanel searchp = new JPanel();
    private JLabel startLabel = new JLabel("开始时间");
    private ObservingTextField startField = new ObservingTextField();
    private JButton startButton = new JButton();
    private JLabel endLabel = new JLabel("开始时间");
    private JButton endButton = new JButton();
    private ObservingTextField endField = new ObservingTextField();
    private JButton search = new JButton("查询");
    private JFileChooser chooser = new JFileChooser();//导出文件
    private FileFilter xlsFilter = new XlsFilter();

    @PostConstruct
    public void init() {
        setLayout(new BorderLayout());
        add(searchp, BorderLayout.NORTH);
        add(contentbp, BorderLayout.CENTER);
        add(buttonbp, BorderLayout.SOUTH);
        {
            chooser.setFileFilter(xlsFilter);
        }
        {
            hptTable.setModel(tabelModel);
            hptTable.setRowSorter(new TableRowSorter<BillTabelModel>(tabelModel));
            int columnIndex = hptTable.getColumnModel().getColumnCount();
            for (int i = 0; i < columnIndex; i++) {
                TableColumn tc = hptTable.getColumnModel().getColumn(i);
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
            buttonbp.add(bydoctor);
            buttonbp.add(byitem);
            buttonbp.add(byuser);
        }
        {
            {
                startField.setEditable(false);
                startField.setColumns(20);
                endField.setEditable(false);
                endField.setColumns(20);
                ImageIcon imageIcon = new ImageIcon(StatPanel.class.getResource("/logo/datePicker.gif"));
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
                            Timestamp st = new Timestamp(DateUtil.parse(
                                    startField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                            Timestamp et = new Timestamp(DateUtil.parse(
                                    endField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                            List<Bill> lb = baseDao.find(
                                    "bill.find.by.time", new String[]{
                                        "stime", "etime"}, new Object[]{
                                        st, et});
                            tabelModel.setItem(lb);
                            hptTable.revalidate();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        }
        {
            bydoctor.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Timestamp st = new Timestamp(DateUtil.parse(
                                startField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                        Timestamp et = new Timestamp(DateUtil.parse(
                                endField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                        List<Object[]> items = baseDao.find("bill.find.group.operator.time", new String[]{
                                    "stime", "etime"}, new Object[]{
                                    st, et});
                        //导出
                        export(items);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        {
            byitem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Timestamp st = new Timestamp(DateUtil.parse(
                                startField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                        Timestamp et = new Timestamp(DateUtil.parse(
                                endField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                        List<Object[]> items = baseDao.find("bill.find.group.item.time", new String[]{
                                    "stime", "etime"}, new Object[]{
                                    st, et});
                        //导出
                        export(items);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        {
            byuser.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Timestamp st = new Timestamp(DateUtil.parse(
                                startField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                        Timestamp et = new Timestamp(DateUtil.parse(
                                endField.getText(), DateUtil.yyyy_MM_dd_HH_mm_ss).getTime());
                        List<Object[]> items = baseDao.find("bill.find.group.user.time", new String[]{
                                    "stime", "etime"}, new Object[]{
                                    st, et});
                        //导出
                        export(items);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }

    private void export(List<Object[]> items) {
        //Export                        
        int opencode = chooser.showSaveDialog(getRootPane());
        if (opencode == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            HelperUtil.exportExcel(file, items);
        }
    }
}

class XlsFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.getName() != null && f.getName().toLowerCase().endsWith(".xls")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Excel文件";
    }
}
