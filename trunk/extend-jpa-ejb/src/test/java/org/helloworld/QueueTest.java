package org.helloworld;

import hellojms.queue.TextListener;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-1-12
 * Time: 10:34:23
 * To change this template use File | Settings | File Templates.
 */
public class QueueTest {
    private ApplicationContext ctx;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml"});
    }

    @Test
    public void testPool() {
        // waiting...
        waiting();
    }

//    @Test

    public void testLook() {
        Queue q = (Queue) ctx.getBean("queue");
        QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.getBean("connectionFactory");
        QueueConnection qc = null;
        QueueSession qs = null;
        QueueReceiver qr = null;
        try {
            qc = qcf.createQueueConnection();
            qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            qr = qs.createReceiver(q);
            qr.setMessageListener(new TextListener());
            qc.start();
            // waiting...
            waiting();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                qs.close();
                qr.close();
                qc.stop();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void waiting() {
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
    }
}
