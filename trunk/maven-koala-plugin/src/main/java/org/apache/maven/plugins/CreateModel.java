package org.apache.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        String packageName = "cn.demo";

        File srcDir = new File("");

        if (!srcDir.exists()) {
            srcDir.mkdirs();
        }

        File touch = new File(srcDir, "touch.txt");

        FileWriter w = null;
        try {
            w = new FileWriter(touch);
            w.write(name);
            w.write("\ntouch.txt");
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
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
