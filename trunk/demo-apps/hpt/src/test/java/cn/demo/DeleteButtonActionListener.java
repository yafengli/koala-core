package cn.demo;

import java.awt.event.ActionEvent;

/**
 * Date: 2009-9-4
 * Time: 16:02:04
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class DeleteButtonActionListener extends ListTableActionListener {
    public void actionPerformed(ActionEvent e) {
        int selectRow = jTable.getSelectedRow();
        if (jTable.isEditing()) {
            return;
        } else if (selectRow>=0&&selectRow<list.size()){            
            list.remove(selectRow);
            jTable.revalidate();
        }
    }
}
