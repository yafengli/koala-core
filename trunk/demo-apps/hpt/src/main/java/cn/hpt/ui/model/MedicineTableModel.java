package cn.hpt.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.dao.IMedicineDao;
import cn.hpt.model.Category;
import cn.hpt.model.Medicine;

@Component
public class MedicineTableModel extends AbstractTableModel {

	public static final Logger logger = LoggerFactory
			.getLogger(MedicineTableModel.class);

	private static final long serialVersionUID = -6945298295399270858L;

	public static final String[] columnNames = new String[] { "编号", "药品名称",
			"快捷简写", "药品价格", "药品编号", "药品类别" };

	private List<Medicine> item;

	@Autowired
	private IMedicineDao medicineDao;
	@Autowired
	private MedicineTableCellEditor cellEditor;

	public List<Medicine> getItem() {
		if (item == null) {
			item = medicineDao.findAll();
		}
		return item;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return getItem().size();
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
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Medicine item = getItem().get(rowIndex);
		switch (columnIndex) {
		case 1:
			item.setMname(value.toString());
			break;
		case 2:
			item.setMshortcut(value.toString());
			break;
		case 3:
			item.setPrice(Float.valueOf(value.toString()));
			break;
		case 4:
			item.setMnumber(value.toString());
			break;
		case 5:
			Object obj = cellEditor.getCellEditorValue();
			if (obj != null && obj instanceof Category) {
				item.setCategory((Category) obj);
			}
			break;
		}
		medicineDao.update(item);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Medicine item = getItem().get(rowIndex);
		Object o = null;
		switch (columnIndex) {
		case 0:
			o = item.getMid();
			break;
		case 1:
			o = item.getMname();
			break;
		case 2:
			o = item.getMshortcut();
			break;
		case 3:
			o = item.getPrice();
			break;
		case 4:
			o = item.getMnumber();
			break;
		case 5:
			if (item.getCategory() != null) {
				o = item.getCategory().getCname();
			}
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
