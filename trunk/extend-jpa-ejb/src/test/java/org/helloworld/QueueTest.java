package org.helloworld;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Created by IntelliJ IDEA. User: Administrator Date: 2010-1-12 Time: 10:34:23
 * To change this template use File | Settings | File Templates.
 */
public class QueueTest {
	private ApplicationContext ctx;
	private JmsTemplate jmsTemplate;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/applicationContext.xml" });
		jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
	}

	@Test
	public void testLook() {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage tm = session.createTextMessage();
				tm.setText("Fuck !");
				return tm;
			}
		});
	}
}
