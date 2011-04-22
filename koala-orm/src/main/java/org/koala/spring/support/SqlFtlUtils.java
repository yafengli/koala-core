package org.koala.spring.support;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author YaFengLi
 */
public class SqlFtlUtils {

    private Configuration cfg;
    private String encoding = "UTF-8";
    private MessageDigest md5;
    private StringTemplateLoader stl;

    public Configuration getCfg() {
        return cfg;
    }

    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public SqlFtlUtils() {
        try {
            if (stl == null) {
                stl = new StringTemplateLoader();
            }
            if (md5 == null) {
                md5 = MessageDigest.getInstance("MD5");
            }
            if (cfg != null) {
                cfg.setObjectWrapper(new DefaultObjectWrapper());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ftlFile    template file name
     * @param model      Map data
     * @param tmlLoading template loading
     * @return
     * @throws java.lang.Exception
     */
    public String getSql(String ftlFile, Map model, File tmlLoading) throws Exception {
        StringWriter writer = new StringWriter();
        try {
            cfg.setDirectoryForTemplateLoading(tmlLoading);
            Template temp = cfg.getTemplate(ftlFile);
            /* Merge data-model with template */
            temp.process(model, writer);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return writer.toString();
    }

    @Deprecated
    public String process(String content, Map model) throws Exception {
        StringWriter writer = new StringWriter();
        try {
            String keyToUse = content;
            if (stl.findTemplateSource(keyToUse) == null) {
                stl.putTemplate(keyToUse, content);
            }
            cfg.setTemplateLoader(stl);
            Template temp = cfg.getTemplate(keyToUse);
            /* Merge data-model with template */
            temp.process(model, writer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return writer.toString();
    }

    public String process(String key, ResourceBundle rb, Map model) throws Exception {
        StringWriter writer = new StringWriter();
        try {
            if (stl.findTemplateSource(key) == null) {
                stl.putTemplate(key, rb.getString(key));
            }
            cfg.setTemplateLoader(stl);
            Template temp = cfg.getTemplate(key);
            /* Merge data-model with template */
            temp.process(model, writer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return writer.toString();
    }
}
