package org.apache.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.*;
import java.util.Properties;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @goal create-project
 * @requiresProject false
 * @phase process-sources
 */
public class CreateProject extends AbstractMojo {

    public static final String PACKAGE_PATTERN = "^[a-zA-z][.0-9a-zA-Z]*[a-zA-Z]$";
    /**
     * @parameter
     *  expression="${cp.name}"
     * @required
     */
    private String projectName;
    private Properties ps = new Properties();
    private File baseDir;
    private File srcDir;
    private File resDir;
    private File tptDir;
    private File envDir;

    public void execute()
            throws MojoExecutionException {
        /* init config */
        if (createInit()) {
            /* make the files */
            SAXReader saxreader = new SAXReader();
            try {
                /* modify the pom file*/
                Document doc_pom = saxreader.read(getClass().getResourceAsStream(PropertiesDefination.INIT_CORE_DIR + PropertiesDefination.INIT_POM_FILE));

                Element root = doc_pom.getRootElement();
                Element groupId_e = root.element("groupId");
                Element name_e = root.element("name");
                Element artifactId_e = root.element("artifactId");
                Element version_e = root.element("version");

                if (name_e != null) {
                    name_e.setText(this.projectName);
                }
                if (artifactId_e != null) {
                    artifactId_e.setText(this.projectName);
                }
                if (groupId_e != null) {
                    groupId_e.setText(ps.getProperty(PropertiesDefination.PACKAGE_NAME));
                }
                if (version_e != null) {
                    version_e.setText(ps.getProperty(PropertiesDefination.VERSION));
                }
                /* maven pom.xml */
                createFiles(doc_pom.asXML(), new File(baseDir, PropertiesDefination.INIT_POM_FILE));
                /* jetty env file */
                Document doc_jetty = saxreader.read(getClass().getResourceAsStream(PropertiesDefination.INIT_CORE_DIR + PropertiesDefination.INIT_JETTY_ENV_FILE));
                createFiles(doc_jetty.asXML(), new File(baseDir, PropertiesDefination.INIT_JETTY_ENV_FILE));
                /* model and view ftl */
                File view = new File(tptDir, "view.ftl");
                createFiles(getClass().getResourceAsStream("/template/view.ftl"), view);
                File model = new File(tptDir, "model.ftl");
                createFiles(getClass().getResourceAsStream("/template/model.ftl"), model);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean createInit() {
        ps.putAll(PropertiesDefination.param());
        FileWriter config_w = null;
        try {
            /* read the put in param */
            for (Iterator it = ps.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                String value = ps.getProperty(key);
                if (value == null || value.trim().length() < 1) {
                    while (true) {
                        try {
                            System.out.print("[" + key + "]:");
                            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                            value = reader.readLine();
                            ps.setProperty(key, value);
                            //TODO
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            /* init the default */
            ps.putAll(PropertiesDefination.init());
            /* init all directoies */
            baseDir = new File(System.getProperty("user.dir"), projectName);
            srcDir = new File(baseDir, ps.getProperty(PropertiesDefination.SOURCE_DIR));
            resDir = new File(baseDir, ps.getProperty(PropertiesDefination.RESOURCE_DIR));
            tptDir = new File(baseDir, ps.getProperty(PropertiesDefination.TEMPLATE_DIR));
            envDir = new File(baseDir, ps.getProperty(PropertiesDefination.SETTING_DIR));
            if (baseDir.exists()) {
                System.err.print("The directory is not null!"+"["+baseDir.getAbsolutePath()+"]");
                return false;
            }
            /* make the directories */
            baseDir.mkdirs();
            srcDir.mkdirs();
            resDir.mkdirs();
            tptDir.mkdirs();
            envDir.mkdirs();
            /* store the config file */
            config_w = new FileWriter(new File(envDir, PropertiesDefination.INIT_CONFIG_FILE));

            ps.store(config_w, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (config_w != null) {
                    config_w.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void createFiles(InputStream in, File f) {
        FileWriter w = null;
        BufferedReader reader = null;
        try {
            w = new FileWriter(f);
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                w.write(line);
                w.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createFiles(String content, File f) {
        FileWriter w = null;
        try {
            w = new FileWriter(f);
            w.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
