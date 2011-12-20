package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class MqHelper<T> implements MessageListener, ExceptionListener {

    public static final String brokeUrl = "tcp://192.168.0.97:61616";
    public static final String text = "Fuck fuck fuck you!";
    private static final ScheduledExecutorService SES = Executors.newScheduledThreadPool(8);


    private static final ThreadLocal<Session> THREAD_LOCAL = new ThreadLocal<Session>();


    private Long timeout = 6000L;
    private final MessageResponse<T> response = new MessageResponse<T>() {
        private T t = null;

        @Override
        public T getResponse() {
            return t;
        }

        @Override
        public void setResponse(T t) {
            this.t = t;
        }
    };

    private ActiveMQConnectionFactory connectionFactory;

    public MqHelper() {
        try {
            connectionFactory = new ActiveMQConnectionFactory(brokeUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send() throws Exception {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("TEST.FOO");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        producer.send(session.createTextMessage(text));
        producer.close();
        connection.close();
    }

    private void recieve() throws Exception {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("TEST.FOO");
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(this);
        consumer.close();
        connection.close();
    }


    public static void main(String[] args) throws Exception {
        MqHelper<String> helper = new MqHelper<String>();
        MessageResponse<String> response = helper.call();
        System.out.println("hello:" + response.getResponse());
    }

    public MessageResponse<T> call() throws Exception {
        long start = System.currentTimeMillis();
        send();
        long send = System.currentTimeMillis();
        recieve();
        long recieve = System.currentTimeMillis();
        while (true) {
            try {
                if (response.getResponse() != null) {
                    break;
                }
                long end = System.currentTimeMillis();
                if (end - start > timeout)
                    break;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("SEND:%s. REVIEVE:%s WAIT:%s\n", send - start, recieve - send, end - recieve);
        return response;
    }

    @Override
    public void onException(JMSException e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            response.setResponse((T) textMessage.getText());
            System.out.println("Received: " + text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}