package activemq;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-20
 * Time: 下午6:10
 * To change this template use File | Settings | File Templates.
 */
public class MqLis implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Recieve:" + message);
    }
}
