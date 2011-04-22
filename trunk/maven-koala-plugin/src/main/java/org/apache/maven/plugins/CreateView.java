package org.apache.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.plugins.util.TemplateProcess;

/**
 * @goal create-view
 * @requiresProject true
 * @phase process-sources
 */
public class CreateView
        extends AbstractMojo {

    /**
     * @parameter expression="${cp.view.name}"
     * @required
     */
    private String name;

    public void execute()
            throws MojoExecutionException {

        String currentDir = System.getProperty("user.dir");
        Properties ps = TemplateProcess.factory(new File(currentDir, "environments/config.properties"));

        /**/
        String webDir = ps.getProperty(PropertiesDefination.WEB_DIR);
        Map map = new HashMap();
        map.put("user", "@QUNIMADE@");
        map.put("name", name);

        /**/
        File templateDir = new File(currentDir, ps.getProperty(PropertiesDefination.TEMPLATE_DIR));
        String templateName = ps.getProperty(PropertiesDefination.VIEW_PREFIX);
        File sourceFile = new File(webDir, name + ".jsp");
        /**/
        TemplateProcess.factory(templateDir, templateName, sourceFile, map);
    }
}
