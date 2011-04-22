package cn.hpt.ui.model;

import cn.hpt.ui.extend.HptFont;
import java.awt.Component;

import javax.annotation.PostConstruct;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatternTableCellEditor extends AbstractCellEditor implements
        TableCellEditor {

    @Autowired
    private HptFont font;
    private static final long serialVersionUID = 2214276271837993833L;
    private JTextField tf;
    private DefaultCellEditor de;

    @PostConstruct
    private void init() {
        tf = new JTextField();
        tf.setFont(font.getSize_12());
        de = new DefaultCellEditor(tf);
    }

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
