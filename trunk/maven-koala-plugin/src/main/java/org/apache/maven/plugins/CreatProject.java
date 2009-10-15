package org.apache.maven.plugins;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import java.io.BufferedReader;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @goal create-project
 * @requiresProject false
 * @phase process-sources
 */
public class CreatProject extends AbstractMojo {

    public static final String PACKAGE_PATTERN = "^[a-zA-z][.0-9a-zA-Z]*[a-zA-Z]$";
    /**
     * the name of project
     * @parameter 
     *  expression="${cp.name}"
     * @required
     */
    private String name;
    /**
     * the name of project
     * @parameter
     *  expression="${cp.core.init}"
     *  default-value="/init/"
     * @required
     */
    private String coreInit;
    /**
     * the name of project
     * @parameter
     *  expression="${cp.pom}"
     *  default-value="pom.xml"
     * @required
     */
    private String pomFile;
    /**
     * the name of project
     * @parameter
     *  expression="${cp.jetty}"
     *  default-value="jetty-env.xml"
     * @required
     */
    private String jettyEnvFile;
    /**
     * the name of project
     * @parameter
     *  expression="${cp.version}"
     *  default-value="1.0"
     * @required
     */
    private String version;

    public void execute()
            throws MojoExecutionException {
        String projectDir = System.getProperty("user.dir");
        File f = new File(projectDir, name);
        File src = new File(f, "src/main/java");
        File resources = new File(f, "src/main/resources");
        if (f.exists()) {
            System.err.print("The directory is not null!");
        } else {
            f.mkdirs();
            src.mkdirs();
            resources.mkdirs();

            String packageName = getPackage();//package name
            SAXReader saxreader = new SAXReader();
            try {
                /**
                StringWriter sw = new StringWriter();
                StringWriter sw = new StringWriter();
                Configuration cfg = new Configuration();
                cfg.setClassForTemplateLoading(getClass(), "/");
                cfg.setObjectWrapper(new DefaultObjectWrapper());
                Template view_tl = cfg.getTemplate("view.ftl");
                Map map = new HashMap();
                map.put("user", "@QUNIMADE@");
                map.put("name", name);
                map.put("package", packageName);
                view_tl.process(map, sw);
                content = sw.toString();
                 */
                Document doc_pom = saxreader.read(getClass().getResourceAsStream(coreInit + pomFile));
                Document doc_jetty = saxreader.read(getClass().getResourceAsStream(coreInit + jettyEnvFile));
                Element root = doc_pom.getRootElement();
                Element groupId_e = root.element("groupId");
                Element name_e = root.element("name");
                Element artifactId_e = root.element("artifactId");
                Element version_e = root.element("version");

                if (name_e != null) {
                    name_e.setText(name);
                }
                if (artifactId_e != null) {
                    artifactId_e.setText(name);
                }
                if (groupId_e != null) {
                    groupId_e.setText(packageName);
                }
                if (version_e != null) {
                    version_e.setText(version);
                }
                File pf = new File(f, pomFile);
                File jf = new File(f, jettyEnvFile);

                FileWriter jetty_w = null;
                FileWriter pom_w = null;
                try {
                    jetty_w = new FileWriter(jf);
                    pom_w = new FileWriter(pf);

                    jetty_w.write(doc_jetty.asXML());
                    pom_w.write(doc_pom.asXML());
                } catch (IOException e) {
                    throw new MojoExecutionException("Error creating file: " + pf + " or " + jf, e);
                } finally {
                    if (jetty_w != null) {
                        try {
                            jetty_w.close();
                        } catch (IOException e) {
                            // ignore
                        }
                    }
                    if (pom_w != null) {
                        try {
                            pom_w.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getPackage() {
        String packageName = null;
        while (true) {
            try {
                System.out.print("Package Name:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                packageName = reader.readLine();
                //TODO
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return packageName;
    }
}
