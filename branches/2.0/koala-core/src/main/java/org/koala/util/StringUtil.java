package org.koala.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: liyafeng Date: 2007-8-15 Time: 14:45:04 To
 * change this template use File | Settings | File Templates.
 */
public class StringUtil {
	private StringUtil() {

	}

	private static StringUtil instance = null;

	public static StringUtil getStringUtil() {
		if (instance == null)
			instance = new StringUtil();
		return instance;
	}

	public synchronized String makeIp(String zone) {
		StringBuffer buffer = new StringBuffer();
		if (zone != null) {
			int length = zone.trim().length();
			if (length >= 3) {
				buffer.append("127.");
				buffer.append(zone.substring(length - 3, length - 2) + ".");
				buffer.append(zone.substring(length - 2, length - 1) + ".");
				buffer.append(zone.substring(length - 1, length));
			}
		}
		return buffer.toString();
	}

	public synchronized List<Integer> getIntTimes(String datestr) {
		String[] ds = datestr.split(" ");
		List<Integer> li = new ArrayList<Integer>();
		if (ds.length == 1) {
			String[] ss = ds[0].split("-");
			for (String s : ss) {
				li.add(Integer.valueOf(s));
			}
		} else if (ds.length == 2) {
			String[] s1 = ds[0].split("-");
			for (String s : s1) {
				li.add(Integer.valueOf(s));
			}
			String[] s2 = ds[1].split(":");
			for (String s : s2) {
				li.add(Integer.valueOf(s));
			}
			for (int i = li.size(); i < 6; i++) {
				li.add(0);
			}
		}
		return li;
	}

	public synchronized int getMonth(Calendar beforc, Calendar afterc) {
		int byear = beforc.get(Calendar.YEAR);
		int ayear = afterc.get(Calendar.YEAR);
		int bmonth = beforc.get(Calendar.MONTH);
		int amonth = afterc.get(Calendar.MONTH);
		return (ayear - byear) * 12 + (amonth - bmonth) + 1;
	}

	public synchronized String changeDateStr(String str) {
		List<Integer> cli = StringUtil.getStringUtil().getIntTimes(str);
		Calendar ct = new GregorianCalendar(cli.get(0), cli.get(1) - 1, cli
				.get(2), cli.get(3), cli.get(4), cli.get(5));
		int year = ct.get(Calendar.YEAR);
		int month = ct.get(Calendar.MONTH);
		Calendar cal = Calendar.getInstance();
		int cyear = cal.get(Calendar.YEAR);
		int cmonth = cal.get(Calendar.MONTH);
		if (year == cyear && month == cmonth) {
			return FormatUtil.getFormatUtil().getTime(ct.getTime(),
					FormatUtil._yyyy_MM_dd_HH_mm_ss);
		} else {
			return FormatUtil.getFormatUtil().getTime(cal.getTime(),
					FormatUtil._yyyy_MM_dd_HH_mm_ss);
		}
	}
}
