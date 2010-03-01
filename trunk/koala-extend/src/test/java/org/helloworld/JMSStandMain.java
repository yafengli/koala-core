package org.helloworld;

import java.util.Properties;

import javax.jms.*;
import javax.naming.*;

public class JMSStandMain {
    public static void main(String[] args) {
        final int msgCount;
        if ((args.length < 1) || (args.length > 2)) {
            System.out.println("Usage: java test.jms.PublishToTopic topicName [sendCount]");
            System.exit(1);
        }

        String tName = new String(args[0]);
        if (args.length == 2) {
            msgCount = (new Integer(args[1]).intValue());
        } else {
            msgCount = 10;
        }

        TopicConnection tc = null;
        try {
            Properties p = new Properties();
            p.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.jnp.interfaces.NamingContextFactory");
            p.setProperty(Context.PROVIDER_URL, "tcp://221.231.148.247:8976");

            InitialContext jc = new InitialContext(p);
            TopicConnectionFactory tcf = (TopicConnectionFactory) jc.lookup("hello");
            Topic t = (Topic) jc.lookup(tName);
            tc = tcf.createTopicConnection();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher tp = ts.createPublisher(t);
            TextMessage msg = ts.createTextMessage();
            for (int i = 0; i < msgCount; i++) {
                msg.setText("Welcome number " + (i + 1));
                tp.publish(msg);
                System.out.println("Publish Message To " + tName + " : " + msg.getText() + "\n");
            }
            tp.close();
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tc != null) {
                try {
                    tc.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}