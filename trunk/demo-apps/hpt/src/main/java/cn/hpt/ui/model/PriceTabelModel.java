package cn.hpt.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.model.BillRecord;
import cn.hpt.ui.view.PriceDialog;

@Component
public class PriceTabelModel extends AbstractTableModel {

    private static final long serialVersionUID = -6945298295399270858L;

    public static final String[] columnNames = new String[]{"标号", "药品简写", "药品名称",
            "药品价格", "数量"};

    private List<BillRecord> item;
    @Autowired
    private PriceDialog priceDialog;

    @Autowired
    private PriceTableCellEditor cellEditor;

    public List<BillRecord> getItem() {
        if (item == null) {
            item = new ArrayList<BillRecord>();
        }
        return item;
    }

    public void setItem(List<BillRecord> item) {
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
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public int getRowCount() {
        return getItem().size();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < getItem().size()) {
            BillRecord item = getItem().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    break;
                case 1:
                    cellEditor.reload(value.toString());
                    break;
                case 2:
                    if (item != null && item.getMedicine() != null)
                        item.getMedicine().getMname();
                    break;
                case 3:
                    if (item != null && item.getMedicine() != null)
                        item.getMedicine().getPrice();
                    break;
                case 4:
                    if (item != null)
                        item.setBnumber(Long.valueOf(value.toString()));
                    break;
            }
            updatePrice();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        if (rowIndex >= 0 && rowIndex < getItem().size()) {
            BillRecord item = getItem().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    o = rowIndex;
                    break;
                case 1:
                    if (item.getMedicine() != null)
                        o = item.getMedicine().getMshortcut();
                    else {
                        o = "";
                    }
                    break;
                case 2:
                    if (item.getMedicine() != null)
                        o = item.getMedicine().getMname();
                    else {
                        o = "";
                    }
                    break;
                case 3:
                    if (item.getMedicine() != null)
                        o = item.getMedicine().getPrice();
                    else
                        o = 0.00;
                    break;
                case 4:
                    o = item.getBnumber();
                    break;
            }
        }
        return o;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3 || columnIndex == 0)
            return false;
        else
            return true;
    }

    /* 跟新价格 */
    private void updatePrice() {
        float result = 0;
        for (BillRecord br : getItem()) {
            if (br != null && br.getMedicine() != null) {
                result += br.getBnumber() * br.getMedicine().getPrice();
            }
        }
        NumberFormat nf=NumberFormat.getInstance();
        priceDialog.priceField.setText(nf.format(result));
    }
}
