package cn.hpt.ui.model;

import cn.hpt.model.Medicine;
import cn.hpt.ui.extend.HptFont;
import org.koala.dao.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;
import java.util.List;

@Service
public class PriceTableCellEditor extends JComboBox implements TableCellEditor {

    public static final Logger logger = LoggerFactory.getLogger(PriceTableCellEditor.class);
    private static final long serialVersionUID = 3568927884211864044L;
    protected ChangeEvent changeEvent = new ChangeEvent(this);
    @Autowired
    private IDao baseDao;
    @Autowired
    private HptFont font;
    private List<Medicine> lm;

    /* 收 mshortcut影响 重新读取药品名 */
    public void reload(String value) {
        removeAllItems();
        lm = baseDao.find("medicine.find.like.byshortcut",
                new String[]{"mshortcut"}, new Object[]{value + "%"});
        for (Medicine md : lm) {
            addItem(md.getMname());
        }
    }

    protected void fireEditingStopped() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length - 1; i++) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length - 1; i++) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingCanceled(changeEvent);
            }
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.setFont(font.getSize_12());
        return this;
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }

    @Override
    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    @Override
    public Object getCellEditorValue() {
        Object obj = null;
        try {
            if (lm != null && getSelectedIndex() >= 0 && getSelectedIndex() < lm.size()) {
                obj = lm.get(getSelectedIndex());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }
}
