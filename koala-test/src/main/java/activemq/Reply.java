package activemq;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.jms.*;
import java.util.Properties;

public class Reply implements MessageListener {

    private QueueConnection conn;
    private QueueSession session;

    public void onMessage(Message msg) {
        try {
            if (msg instanceof TextMessage) {
                TextMessage requestMessage = (TextMessage) msg;
                System.out.println("Recived request");
                System.out.println("\t Time:      " + System.currentTimeMillis() + "ms");
                System.out.println("\t Message ID:" + requestMessage.getJMSMessageID());
                System.out.println("\t Correl. ID:" + requestMessage.getJMSCorrelationID());
                System.out.println("\t reply to: " + requestMessage.getJMSReplyTo());
                System.out.println("\t contents:" + requestMessage.getText());

                Destination replyDestination = msg.getJMSReplyTo();
                Properties props = new Properties();
                props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
                props.setProperty(Context.PROVIDER_URL, "localhost:1099");
                props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
                InitialContext ctx = new InitialContext(props);

                QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
                conn = factory.createQueueConnection();
                session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(replyDestination);
                TextMessage replyMessage = session.createTextMessage();
                replyMessage.setText("message have receive");
                replyMessage.setJMSCorrelationID(requestMessage.getJMSMessageID());
                producer.send(replyMessage);

                System.out.println("sent reply");
                System.out.println("\t Time:      " + System.currentTimeMillis() + "ms");
                System.out.println("\t Message ID:" + replyMessage.getJMSMessageID());
                System.out.println("\t Correl. ID:" + replyMessage.getJMSCorrelationID());
                System.out.println("\t reply to: " + replyMessage.getJMSReplyTo());
                System.out.println("\t contents:" + replyMessage.getText());
            } else {
                System.out.println("sent to invalid queue");
            }
        } catch (Exception e) {
            System.out.println(e.toString() + "Reply problems");
        }
    }

}