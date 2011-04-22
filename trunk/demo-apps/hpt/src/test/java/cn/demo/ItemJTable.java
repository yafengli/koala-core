package cn.demo;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * @author Administrator
 *
 */
public class ItemJTable extends JTable {
	private GenderRenderer genderRenderer;
	private  GenderEditor genderEditor;
	public GenderRenderer getGenderRenderer() {
		return genderRenderer;
	}

	public void setGenderRenderer(GenderRenderer genderRenderer) {
		this.genderRenderer = genderRenderer;
	}

	
	public GenderEditor getGenderEditor() {
		return genderEditor;
	}

	public void setGenderEditor(GenderEditor genderEditor) {
		this.genderEditor = genderEditor;
	}

	public void init(){		
		TableColumn tc = this.getColumnModel().getColumn(2);// genderColumn为你的性别的例序号。
        tc.setCellRenderer(this.getGenderRenderer());
        tc.setCellEditor(this.getGenderEditor());         
	}
}
