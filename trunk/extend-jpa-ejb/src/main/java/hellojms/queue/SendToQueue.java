package hellojms.queue;

import hellojms.ContextUtil;

import javax.jms.*;


public class SendToQueue {
    public static void main(String[] args) {
        final int msgCount = 3;

        QueueConnection qc = null;
        try {
            QueueConnectionFactory qcf = (QueueConnectionFactory) ContextUtil.lookContext().lookup("ConnectionFactory");
            Queue q = (Queue) ContextUtil.lookContext().lookup(ContextUtil.qName);
            qc = qcf.createQueueConnection();
            QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            qs.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            });

            QueueSender qSender = qs.createSender(q);

            TextMessage msg = qs.createTextMessage();
            for (int i = 0; i < msgCount; i++) {
                msg.setText("Welcome number " + System.currentTimeMillis());
                qSender.send(msg);
                System.out.println("Send Message To " + ContextUtil.qName + " : " + msg.getText() + "\n");
            }
            qSender.close();
            qs.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            if (qc != null) {
                try {
                    qc.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}

