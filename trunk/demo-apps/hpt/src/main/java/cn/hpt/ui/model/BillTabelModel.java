package cn.hpt.ui.model;

import cn.hpt.model.Bill;
import cn.hpt.util.DateUtil;
import org.springframework.stereotype.Component;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BillTabelModel extends AbstractTableModel {

    private static final long serialVersionUID = -6945298295399270858L;

    public static final String[] columnNames = new String[]{"收费单号", "用户姓名",
            "费用", "收费时间"};

    private List<Bill> item = new ArrayList<Bill>();

    public List<Bill> getItem() {
        return item;
    }

    public void setItem(List<Bill> item) {
        this.item = item;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object o = getValueAt(0, columnIndex);
        if (o != null)
            return o.getClass();
        else
            return null;
    }

    @Override
    public int getRowCount() {
        return getItem().size();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object o = null;
        if (rowIndex >= 0 && rowIndex < getItem().size()) {
            Bill item = getItem().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    o = item.getRid();
                    break;
                case 1:
                    o = item.getUsername();
                    break;
                case 2:
                    o = item.getPricenum();
                    break;
                case 3:
                    if (item.getRcreatedate() != null) {
                        o = DateUtil.format(new Date(item.getRcreatedate().getTime()), DateUtil.yyyy_MM_dd_HH_mm_ss);
                    }
                    break;
            }
        }
        return o;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
