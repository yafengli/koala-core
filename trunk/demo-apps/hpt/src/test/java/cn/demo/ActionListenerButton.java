package cn.demo;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Date: 2009-9-4
 * Time: 15:54:52
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class ActionListenerButton extends JButton{
    private ActionListener actionListener;

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    public void init(){
        this.addActionListener(actionListener);
    }
}
