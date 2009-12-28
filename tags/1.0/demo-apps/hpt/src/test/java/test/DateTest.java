package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class DateTest {

	@Test
	public void testDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		try {
			System.out.printf("[%s]\n",df.parse("1971-01-01 00:00:01").getTime());
			System.out.printf("[%s]\n",df.parse("2000-00-00 00:00:00").getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
