package jactor;

import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 *
 * @author phoenixup
 */
public class Actor1 extends JLPCActor {

    public Actor1(Mailbox mailbox) {
        super(mailbox);
    }

    @Override
    protected void processRequest(Object o, RP rp) throws Exception {
        System.out.printf("@actor1:%s\n", o.toString());
        rp.processResponse("Hello World!");
    }
}
