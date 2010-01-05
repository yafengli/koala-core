package cn.hpt.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.springframework.stereotype.Component;

import cn.hpt.model.BillRecord;

@Component
public class BillRecordTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = 5283877723804938964L;

	public static final String[] columnNames = new String[] { "药品简写", "药品名称",
			"药品价格", "数量" };

	private List<BillRecord> item;

	public List<BillRecord> getItem() {
		if (item == null) {
			// item = billRecordDao.findAll();
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
		return;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object o = null;
		if (rowIndex >= 0 && rowIndex < getItem().size()) {
			BillRecord item = getItem().get(rowIndex);
			switch (columnIndex) {
			case 0:
				if (item.getMedicine() != null)
					o = item.getMedicine().getMshortcut();
				else {
					o = "";
				}
				break;
			case 1:
				if (item.getMedicine() != null)
					o = item.getMedicine().getMname();
				else {
					o = "";
				}
				break;
			case 2:
				if (item.getMedicine() != null)
					o = item.getMedicine().getPrice();
				else
					o = 0.00;
				break;
			case 3:
				o = item.getBnumber();
				break;
			}
		}
		return o;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}