package org.helloworld;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.junit.Test;

public class FileTest {

	@Test
	public void testWriter() throws Exception {
		BufferedWriter writer = null;
		BufferedOutputStream output = null;
		try {
			File f = new File("F:/tmp/test1/test1/1.txt");
			if (!f.getParentFile().exists() && !f.getParentFile().mkdirs()) {
				System.err.printf("The [%s] directory can't be build.", f
						.getParentFile().getAbsolutePath());
			}
			writer = new BufferedWriter(new FileWriter(f));

			writer.write("Hello World,the file is one!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		try {
			File f = new File("F:/tmp/test2/test2/2.txt");
			if (!f.getParentFile().exists() && !f.getParentFile().mkdirs()) {
				System.err.printf("The [%s] directory can't be build.", f
						.getParentFile().getAbsolutePath());
			}
			output = new BufferedOutputStream(new FileOutputStream(f));
			output.write("Hello World,the file is one!".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
		}

	}
}
