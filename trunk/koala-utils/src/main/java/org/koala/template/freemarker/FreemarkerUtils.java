package org.koala.template.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.koala.template.ITemplateUtils;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

/**
 * User: phoenixup
 * Date: 2010-7-16
 * Time: 10:25:30
 */
public class FreemarkerUtils implements ITemplateUtils {
    public static String TEMPLATE_LOAD_PATH = "template.path";
    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void init(Object initObj) {
        if(initObj!=null && initObj instanceof Configuration){
            this.setConfiguration((Configuration)initObj);
        }
    }

    public String template(String templateFileName, Map model) {
        StringWriter writer = new StringWriter();
        try {            
            Template t = configuration.getTemplate(templateFileName);
            t.process(model, writer);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
