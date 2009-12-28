package cn.hpt.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

public class PropertiesLoader extends ResourceBundle {

    private String resource;
    private File f;

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
            f = new File(conf, this.getResource());
            if (!f.exists()) {
                System.err.printf("ERROR:The %s/%s is not find.", conf, this.getResource());
            } else {
                in = new FileInputStream(f);
                props = new Properties();

                props.loadFromXML(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    public void setKey(String key, String val) {
        props.setProperty(key, val);
    }

    public void store() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(f);
            props.storeToXML(out, DateUtil.format(new Date(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
