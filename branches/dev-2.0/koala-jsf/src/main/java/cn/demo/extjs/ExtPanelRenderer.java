package cn.demo.extjs;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-18
 * Time: 16:10:09
 * To change this template use File | Settings | File Templates.
 */
public class ExtPanelRenderer extends Renderer{
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        context.getExternalContext().log("The start.");
        
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        context.getExternalContext().log("The end.");
    }
}
