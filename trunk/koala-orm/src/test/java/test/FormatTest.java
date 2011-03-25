package test;

import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatTest {

	@Test
	public void format() {
		DecimalFormat df = new DecimalFormat("#{3}-##");
		System.out.println(df.format(1000223000333.00));
	}
}
