package hellothread;

/**
 * Created by IntelliJ IDEA.
 * User: phoenixup
 * Date: 2010-8-20
 * Time: 9:43:23
 * To change this template use File | Settings | File Templates.
 */
public class LocalBean {
    private static final ThreadLocal<String> tlStr = new ThreadLocal<String>();

    public static String look() {
        if (tlStr.get() == null) {
            tlStr.set(String.valueOf(System.currentTimeMillis()));
        }
        return tlStr.get();
    }

    public static void remove() {
        if (tlStr.get() == null) {
            System.err.println("The tlStr is null.");
        } else {
            tlStr.remove();
            System.err.println("The tlStr is remove.");
        }
    }

}
