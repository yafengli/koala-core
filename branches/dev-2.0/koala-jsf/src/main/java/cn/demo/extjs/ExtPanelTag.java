package cn.demo.extjs;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-18
 * Time: 16:01:49
 * To change this template use File | Settings | File Templates.
 */
public class ExtPanelTag extends UIComponentELTag {

    @Override
    public String getComponentType() {
        return ExtPanel.ComponentType;
    }

    @Override
    public String getRendererType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        System.out.println("[setProperties]");
    }
}
