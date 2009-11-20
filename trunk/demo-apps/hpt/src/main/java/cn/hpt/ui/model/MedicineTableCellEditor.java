package cn.hpt.ui.model;

import java.awt.Component;
import java.util.EventObject;

import javax.annotation.PostConstruct;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hpt.model.Category;
import cn.hpt.ui.component.MedicinePanel;
import cn.hpt.ui.extend.HptFont;

@Service
public class MedicineTableCellEditor extends JComboBox implements
        TableCellEditor {

    public static final Logger logger = LoggerFactory.getLogger(MedicineTableCellEditor.class);
    private static final long serialVersionUID = 3568927884211864044L;
    protected EventListenerList listenerList = new EventListenerList();
    protected ChangeEvent changeEvent = new ChangeEvent(this);
    @Autowired
    private CategoryTabelModel tabelModel;
    @Autowired
    public MedicinePanel medicinePanel;
    @Autowired
    public MedicineTableModel mtableModel;
    @Autowired
    private HptFont font;

    @PostConstruct
    public void init() {
        for (Category cg : tabelModel.getItem()) {
            this.addItem(cg.getName());
        }
    }

    protected void fireEditingStopped() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        this.setSelectedIndex(-1);
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
        int index = getSelectedIndex();
        if (index >= 0) {
            Category cg = tabelModel.getItem().get(index);
            return cg;
        } else {
            return null;
        }
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
