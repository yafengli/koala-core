package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 */
public class DemoOk {
    public static final String brokeUrl = "tcp://192.168.0.97:61616";

    public static void main(String[] args) throws Exception {

        HelloWorldConsumer consumer = new HelloWorldConsumer();
        HelloWorldProducer producer = new HelloWorldProducer();
        thread(consumer, false);
        Thread.sleep(5000);
        thread(producer, false);
        synchronized (consumer) {
            consumer.wait(200);
        }
        consumer.close();
    }

    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }

    public static class HelloWorldProducer implements Runnable {
        public void run() {
            try {
                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);

                // Create a Connection
                Connection connection = connectionFactory.createConnection();
                connection.start();

                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("TEST.FOO");

                // Create a MessageProducer from the Session to the Topic or Queue
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);

                // Create a messages
                String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
                TextMessage message = session.createTextMessage(text);

                // Tell the producer to send the message
                System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
                producer.send(message);

                // Clean up
                session.close();
                connection.close();
                System.out.println("SENDED.");
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }
    }

    public static class HelloWorldConsumer implements Runnable, ExceptionListener {
        private Connection connection;

        public void run() {
            try {

                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);

                // Create a Connection
                connection = connectionFactory.createConnection();
                connection.start();

                connection.setExceptionListener(this);

                // Create a Session
                Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("TEST.FOO");

                // Create a MessageConsumer from the Session to the Topic or Queue
                MessageConsumer consumer = session.createConsumer(destination);

                //asyn
                consumer.setMessageListener(new TextMessageListener());

                // Wait for a message
//                waitForCompletion(consumer);

//                consumer.close();
//                session.close();
                session.rollback();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }

        public void close() throws Exception {
            connection.close();
        }

        public void waitForCompletion(MessageConsumer consumer) {
            while (true) {
                try {
                    Message message = consumer.receive(1000);
                    if (message == null) {
                        break;
                    } else if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        System.out.println("Text: " + text);
                    } else {
                        System.out.println("Message: " + message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void onException(JMSException ex) {
            System.out.println("JMS Exception occured.  Shutting down client.");
        }
    }

    public static class TextMessageListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            System.out.printf("#message:%s\n", message);
        }
    }
}
