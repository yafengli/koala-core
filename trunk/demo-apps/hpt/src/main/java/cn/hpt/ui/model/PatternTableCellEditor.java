package cn.hpt.ui.model;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import org.springframework.stereotype.Service;

@Service
public class PatternTableCellEditor extends AbstractCellEditor implements
		TableCellEditor {
	private static final long serialVersionUID = 2214276271837993833L;

	private static DefaultCellEditor de = new DefaultCellEditor(
			new JTextField());

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return de.getTableCellEditorComponent(table, value, isSelected, row,
				column);
	}

	@Override
	public Object getCellEditorValue() {
		return de.getCellEditorValue();
	}
}
