package cn.demo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cn.demo.dao.IDemoDao;
import cn.demo.model.Demo;

/**
 * Date: 2009-9-2
 * Time: 18:06:57
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class ItemTableModel extends AbstractTableModel {
    List<Demo> itemList;
    String[] columnNames;
    IDemoDao demoDao;

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public IDemoDao getDemoDao() {
        return demoDao;
    }

    public void setDemoDao(IDemoDao demoDao) {
        this.demoDao = demoDao;
    }

    public List<Demo> getItemList() {
        if (itemList == null) {
            itemList = demoDao.findAll();
        }
        return itemList;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex >= 1)
            return true;
        else
            return false;
    }

    
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }


    public int getRowCount() {
        return getItemList().size();
    }

    public void setValueAt(Object value,
                           int rowIndex, int columnIndex) {
        Demo demo = getItemList().get(rowIndex);
        switch (columnIndex) {
            case 1:
                demo.setName(value.toString());
                break;
            case 2:
                demo.setDesc(value.toString());
                break;
        }
        demoDao.update(demo);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Demo demo = getItemList().get(rowIndex);
        Object o = null;
        switch (columnIndex) {
            case 0:
                o = demo.getDid();
                break;
            case 1:
                o = demo.getName();
                break;
            case 2:
                o = demo.getDesc();
                break;
        }
        return o;
    }
}
