package hellojms.queue;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * User: YaFengLi
 * Date: 2010-1-11
 * Time: 14:28:00 
 */
public class TextListener implements MessageListener {
    public void onMessage(Message message) {
        try {
            message.acknowledge();
            if (message instanceof TextMessage) {

                TextMessage textMsg = (TextMessage) message;
                System.out.printf("[recieve:%s]\n", textMsg.getText());
            }
            else{
                System.out.printf("[recieve:%s]\n", message.getJMSMessageID());
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
