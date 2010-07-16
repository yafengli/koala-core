package org.koala.utils.template.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.koala.utils.template.ITemplateUtils;

import java.io.StringWriter;
import java.util.Map;

/**
 * User: phoenixup
 * Date: 2010-7-16
 * Time: 10:25:30
 */
public class VelocityUtils implements ITemplateUtils {

    private VelocityEngine velocityEngine;

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public void init(Object initObj) {
        try {
            if (initObj != null && initObj instanceof VelocityEngine) {
                this.setVelocityEngine((VelocityEngine) initObj);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String template(String templateFileName, Map model) {
        StringWriter writer = new StringWriter();
        try {
            Template t = this.getVelocityEngine().getTemplate(templateFileName);
            VelocityContext context = new VelocityContext(model);
            t.merge(context, writer);            
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}