package cn.hpt.ui.model;

import cn.hpt.model.BillRecord;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PriceIITabelModel extends AbstractTableModel {

    public static final String[] columnNames = new String[]{"标号", "药品名称",
        "药品价格", "数量"};
    private List<BillRecord> items;

    public List<BillRecord> getItems() {
        if (items == null) {
            items = new ArrayList<BillRecord>();
        }
        return items;
    }

    public void setItems(List<BillRecord> items) {
        this.items = items;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return getItems().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        if (rowIndex >= 0 && rowIndex < getItems().size()) {
            BillRecord item = getItems().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    o = rowIndex;
                    break;
                case 1:
                    if (item.getMedicine() != null) {
                        o = item.getMedicine().getMname();
                    } else {
                        o = "";
                    }
                    break;
                case 2:
                    if (item.getMedicine() != null) {
                        o = item.getMedicine().getPrice();
                    } else {
                        o = 0.00;
                    }
                    break;
                case 3:
                    o = item.getBnumber();
                    break;
            }
        }
        return o;
    }

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        if (rowIndex >= 0 && rowIndex < getItems().size()) {
//            BillRecord item = getItems().get(rowIndex);
//            switch (columnIndex) {
//                case 0:
//                    break;
//                case 1:
//                    if (item != null && item.getMedicine() != null)
//                        item.getMedicine().getMname();
//                    break;
//                case 2:
//                    if (item != null && item.getMedicine() != null)
//                        item.getMedicine().getPrice();
//                    break;
//                case 3:
//                    if (item != null)
//                        item.setBnumber(Long.valueOf(aValue.toString()));
//                    break;
//            }
//        }
//    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getItems().size() > 0) {
            return getValueAt(0, columnIndex).getClass();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
