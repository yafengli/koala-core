package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ChatClient implements MessageListener {
    public static final String BROKER_URL = "tcp://192.168.0.97:61616";
    QueueConnection conn;
    Queue requestQueue;
    QueueSession session;

    public ChatClient() throws Exception {
        ActiveMQConnectionFactory factory = new
                ActiveMQConnectionFactory(BROKER_URL);
        conn = factory.createQueueConnection();
        session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            requestQueue = session.createQueue("requestQueue");
        try {
            session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            conn.start();
            QueueReceiver receiver = session.createReceiver(requestQueue);
            receiver.setMessageListener(this);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message msgObj) {
        System.out.println(msgObj.getClass());
        if (!(msgObj instanceof MapMessage)) {
            return;
        }
        MapMessage msg = (MapMessage) msgObj;
        try {
            int requestAge = msg.getInt("age");
            System.out.println(requestAge);
            TextMessage responseMsg = session.createTextMessage();
            responseMsg.setJMSCorrelationID(msg.getJMSMessageID());
            // 这里将responseMsg 的JMSCorrelationID 设成msg.getJMSMessageID ,
            // 因此，服务器端只需要处理responseMsg 中JMSCorrelationID==msg.getJMSMessageID
            // 的消息
            if (requestAge > 50) {
                responseMsg.setText("年龄大于50 ，不批准修改");
            } else {
                responseMsg.setText("请求通过");
            }
            Queue responseQueue = (Queue) msgObj.getJMSReplyTo();
            session.createSender(responseQueue).send(responseMsg); // 将responseMsg
            // 发送到responseQueue队列中，以便服务器端进行处理
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
        ChatClient client = new ChatClient();

    }
}
