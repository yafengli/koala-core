package cn.hpt.ui.component;

import cn.hpt.model.Operator;
import cn.hpt.model.Role;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.model.OperatorTabelModel;
import cn.hpt.ui.model.SelectColorTableCellRenderer;
import cn.hpt.ui.model.TableHeaderRenderer;
import cn.hpt.util.PropertiesLoader;
import org.koala.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

@Service
public class OperatorPanel extends JPanel {

    private static final long serialVersionUID = -1047279537436880394L;
    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private IDao baseDao;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private HptFont font;
    @Autowired
    private TableHeaderRenderer tableHeaderRenderer;
    @Autowired
    private OperatorTabelModel operatorTabelModel;
    @Autowired
    private SelectColorTableCellRenderer cellRenderer;
    private JScrollPane contentbp = new JScrollPane();
    private JPanel buttonbp = new JPanel();
    private JTable hptTable = new JTable();
    private JButton add = new JButton("增加");
    private JButton delete = new JButton("删除");
    private JButton excel = new JButton("导出");
    private JButton print = new JButton("打印");

    @PostConstruct
    public void init() {
        setLayout(new BorderLayout());
        add(contentbp, BorderLayout.CENTER);
        add(buttonbp, BorderLayout.SOUTH);
        {
            hptTable.setModel(operatorTabelModel);
            hptTable.setRowSorter(new TableRowSorter<OperatorTabelModel>(
                    operatorTabelModel));
            int columnIndex = hptTable.getColumnModel().getColumnCount();
            for (int i = 0; i < columnIndex; i++) {
                TableColumn tc = hptTable.getColumnModel().getColumn(i);
                tc.setHeaderRenderer(tableHeaderRenderer);
                tc.setCellRenderer(cellRenderer);
            }
            contentbp.setViewportView(hptTable);
        }
        {
            buttonbp.setLayout(new FlowLayout());
            buttonbp.add(add);
            buttonbp.add(delete);
            buttonbp.add(excel);
            buttonbp.add(print);
        }
        {
            hptTable.setFont(font.getSize_12());
            add.setFont(font.getSize_12());
            delete.setFont(font.getSize_12());
            excel.setFont(font.getSize_12());
            print.setFont(font.getSize_12());
        }
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Operator item = new Operator();
                item.setLoginname("");
                item.setPassword("123456");
                item.setDescribe("员工");
                item.setCreatedate(new Timestamp(System.currentTimeMillis()));
                item.setRole(baseDao.findById(Role.class, Role.OPERATOR_ID));

                baseDao.save(item);
                ((OperatorTabelModel) hptTable.getModel()).getItem().add(item);
                hptTable.revalidate();
            }
        });
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("del");
                int selectRow = hptTable.getSelectedRow();
                if (hptTable.isEditing()) {
                    return;
                } else if (selectRow >= 0
                        && selectRow <= operatorTabelModel.getItem().size()) {
                    int option = JOptionPane.showConfirmDialog(mainFrame, pl.getString("del.operator.msg"), null,
                            JOptionPane.YES_NO_OPTION);
                    switch (option) {
                        case JOptionPane.YES_OPTION:
                            Operator item = operatorTabelModel.getItem().get(
                                    selectRow);
                            operatorTabelModel.getItem().remove(selectRow);
                            baseDao.remove(item);
                            hptTable.revalidate();
                            break;
                        case JOptionPane.NO_OPTION:
                            break;
                    }
                }
            }
        });
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
