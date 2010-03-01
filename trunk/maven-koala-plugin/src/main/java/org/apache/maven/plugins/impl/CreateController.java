package org.apache.maven.plugins.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.TemplateCreate;
import org.apache.maven.plugins.util.PropertiesDefination;
import org.apache.maven.plugins.util.TemplateProcess;

/**
 * @goal controller
 * @requiresProject true
 * @phase process-sources
 */
public class CreateController
        extends AbstractMojo implements TemplateCreate{

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

	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public File getSourceFile() {
		// TODO Auto-generated method stub
		return null;
	}

	public File getTemplateFile() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
