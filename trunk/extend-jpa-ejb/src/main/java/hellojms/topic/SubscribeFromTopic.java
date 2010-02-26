package hellojms.topic;

import hellojms.ContextUtil;
import hellojms.queue.TextListener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.*;
import javax.naming.*;

public class SubscribeFromTopic {
    public static void doListen(String tName) {
        char response = '\0';

        TopicConnection tc = null;
        try {

            TopicConnectionFactory tcf = (TopicConnectionFactory) ContextUtil.lookContext().lookup("TopicConnectionFactory");
            Topic t = (Topic) ContextUtil.lookContext().lookup(tName);
            tc = tcf.createTopicConnection();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber tSubscriber = ts.createSubscriber(t);

            System.out.println("begin listen to messge from " + tName + "...");
            TextListener tListener = new TextListener();
            tSubscriber.setMessageListener(tListener);
            tc.start();
            System.out.println("Enter 'q' and press <return> to exit ");
            InputStreamReader isr = new InputStreamReader(System.in);
            while (!((response == 'q') || (response == 'Q'))) {
                try {
                    response = (char) isr.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End listening!");

            tSubscriber.close();
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

    public static void doSubscribe(String tName) {
        Message msg;
        TextMessage txtMsg;
        TopicConnection tc = null;
        try {

            TopicConnectionFactory tcf = (TopicConnectionFactory) ContextUtil.lookContext().lookup("TopicConnectionFactory");
            Topic t = (Topic) ContextUtil.lookContext().lookup(tName);
            tc = tcf.createTopicConnection();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber tSubscriber = ts.createSubscriber(t);

            System.out.println("begin listen to messge from " + tName + "...");

            System.out.println("begin receive messge from " + tName + "...");
            msg = tSubscriber.receive(1000);
            while (msg != null) {
                if (msg instanceof TextMessage) {
                    txtMsg = (TextMessage) msg;
                    System.out.println("Receive Msg from " + tName + " : "
                            + txtMsg.getText());
                }
                msg = tSubscriber.receive(1000);
            }
            System.out.println("no message available!");

            tSubscriber.close();
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

    public static void main(String[] args) {
        // doSubscribe(tName); //通过receive()来接收消息
        doListen(ContextUtil.tName); //通过消息监听器接收消息
    }
}