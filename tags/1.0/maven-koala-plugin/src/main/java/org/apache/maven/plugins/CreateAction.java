package org.apache.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.plugins.util.TemplateProcess;

/**
 * @goal create-action
 * @requiresProject true
 * @phase process-sources
 */
public class CreateAction
        extends AbstractMojo {

    /**
     * @parameter expression="${cp.action.name}"
     * @required
     */
    private String name;

    public void execute()
            throws MojoExecutionException {

        String currentDir = System.getProperty("user.dir");
        Properties ps = TemplateProcess.factory(new File(currentDir, "environments/config.properties"));

        String webDir = ps.getProperty(PropertiesDefination.WEB_DIR);
        String packageName = ps.getProperty(PropertiesDefination.PACKAGE_NAME);

        Map map = new HashMap();
        map.put("user", "@QUNIMADE@");
        map.put("name", name);
        map.put("package", packageName);

        /**/
        File templateFile = new File(currentDir, ps.getProperty(PropertiesDefination.TEMPLATE_DIR));
        String templateName = ps.getProperty(PropertiesDefination.VIEW_PREFIX);
        File sourceFile = new File(webDir, name + ".java");
        /**/
        TemplateProcess.factory(templateFile, templateName, sourceFile, map);
    }
}
