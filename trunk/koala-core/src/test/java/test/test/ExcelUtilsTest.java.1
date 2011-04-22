package test.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.koala.util.ExcelUtils;

public class ExcelUtilsTest {

	// @Test
	public void jsf() {
		try {
			// 获得JSF上下文环境
			HttpServletResponse resp = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletOutputStream out = resp.getOutputStream();

			resp.setHeader("Content-disposition",
					"attachment; filename=hello.xls");
			// resp.setContentLength((int) exportFile.length());
			resp.setContentType("application/x-download");
			// resp.setContentType("application/vnd.ms-excel");
			ExcelUtils eu = new ExcelUtils();
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(new Object[] { "1", "2", "3" });
			list.add(new Object[] { "11", "22", "33" });
			list.add(new Object[] { "111", "222", "333" });
			String[] headNames = new String[] { "one", "two", "three" };
			eu.outputExcel("Hello", list, headNames, out);

		} catch (IOException e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().responseComplete();
	}

	@Test
	public void one() {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("f:/hello1.xls");
			ExcelUtils eu = new ExcelUtils();
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(new Object[] { "1", "2", "3" });
			list.add(new Object[] { "11", "22", "33" });
			list.add(new Object[] { "111", "222", "333" });
			String[] headNames = new String[] { "one", "two", "three" };
			eu.outputExcel("Hello", list, headNames, out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void twos() {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("f:/hello2.xls");
			ExcelUtils eu = new ExcelUtils();
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(new Object[] { "1", "2", "3" });
			list.add(new Object[] { "11", "22", "33" });
			list.add(new Object[] { "111", "222", "333" });
			String[] headNames = new String[] { "one", "two", "three" };

			String[] sheetNames = new String[] { "hello", "world" };
			HashMap<String, List<Object[]>> hmsl = new HashMap<String, List<Object[]>>();
			hmsl.put("hello", list);
			hmsl.put("world", list);
			HashMap<String, String[]> hmss = new HashMap<String, String[]>();
			hmss.put("hello", headNames);
			hmss.put("world", headNames);

			eu.outputExcel(sheetNames, hmsl, hmss, out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
