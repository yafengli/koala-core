package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class RegularTest {

    public static final String STR = "Hello 23 World!";

    @Test
    public void testRegex() {

        Pattern pattern = Pattern.compile("(\\w+)\\s(\\d{1,3})");
        Matcher matcher = pattern.matcher(STR);
        while (matcher.find()) {
            int count = matcher.groupCount();
            System.out.printf("{size=%d}\n", count);
            for (int i = 1; i <= count; i++) {
                System.out.printf("[%s]\n", matcher.group(i));
            }
        }
    }
}
