/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.maven.plugins.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class TemplateProcess {

    private static Configuration cfg = new Configuration();
    private static TemplateProcess instance;

    private TemplateProcess() {
    }

    public static void factory(File templateFile, String templateName, File outputFile, Map map) {
        StringWriter sw = new StringWriter();
        FileWriter fw = null;
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
            }
            fw = new FileWriter(outputFile);
            cfg.setDirectoryForTemplateLoading(templateFile);
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template tl = cfg.getTemplate(templateName);

            tl.process(map, sw);
            fw.write(sw.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static Properties factory(File propFile) {
        Properties ps = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(propFile);
            ps.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ps;
    }
}
