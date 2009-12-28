package cn.demo;

import javax.swing.*;
import java.util.List;
import java.util.Iterator;
import java.awt.*;

/**
 * Date: 2009-9-2
 * Time: 17:57:55
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class BoxLayoutPanel extends JPanel{
 	private static final long serialVersionUID = -537579453002423409L;
	private List<Component> panelComponents;
    private int axis;

    public void setPanelComponents(List<Component> panelComponents) {
        this.panelComponents = panelComponents;
    }

    public void setAxis(int axis) {
        this.axis = axis;
    }
    public void init() {
           setLayout(new BoxLayout(this, axis));

           for (Iterator<Component> iter = panelComponents.iterator();
                iter.hasNext();) {
               Component component = iter.next();
               add(component);
           }
       }
    
    
}

