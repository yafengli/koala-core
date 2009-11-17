package cn.hpt.ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.koala.dao.IDao;

import cn.hpt.model.Bill;
import cn.hpt.model.BillRecord;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.model.BillRecordTabelModel;
import cn.hpt.ui.model.SelectColorTableCellRenderer;
import cn.hpt.ui.model.TableHeaderRenderer;
import cn.hpt.util.WindowUtil;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BillRecordDialog extends JDialog {

    private static final long serialVersionUID = -1547042069151210354L;
    @Autowired
    private BillRecordTabelModel tabelModel;
    @Autowired
    private SelectColorTableCellRenderer cellRenderer;
    @Autowired
    private IDao baseDao;
    @Autowired
    private HptFont font;
    @Autowired
    private TableHeaderRenderer tableHeaderRenderer;
    private JScrollPane contentbp = new JScrollPane();
    private JTable hptTable = new JTable();
    private JPanel buttonp = new JPanel();
    private JButton close = new JButton("关闭");

    @PostConstruct
    public void init() {

        this.setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        add(contentbp, BorderLayout.CENTER);
        {
            hptTable.setModel(tabelModel);
            hptTable.setRowSorter(new TableRowSorter<BillRecordTabelModel>(
                    tabelModel));
            int columnIndex = hptTable.getColumnModel().getColumnCount();
            for (int i = 0; i < columnIndex; i++) {
                TableColumn tc = hptTable.getColumnModel().getColumn(i);
                tc.setHeaderRenderer(tableHeaderRenderer);
                tc.setCellRenderer(cellRenderer);
            }
            contentbp.setViewportView(hptTable);
        }
        {
            final JDialog dialog = this;
            close.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
        }
        buttonp.add(close);
        add(buttonp, BorderLayout.SOUTH);
        //font
        {
            hptTable.setFont(font.getSize_12());
            close.setFont(font.getSize_12());
        }
    }

    public void view(Bill bill) {
        List<BillRecord> items = baseDao.find("billrecord.find.by.bill",
                new String[]{"bill"},
                new Object[]{bill});
        tabelModel.setItem(items);
        hptTable.revalidate();
        pack();
        setLocation(WindowUtil.center(this));
        setVisible(true);
    }
}
