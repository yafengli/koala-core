package org.apache.maven.plugins;

import java.io.File;

public interface TemplateCreate {

	public File getTemplateFile();

	public File getSourceFile();

	public String getContent();

}
