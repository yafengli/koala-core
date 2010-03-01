package hellojms.topic;

import hellojms.ContextUtil;

import javax.jms.*;

public class PublishToTopic {
    public static void main(String[] args) {
        final int msgCount = 4;

        TopicConnection tc = null;
        try {

            TopicConnectionFactory tcf = (TopicConnectionFactory) ContextUtil.lookContext().lookup("TopicConnectionFactory");
            Topic t = (Topic) ContextUtil.lookContext().lookup(ContextUtil.tName);
            tc = tcf.createTopicConnection();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher tp = ts.createPublisher(t);
            TextMessage msg = ts.createTextMessage();
            for (int i = 0; i < msgCount; i++) {
                msg.setText("Welcome number " + (i + 1));
                tp.publish(msg);
                System.out.println("Publish Message To " + ContextUtil.tName + " : " + msg.getText() + "\n");
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