package cn.hpt.ui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hpt.model.BillRecord;
import cn.hpt.model.Medicine;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.model.PriceTabelModel;
import cn.hpt.ui.model.PriceTableCellEditor;
import cn.hpt.ui.model.SelectColorTableCellRenderer;
import cn.hpt.ui.model.TableHeaderRenderer;

@Service
public class PricePanel extends JPanel {

    private static final long serialVersionUID = -1047279537436880394L;
    @Autowired
    public PriceTabelModel tabelModel;
    @Autowired
    private PriceTableCellEditor cellEditor;
    @Autowired
    private SelectColorTableCellRenderer cellRenderer;
    @Autowired
    private HptFont font;
    @Autowired
    private TableHeaderRenderer tableHeaderRenderer;
    private JScrollPane contentbp = new JScrollPane();
    public JTable hptTable = new JTable();

    @PostConstruct
    public void init() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(contentbp, BorderLayout.CENTER);
        {
            hptTable.setModel(tabelModel);
            hptTable.setFont(font.getSize_12());
            hptTable.setBackground(Color.WHITE);
            hptTable.setRowSorter(new TableRowSorter<PriceTabelModel>(
                    tabelModel));
            int columnIndex = hptTable.getColumnModel().getColumnCount();
            for (int i = 0; i < columnIndex; i++) {
                TableColumn tc = hptTable.getColumnModel().getColumn(i);
                tc.setHeaderRenderer(tableHeaderRenderer);
                tc.setCellRenderer(cellRenderer);
            }
            //药品选择
            hptTable.getColumnModel().getColumn(2).setCellEditor(cellEditor);
            cellEditor.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        Object obj = cellEditor.getCellEditorValue();
                        if (obj != null && obj instanceof Medicine) {
                            Medicine md = (Medicine) obj;
                            BillRecord br = tabelModel.getItem().get(
                                    hptTable.getSelectedRow());
                            br.setMedicine(md);
                        }
                    }
                }
            });
            contentbp.setViewportView(hptTable);

            // JTable的背景颜色设置
            hptTable.setBackground(new Color(230, 230, 230));
            // JTable没有选中的文字颜色
            hptTable.setForeground(new Color(0, 0, 0));
            // JTable边线颜色
            hptTable.setGridColor(new Color(100, 100, 100));
            // 获得表头
            JTableHeader tableH = hptTable.getTableHeader();
            // 设置表头的背景色
            tableH.setBackground(new Color(200, 200, 200));
            // 设置表头的文字颜色
            tableH.setForeground(new Color(0, 0, 0));
            // JScrollPane的背景色设置
            contentbp.getViewport().setBackground(Color.WHITE);
        }
    }
}
