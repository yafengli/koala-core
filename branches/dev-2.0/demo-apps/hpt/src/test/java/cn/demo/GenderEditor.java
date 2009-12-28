package cn.demo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

public class GenderEditor extends JComboBox implements TableCellEditor {

	protected EventListenerList listenerList = new EventListenerList();
	protected ChangeEvent changeEvent = new ChangeEvent(this);

	public GenderEditor() {
		super();
		addItem("Male");
		addItem("Female");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fireEditingStopped();
			}
		});
	}

	public void addCellEditorListener(CellEditorListener listener) {
		listenerList.add(CellEditorListener.class, listener);
	}

	public void removeCellEditorListener(CellEditorListener listener) {
		listenerList.remove(CellEditorListener.class, listener);
	}

	protected void fireEditingStopped() {
		CellEditorListener listener;
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i++) {
			if (listeners[i] == CellEditorListener.class) {
				listener = (CellEditorListener) listeners[i + 1];
				listener.editingStopped(changeEvent);
			}
		}
	}

	protected void fireEditingCanceled() {
		CellEditorListener listener;
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i++) {
			if (listeners[i] == CellEditorListener.class) {
				listener = (CellEditorListener) listeners[i + 1];
				listener.editingCanceled(changeEvent);
			}
		}
	}

	public void cancelCellEditing() {
		fireEditingCanceled();
	}

	public boolean stopCellEditing() {
		fireEditingStopped();
		return true;
	}

	public boolean isCellEditable(EventObject event) {
		return true;
	}

	public boolean shouldSelectCell(EventObject event) {
		return true;
	}

	public Object getCellEditorValue() {
		return new Boolean(getSelectedIndex() == 0 ? true : false);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {		
		return this;
	}
}