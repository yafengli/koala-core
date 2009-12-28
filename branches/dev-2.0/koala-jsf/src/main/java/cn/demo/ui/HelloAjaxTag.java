package cn.demo.ui;


import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;
import javax.el.ValueExpression;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-17
 * Time: 13:29:26
 * To change this template use File | Settings | File Templates.
 */
public class HelloAjaxTag extends UIComponentELTag {
    public static final String COMPONENT_TYPE = "cn.demo.ui.HelloAjax";

    private ValueExpression dname;

    public ValueExpression getDname() {
        return dname;
    }

    public void setDname(ValueExpression dname) {
        this.dname = dname;
    }

    @Override
    public String getComponentType() {
        return HelloAjaxTag.COMPONENT_TYPE;
    }

    @Override
    public String getRendererType() {
        return null;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        Object o = this.getDname().getValue(this.getELContext());
        System.out.printf("[ooo=%s]",o);
        component.getAttributes().put("name", o);
    }
}
