package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class MessageSendReceiveAndReply {
    public static final String BROKER_URL = "tcp://192.168.0.97:61616";
    public static final String MESSAGE_QUEUE = "message.queue";
    public static final String REPLAY_QUEUE = "replay.queue";

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory factory = new
                ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        //消息发送到这个Queue
        Queue queue = new ActiveMQQueue(MESSAGE_QUEUE);
        //消息回复到这个Queue
        Queue replyQueue = new ActiveMQQueue(REPLAY_QUEUE);
        final Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        //创建一个消息，并设置它的JMSReplyTo为replyQueue。
        TextMessage message = session.createTextMessage("Andy");
        MessageProducer producer = session.createProducer(queue);
        System.out.println("###" + message.getJMSCorrelationID());
        producer.send(message);
        //消息的接收者
        MessageConsumer comsumer = session.createConsumer(queue);
        comsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message m) {
                try {
                    System.out.println("Message:" + ((TextMessage) m).getText());
                    //创建一个新的MessageProducer来发送一个回复消息。
                    MessageProducer producer =
                            session.createProducer(m.getJMSReplyTo());
                    producer.send(session.createTextMessage("Hello "
                            + ((TextMessage) m).getText()));
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //这个接收者用来接收回复的消息
        MessageConsumer comsumer2 =
                session.createConsumer(replyQueue);
        comsumer2.setMessageListener(new MessageListener() {
            public void onMessage(Message m) {
                try {
                    System.out.println("Replay:" + ((TextMessage) m).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        synchronized (comsumer2) {
            comsumer2.wait(2000);
        }
        connection.close();
    }
}