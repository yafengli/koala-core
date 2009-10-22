package org.apache.maven.plugins;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @goal create-model
 * @requiresProject true
 * @phase process-sources
 */
public class CreateModel
        extends AbstractMojo {

    /**
     * @parameter expression="${cp.model.name}"
     * @required
     */
    private String name;

    public void execute()
            throws MojoExecutionException {

        String currentDir = System.getProperty("user.dir");
        Properties ps = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(new File(currentDir, "environments/config.properties"));
            ps.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String srcDir = ps.getProperty(PropertiesDefination.SOURCE_DIR);
        String packageName = ps.getProperty(PropertiesDefination.PACKAGE_NAME);



        StringBuilder builder = new StringBuilder();
        String[] strs = packageName.split("\\.");
        for (int i = 0; i < strs.length; i++) {
            builder.append(strs[i]);
            builder.append("/");
        }
        File packageDir = new File(srcDir, builder.toString());
        if (!packageDir.mkdirs()) {
            System.err.println("The directory is not build.[" + packageDir.getAbsolutePath() + "]");
        }

        File sourceFile = new File(packageDir, name.substring(0, 1).toUpperCase() + name.substring(1) + ".java");
        FileWriter w = null;
        try {
            w = new FileWriter(sourceFile);
            StringWriter sw = new StringWriter();
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(currentDir, ps.getProperty(PropertiesDefination.TEMPLATE_DIR)));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template view_tl = cfg.getTemplate(ps.getProperty(PropertiesDefination.MODEL_PREFIX));
            Map map = new HashMap();
            map.put("user", "@QUNIMADE@");
            map.put("name", name);
            map.put("package", packageName);
            view_tl.process(map, sw);
            w.write(sw.toString());
        } catch (Exception e) {
            throw new MojoExecutionException("Error creating file " + sourceFile.getAbsolutePath(), e);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
