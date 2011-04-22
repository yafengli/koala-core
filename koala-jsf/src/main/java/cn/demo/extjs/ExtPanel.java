package cn.demo.extjs;

import javax.faces.component.UIComponentBase;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-18
 * Time: 16:00:10
 * To change this template use File | Settings | File Templates.
 */
public class ExtPanel extends UIComponentBase {
    public static final String ComponentType = "cn.demo.extjs.ExtPanel";

    @Override
    public String getFamily() {
        return ComponentType;
    }

    @Override
    public String getRendererType() {
        return "cn.demo.extjs.ExtPanelRenderer";
    }
}
