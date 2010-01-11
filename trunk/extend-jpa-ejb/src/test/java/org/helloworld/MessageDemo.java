package org.helloworld;

import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-1-11
 * Time: 13:05:33
 * To change this template use File | Settings | File Templates.
 */
public class MessageDemo {

    public static void main(String[] args) {
        Message msg;
        TextMessage tmsg;
        QueueConnection qc = null;
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://221.231.148.247:8976");
            InitialContext ict = new InitialContext(props);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
