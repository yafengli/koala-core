package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    public static final String brokeUrl = "tcp://192.168.0.97:61616";

    private static final ScheduledExecutorService SES = Executors.newScheduledThreadPool(8);

    public static void main(String[] args) throws Exception {
        SES.schedule(new HelloWorldProducer(), 0, TimeUnit.MILLISECONDS);
        SES.scheduleWithFixedDelay(new HelloWorldConsumer(), 0, 1000, TimeUnit.MILLISECONDS);
        Thread.sleep(20000);
    }

    public static void thread(Runnable runnable, long delaytime) {
        SES.schedule(runnable, delaytime, TimeUnit.MILLISECONDS);
    }

    public static class HelloWorldProducer implements Runnable {
        private Connection connection;

        public HelloWorldProducer() {
            if (connection == null) {
                try {
                    // Create a ConnectionFactory
                    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
                    // Create a Connection
                    connection = connectionFactory.createConnection();
                    connection.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            try {
                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("TEST.FOO");

                // Create a MessageProducer from the Session to the Topic or Queue
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

                // Create a messages
                String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
                TextMessage message = session.createTextMessage(text);

                // Tell the producer to send the message
                System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
                producer.send(message);
                // Clean up
                session.close();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }
    }

    public static class HelloWorldConsumer implements Runnable, ExceptionListener {
        private Connection connection;

        public HelloWorldConsumer() {
            if (connection == null) {
                try {
                    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
                    // Create a Connection
                    connection = connectionFactory.createConnection();


                    connection.setExceptionListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            try {
                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("TEST.FOO");

                // Create a MessageConsumer from the Session to the Topic or Queue
                MessageConsumer consumer = session.createConsumer(destination);
//                consumer.setMessageListener(new MqLis());
                connection.start();
                // Wait for a message
                Message message = consumer.receive(1000);

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                } else {
                    System.out.println("Received: " + message);
                }

                consumer.close();
                session.close();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }

        public synchronized void onException(JMSException ex) {
            System.out.println("JMS Exception occured.  Shutting down client.");
        }

    }
}