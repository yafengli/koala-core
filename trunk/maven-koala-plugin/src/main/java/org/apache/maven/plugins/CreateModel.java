package org.apache.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Goal which touches a timestamp file.
 *
 * @goal create-model
 * @requiresProject true
 * @phase process-sources
 */
public class CreateModel
        extends AbstractMojo {

    /**
     * Location of the file.
     * @parameter
     *  expression="${core.dir}"
     * @required
     */
    private File outputDirectory;
    /**
     * @parameter
     *  expression="${core.name}"
     * @required
     */
    private String name;

    public void execute()
            throws MojoExecutionException {
        File f = outputDirectory;

        if (!f.exists()) {
            f.mkdirs();
        }

        File touch = new File(f, "touch.txt");

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