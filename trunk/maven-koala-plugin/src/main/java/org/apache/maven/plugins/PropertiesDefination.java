package org.apache.maven.plugins;

import java.util.Properties;

/**
 * Date: 2009-10-16
 * Time: 15:46:13
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class PropertiesDefination {
    public static final String PROJECT_NAME = "project.name";
    public static final String PACKAGE_NAME = "package.name";

    public static final String VIEW_PREFIX = "view.prefix";
    public static final String MODEL_PREFIX = "model.prefix";

    public static final String VERSION = "version";

    public static final String TEMPLATE_DIR = "template.dir";
    public static final String SOURCE_DIR = "src.dir";
    public static final String RESOURCE_DIR = "resource.dir";
    public static final String WEB_DIR = "web.dir";
    public static final String SETTING_DIR = "settings.dir";

    public static final String INIT_CORE_DIR = "/init/";
    public static final String INIT_POM_FILE = "pom.xml";
    public static final String INIT_JETTY_ENV_FILE = "jetty-env.xml";
    public static final String INIT_CONFIG_FILE = "config.properties";

    private static final Properties PTS;

    static {
        PTS = new Properties();
        try {
            PTS.load(PropertiesDefination.class.getResourceAsStream(INIT_CORE_DIR+INIT_CONFIG_FILE));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties load() {
        return PTS;
    }
}
