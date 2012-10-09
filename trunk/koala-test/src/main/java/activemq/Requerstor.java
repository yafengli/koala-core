package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.jms.Queue;

import java.util.*;

import javax.naming.*;

public class Requerstor {
    public static final String BROKER_URL = "tcp://192.168.0.97:61616";

    /**
     * @param args
     */
    private QueueSession session = null;
    private QueueConnection conn = null;
    private MessageProducer producer = null;
    private MessageProducer invalidProducer = null;
    private Destination replyQueue = null;
    private MessageConsumer replyConsumer;

    public void send() throws JMSException {
        TextMessage requestMessage = session.createTextMessage();
        requestMessage.setText("Hello world");
        requestMessage.setJMSReplyTo(replyQueue);
        producer.send(requestMessage);
        System.out.println("sent request ");
        System.out.println("\t Time:      " + System.currentTimeMillis() + "ms");
        System.out.println("\t Message ID:" + requestMessage.getJMSMessageID());
        System.out.println("\t Correl. ID:" + requestMessage.getJMSCorrelationID());
        System.out.println("\t reply to: " + requestMessage.getJMSReplyTo());
        System.out.println("\t contents:" + requestMessage.getText());
    }

    public void receive() throws JMSException {
        Message msg = replyConsumer.receive();
        if (msg instanceof TextMessage) {
            TextMessage replyMessage = (TextMessage) msg;
            System.out.println("recive reply ");
            System.out.println("\t Time:      " + System.currentTimeMillis() + "ms");
            System.out.println("\t Message ID:" + replyMessage.getJMSMessageID());
            System.out.println("\t Correl. ID:" + replyMessage.getJMSCorrelationID());
            System.out.println("\t reply to: " + replyMessage.getJMSReplyTo());
            System.out.println("\t contents:" + replyMessage.getText());
        } else {
            System.out.println("Invalid message detected");
            System.out.println("\t Type:   " + msg.getClass().getName());
            System.out.println("\t Time:   " + System.currentTimeMillis());
            System.out.println("\t Message ID:" + msg.getJMSMessageID());
            System.out.println("\t Correl. ID:" + msg.getJMSCorrelationID());
            System.out.println("\t reply to: " + msg.getJMSReplyTo());

            msg.setJMSCorrelationID(msg.getJMSMessageID());
            invalidProducer.send(msg);
            System.out.println("\t Type:   " + msg.getClass().getName());
            System.out.println("\t Time:   " + System.currentTimeMillis());
            System.out.println("\t Message ID:" + msg.getJMSMessageID());
            System.out.println("\t Correl. ID:" + msg.getJMSCorrelationID());
            System.out.println("\t reply to: " + msg.getJMSReplyTo());
        }
    }

    protected void init() {
        try {

            ActiveMQConnectionFactory factory = new
                    ActiveMQConnectionFactory(BROKER_URL);
            conn = factory.createQueueConnection();
            session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            Destination requestDestination = session.createQueue("queue/requestQueue");
            Destination invalidDestination = session.createQueue("queue/invalidQueue");
            replyQueue = session.createQueue("queue/replyQueue");
            producer = session.createProducer(requestDestination);
            invalidProducer = session.createProducer(invalidDestination);
            replyConsumer = session.createConsumer(replyQueue);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        Requerstor r = new Requerstor();
        try {
            r.init();
            r.send();
        } catch (Exception e) {
            System.out.println(e.toString() + "    Requerstor problems");
        }
    }

}