package cn.demo;

import javax.swing.*;
import java.util.List;

/**
 * Date: 2009-9-9
 * Time: 14:32:49
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class FileMenu extends JMenu {
    private List<JMenuItem> ljis;
    private DemoCloseActionListener closeActionListener;

    public void setLjis(List<JMenuItem> ljis) {
        this.ljis = ljis;
    }

    public List<JMenuItem> getLjis() {
        return ljis;
    }

    public DemoCloseActionListener getCloseActionListener() {
        return closeActionListener;
    }

    public void setCloseActionListener(DemoCloseActionListener closeActionListener) {
        this.closeActionListener = closeActionListener;
    }

    public void init() {
        for (JMenuItem ji : ljis) {
            this.add(ji);
            if (ji.getText().equalsIgnoreCase("关闭")) {
                ji.addActionListener(closeActionListener);               
            }
        }
    }
}
