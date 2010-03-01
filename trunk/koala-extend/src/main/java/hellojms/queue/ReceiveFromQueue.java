package hellojms.queue;

import hellojms.ContextUtil;

import java.io.*;
import javax.jms.*;

public class ReceiveFromQueue {


    public static void doReceive(String qName) {
        Message msg;
        TextMessage txtMsg;

        QueueConnection qc = null;
        try {

            QueueConnectionFactory qcf = (QueueConnectionFactory) ContextUtil.lookContext()
                    .lookup("QueueConnectionFactory");
            Queue q = (Queue) ContextUtil.lookContext().lookup(qName);
            qc = qcf.createQueueConnection();
            QueueSession qs = qc.createQueueSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            QueueReceiver qr = qs.createReceiver(q);
            qc.start();
            System.out.println("begin receive messge from " + qName + "...");
            msg = qr.receive(); // 如果不加间隔参数，会一直等着，知道消息到来。
            while (msg != null) {
                if (msg instanceof TextMessage) {
                    txtMsg = (TextMessage) msg;
                    System.out.println("Receive Msg from " + qName + " : "
                            + txtMsg.getText());
                }
                msg = qr.receive(1000);
            }
            System.out.println("no message available!");
            qr.close();
            qs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (qc != null) {
                try {
                    qc.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    public static void doListen(String qName) {
        QueueConnection qc = null;
        try {

            QueueConnectionFactory qcf = (QueueConnectionFactory) ContextUtil.lookContext()
                    .lookup("QueueConnectionFactory");
            Queue q = (Queue) ContextUtil.lookContext().lookup(ContextUtil.qName);
            qc = qcf.createQueueConnection();
            QueueSession qs = qc.createQueueSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            QueueReceiver qr = qs.createReceiver(q);
            qc.start();

            System.out.println("begin listen to messge from " + qName + "...");
            TextListener tListener = new TextListener();
            qr.setMessageListener(tListener);
            qc.start();
            /*quit*/
            System.out.println("Enter 'q' and press <return> to exit ");
            InputStreamReader isr = new InputStreamReader(System.in);

            char response = '\0';
            while (!((response == 'q') || (response == 'Q'))) {
                try {
                    response = (char) isr.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End listening!");

            qr.close();
            qs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (qc != null) {
                try {
                    qc.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
//        doReceive(ContextUtil.qName); //通过QueueReceiver.receive()读消息
        doListen(ContextUtil.qName); // 通过消息监听器读消息
    }
}