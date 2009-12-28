package cn.demo.extjs;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-18
 * Time: 18:05:56
 * To change this template use File | Settings | File Templates.
 */
public class PanelHandler extends TagHandler {
    private TagAttribute id;

    public PanelHandler(TagConfig config) {
        super(config);
        this.id=this.getAttribute("id");
    }

    public void apply(FaceletContext faceletContext, UIComponent uiComponent) throws IOException {
        faceletContext.setAttribute("id",id.getValue());
    }
}
