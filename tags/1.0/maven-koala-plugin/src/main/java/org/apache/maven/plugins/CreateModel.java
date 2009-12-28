package org.apache.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.plugins.util.TemplateProcess;

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
        Properties ps = TemplateProcess.factory(new File(currentDir, "environments/config.properties"));
        
        String srcDir = ps.getProperty(PropertiesDefination.SOURCE_DIR);
        String packageName = ps.getProperty(PropertiesDefination.PACKAGE_NAME);

        /* look path the source */
        StringBuilder builder = new StringBuilder();
        String[] strs = packageName.split("\\.");
        for (int i = 0; i < strs.length; i++) {
            builder.append(strs[i]);
            builder.append("/");
        }
        File packageDir = new File(srcDir, builder.toString());
        
        /* params */
        Map map = new HashMap();
        map.put("user", "@QUNIMADE@");
        map.put("name", name);
        map.put("package", packageName);
        /* config */
        File templateDir = new File(currentDir, ps.getProperty(PropertiesDefination.TEMPLATE_DIR));
        String templateName = ps.getProperty(PropertiesDefination.MODEL_PREFIX);
        File sourceFile = new File(packageDir, name.substring(0, 1).toUpperCase() + name.substring(1) + ".java");
        /* process the template */
        TemplateProcess.factory(templateDir, templateName, sourceFile, map);
    }
}
