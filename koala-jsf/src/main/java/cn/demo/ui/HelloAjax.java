package cn.demo.ui;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-17
 * Time: 13:23:18
 * To change this template use File | Settings | File Templates.
 */
public class HelloAjax extends UIComponentBase implements Ajax {
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        sayHello(context);
    }

    public void sayHello(FacesContext fc) {
        System.out.printf("What is [%s] the hello going on?", this.getId());
        Map<String, Object> items = this.getAttributes();
        for (String key : items.keySet()) {
            System.out.printf("[%s=%s]\n", key, items.get(key));
        }

        String clientId = this.getClientId(fc);//获得组件的客户端ID        
        ResponseWriter rw = fc.getResponseWriter();
        try {
            rw.startElement("input", this);
            rw.writeAttribute("id", clientId, null);
            rw.writeAttribute("name", clientId, null);
            rw.writeAttribute("type", "text", null);
            rw.endElement("input");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public String getFamily() {
        return HelloAjaxTag.COMPONENT_TYPE;
    }
}
