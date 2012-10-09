package test;

import jactor.Actor1;
import jactor.Actor2;
import jactor.GreeterImpl;
import jactor.Hi;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jactor.JAMailboxFactory;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.MailboxFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author phoenixup
 */
public class JActorTest {

    private static MailboxFactory mbfaFactory;

    @BeforeClass
    public static void init() {
        mbfaFactory = JAMailboxFactory.newMailboxFactory(10);
    }

    @AfterClass
    public static void after() {
        mbfaFactory.close();
    }

//    @Test
    public void testActor1() {
        try {
            Mailbox mb = mbfaFactory.createMailbox();

            Actor1 actor1 = new Actor1(mb);
            JAFuture future = new JAFuture();
            String result = Hi.Instance.send(future, actor1);
            System.out.printf("$result:%s\n", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testActor2() {
        try {
            Mailbox mb = mbfaFactory.createMailbox();
            Actor1 actor1 = new Actor1(mb);
            Actor2 actor2 = new Actor2(mb);
            actor2.setParent(actor1);
                        
            JAFuture future = new JAFuture();
            String result = GreeterImpl.Instance.send(future, actor2);
            System.out.printf("$result:%s\n", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
