package cn.hpt.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.koala.dao.IDao;

import cn.hpt.model.Category;

@Component
public class CategoryTabelModel extends AbstractTableModel {

    private static final long serialVersionUID = -6945298295399270858L;

    public static final String[] columnNames = new String[]{"编号", "药品类别",
            "类别描述"};

    private List<Category> item;

    @Autowired
    private IDao baseDao;

    public List<Category> getItem() {
        if (item == null) {
            item = baseDao.findAll(Category.class);
        }
        return item;
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
        Category item = getItem().get(rowIndex);
        switch (columnIndex) {
            case 1:
                item.setCname(value.toString());
                break;
            case 2:
                item.setCdesc(value.toString());
                break;
        }
        baseDao.update(item);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category item = getItem().get(rowIndex);
        Object o = null;
        switch (columnIndex) {
            case 0:
                o = item.getCid();
                break;
            case 1:
                o = item.getCname();
                break;
            case 2:
                o = item.getCdesc();
                break;
        }
        return o;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return false;
        else
            return true;
    }

}
