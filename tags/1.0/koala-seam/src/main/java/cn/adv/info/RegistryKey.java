package cn.adv.info;

import java.io.File;

import javax.servlet.ServletContext;

public class RegistryKey {
	public static final String A_ID = "_a_count_hash";
	public static final String L_ID = "_a_count_log";
	public static final String S_ID = "_s_search";
	public static final String S_ID_DESC = "_s_search_desc";
	public static final String P_ID = "_a_count_props";
	private static File logfile;
	private static File storefile;
	private static File keyfile;

	public static File getLogfile(ServletContext sc) {
		if (logfile == null) {
			String path = sc.getInitParameter("cd.adv.STORE_FILE");
			File apath = new File(sc.getRealPath(path));
			logfile = new File(apath.getParentFile(), "logfile");
		}
		return logfile;
	}

	public static File getStorefile(ServletContext sc) {
		if (storefile == null) {
			String path = sc.getInitParameter("cd.adv.STORE_FILE");
			String apath = sc.getRealPath(path);
			storefile = new File(apath);
		}
		return storefile;
	}
	public static File getKeyfile(ServletContext sc) {
		if (keyfile == null) {
			String path = sc.getInitParameter("cd.adv.ipmast.CONF_FILE");
			String apath = sc.getRealPath(path);
			keyfile = new File(apath);
		}
		return keyfile;
	}

	public static String getSnappath(ServletContext sc) {
		String path = sc.getInitParameter("cd.adv.STORE_FILE");
		File apath = new File(sc.getRealPath(path));
		return apath.getParentFile().getAbsolutePath();
	}
}
