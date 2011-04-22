package cn.hpt.ui.model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hpt.ui.extend.HptFont;

@Service
public class SelectColorTableCellRenderer implements TableCellRenderer {

    private static final long serialVersionUID = 2284481160177046667L;
    public static final Logger logger = LoggerFactory.getLogger(SelectColorTableCellRenderer.class);
    public static DefaultTableCellRenderer DF = new DefaultTableCellRenderer();
    @Autowired
    private HptFont font;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        final Component renderer = DF.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);       
        table.setRowHeight(font.getSize_12().getSize() + 5);
        table.getColumnModel().getColumn(column).setPreferredWidth(20);
        renderer.setFont(font.getSize_12());
        if (isSelected) {
            if (hasFocus && table.isCellEditable(row, column)) {
                table.editCellAt(row, column);
                renderer.setBackground(Color.WHITE);
                renderer.setForeground(Color.BLACK);
            } else {
                renderer.setBackground(Color.GRAY);
                renderer.setForeground(Color.BLACK);
            }
        } else {
            renderer.setBackground(Color.WHITE);
            renderer.setForeground(Color.BLACK);
        }
        return renderer;
    }
}
