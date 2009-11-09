package cn.hpt.ui.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.koala.dao.IDao;

import cn.hpt.model.Category;
import cn.hpt.model.Medicine;
import cn.hpt.ui.MainFrame;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.model.MedicineTableCellEditor;
import cn.hpt.ui.model.MedicineTableModel;
import cn.hpt.ui.model.SelectColorTableCellRenderer;
import cn.hpt.util.PropertiesLoader;

@Service
public class MedicinePanel extends JPanel {

    private static final long serialVersionUID = -1047279537436880394L;
    public static final Logger logger = LoggerFactory
            .getLogger(MedicinePanel.class);

    @Autowired
    private MainFrame mainFrame;
    @Autowired
    private MedicineTableModel medicineTabelModel;
    @Autowired
    private IDao baseDao;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private SelectColorTableCellRenderer cellRenderer;
    @Autowired
    private MedicineTableCellEditor cellEditor;
    @Autowired
    private HptFont font;

    public JScrollPane contentbp = new JScrollPane();
    public JPanel buttonbp = new JPanel();
    public JTable hptTable = new JTable();
    public JButton add = new JButton("增加");
    public JButton delete = new JButton("删除");
    public JButton excel = new JButton("导出");
    public JButton print = new JButton("打印");

    @PostConstruct
    public void init() {
        setLayout(new BorderLayout());
        add(contentbp, BorderLayout.CENTER);
        add(buttonbp, BorderLayout.SOUTH);
        {
            hptTable.setModel(medicineTabelModel);
            hptTable.setRowSorter(new TableRowSorter<MedicineTableModel>(
                    medicineTabelModel));
            int columnIndex = hptTable.getColumnModel().getColumnCount();
            for (int i = 0; i < columnIndex; i++) {
                TableColumn tc = hptTable.getColumnModel().getColumn(i);
                tc.setCellRenderer(cellRenderer);
            }
            hptTable.setRowHeight(font.LABLE_SONTI.getSize() + 5);
            cellEditor.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        int columnIndex = hptTable.getSelectedColumn();
                        int rowIndex = hptTable.getSelectedRow();
                        Object obj = cellEditor.getCellEditorValue();
                        if (obj != null && obj instanceof Category) {
                            medicineTabelModel.setValueAt("", rowIndex,
                                    columnIndex);
                        }
                    } else {
                        cellEditor.setEnabled(true);
                    }
                }
            });
            hptTable.getColumnModel().getColumn(5).setCellEditor(cellEditor);
            contentbp.setViewportView(hptTable);
        }
        {
            buttonbp.setLayout(new FlowLayout());
            buttonbp.add(add);
            buttonbp.add(delete);
            buttonbp.add(excel);
            buttonbp.add(print);
        }
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medicine item = new Medicine();

                baseDao.save(item);
                ((MedicineTableModel) hptTable.getModel()).getItem().add(item);
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
                        && selectRow < medicineTabelModel.getItem().size()) {
                    int option = JOptionPane.showConfirmDialog(mainFrame, pl
                            .getString("del.operator.msg"), null,
                            JOptionPane.YES_NO_OPTION);
                    switch (option) {
                        case JOptionPane.YES_OPTION:
                            Medicine item = medicineTabelModel.getItem().get(
                                    selectRow);
                            medicineTabelModel.getItem().remove(selectRow);
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
