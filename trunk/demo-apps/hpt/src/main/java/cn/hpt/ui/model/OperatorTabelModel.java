package cn.hpt.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hpt.dao.IOperatorDao;
import cn.hpt.model.Operator;

@Component
public class OperatorTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = -6945298295399270858L;

	public static final String[] columnNames = new String[] { "编号", "用户名",
			"用户描述", "登录密码", "登录时间" };

	private List<Operator> lop;

	@Autowired
	private IOperatorDao operatorDao;

	public List<Operator> getItem() {
		if (lop == null) {
			lop = operatorDao.findAll();
		}
		return lop;
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
		Operator op = getItem().get(rowIndex);
		switch (columnIndex) {
		case 1:
			op.setLoginname(value.toString());
			break;
		case 2:
			op.setDesc(value.toString());
			break;
		case 3:
			op.setPassword(value.toString());
			break;
		}
		operatorDao.update(op);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Operator op = getItem().get(rowIndex);
		Object o = null;
		switch (columnIndex) {
		case 0:
			o = op.getPid();
			break;
		case 1:
			o = op.getLoginname();
			break;
		case 2:
			o = op.getDesc();
			break;
		case 3:
			o = op.getPassword();
			break;
		case 4:
			o = op.getLastlogin();
			break;
		}
		return o;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0 || columnIndex == 4)
			return false;
		else
			return true;
	}

}
