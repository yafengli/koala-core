package cn.demo;

import java.awt.event.ActionEvent;

/**
 * Date: 2009-9-4
 * Time: 16:00:58
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class AddNewButtonActionListener extends ListTableActionListener{
    public void actionPerformed(ActionEvent e) {
        jdialog.setVisible(true);        
        list.add("New Item.");
        jTable.revalidate();
    }
}
