package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ChatServer {
    public static final String BROKER_URL = "tcp://192.168.0.97:61616";
    QueueConnection conn;
    Queue requestQueue;
    Queue responseQueue;
    QueueSession session;
    int age = 10;

    public ChatServer() throws Exception {
        ActiveMQConnectionFactory factory = new
                ActiveMQConnectionFactory(BROKER_URL);
        conn = factory.createQueueConnection();
        session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        requestQueue = session.createQueue("requestQueue");
        responseQueue = session.createQueue("responseQueue");
        try {
            session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            conn.start();//一般情况下，如果只是发送消息，而不接收不需要start() ;
            //但是这个发送消息的服务端需要处理客户端反馈回来的消息，所以start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void request() {
        try {
            MapMessage msg = session.createMapMessage();
            msg.setInt("age", (int) (this.age * Math.random() * 10));
            msg.setJMSReplyTo(responseQueue);
            // 注意这里，设置replyto 何处，如此，客户端便可将消息发送到responseQueue，
            // 然后下面可以从responseQueue 接收客户端返回的消息
            QueueSender sender = session.createSender(requestQueue);
            sender.send(msg);

            //    处理客户端反馈回来的消息
            String filter = "JMSCorrelationID='" + msg.getJMSMessageID() + "'";
            QueueReceiver receiver = session.createReceiver(responseQueue,
                    filter);
            TextMessage resMsg = (TextMessage) receiver.receive(20 * 1000);
            if (resMsg == null) {
                System.out.print("客户端超时，无法收到客户端返回的消息；");
            } else {
                System.out.println("客户端返回的信息是：" + resMsg.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        try {
            conn.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        ChatServer server = new ChatServer();
        server.request();
//        server.exit();
    }
}

