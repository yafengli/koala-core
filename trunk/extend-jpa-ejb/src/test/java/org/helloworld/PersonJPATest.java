package org.helloworld;

import helloejb.Hello;
import hellojpa.Message;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.rmi.PortableRemoteObject;

import org.apache.openjpa.jdbc.conf.JDBCConfiguration;
import org.apache.openjpa.jdbc.conf.JDBCConfigurationImpl;
import org.apache.openjpa.jdbc.meta.MappingTool;
import org.apache.openjpa.lib.util.Options;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Administrator
 */
public class PersonJPATest {
	private EntityManagerFactory factory;
	private EntityManager em;

	@Before
	public void init() {
		JDBCConfiguration conf = new JDBCConfigurationImpl();
		try {
			String dir = "F:/TestProjects/helloworld-jpa/src/main/java/";
			MappingTool.run(conf, new String[] {
					dir + "org/helloworld/Person.java",
					dir + "hellojpa/Message.java" }, Options.EMPTY);
			factory = Persistence.createEntityManagerFactory("hellojpa", System
					.getProperties());
			em = factory.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	private void testJpa() {

		Message m = new Message();
		m.setCreated(new Date());
		m.setMessage("Hello World");
		m.setId(System.currentTimeMillis());
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		em.close();

		em = factory.createEntityManager();
		Person p = new Person();
		p.setName(String.valueOf(System.currentTimeMillis()));
		p.setAddress(p.getName());
		p.setMessage(m);
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();

		em = factory.createEntityManager();
		Query query = em.createQuery("select p from Person as p");
		List<Person> items = query.getResultList();
		for (Person item : items) {
			System.out.printf("[%s,%s,%s]/n", item.getName(),
					item.getAddress(), item.getMessage());
		}
		em.close();
		factory.close();
	}

	@Test
	private void testEjb() {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.RemoteInitialContextFactory");
		props.put(Context.PROVIDER_URL, "ejbd://127.0.0.1:4201");
		try {
			Context ctx = new InitialContext(props);
			Object ref = ctx.lookup("HelloWorldRemote");
			Hello h = (Hello) PortableRemoteObject.narrow(ref, Hello.class);
			h.sayHello("Fuck");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
