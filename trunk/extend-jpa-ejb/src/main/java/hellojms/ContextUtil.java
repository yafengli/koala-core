package hellojms;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class ContextUtil {

    public static final String qName = new String("dynamicQueues/helloq");
    public static final String tName = new String("dynamicTopics/hellot");
    private static Properties p = new Properties();
    private static InitialContext jc;

    static {
        try {
            p.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            p.setProperty(Context.PROVIDER_URL, "tcp://221.231.148.247:8976");
            jc = new InitialContext(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final InitialContext lookContext() {
        return jc;
    }
}
