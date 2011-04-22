package cn.hpt.ui.model;

import cn.hpt.ui.extend.HptFont;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
@org.springframework.stereotype.Component
public class TableHeaderRenderer implements TableCellRenderer {

    @Autowired
    private HptFont font;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel lb = new JLabel();
        lb.setText(value.toString());
        lb.setHorizontalAlignment(lb.CENTER);
        lb.setFont(font.getSize_12_b());
        lb.setBorder(BorderFactory.createEtchedBorder());
        return lb;
    }
}
