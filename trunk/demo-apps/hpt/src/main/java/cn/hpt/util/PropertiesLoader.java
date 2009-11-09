package cn.hpt.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public class PropertiesLoader extends ResourceBundle {

    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    private Properties props;

    public PropertiesLoader() {

    }

    public void init() {
        InputStream in = null;
        try {
            String conf = System.getProperty("user.dir");
            System.out.println("[resource:]" + this.getResource());
            File f = new File(conf, this.getResource());
            if (!f.exists()) {
                System.err.printf("ERROR:The %s/%s is not find.", conf, this.getResource());
            } else {
                in = new FileInputStream(f);
                props = new Properties();

                props.loadFromXML(in);
//			props.loadFromXML(this.getClass().getResourceAsStream(
//					this.getResource()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (null != in) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected Object handleGetObject(String key) {
        return props.getProperty(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        Set<String> handleKeys = props.stringPropertyNames();
        return Collections.enumeration(handleKeys);
    }
}
