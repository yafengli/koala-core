package jactor;

import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 *
 * @author phoenixup
 */
public class Actor2 extends JLPCActor implements Greeter {

    public Actor2(Mailbox mlbx) {
        super(mlbx);
    }

    @Override
    protected void processRequest(Object o, final RP rp) throws Exception {
        Hi.Instance.send(this, getParent(), new RP<String>() {

            @Override
            public void processResponse(String r) throws Exception {
                System.out.printf("@actor2:%s\n",r);
                rp.processResponse(r+".fromActor2");
            }
        });
    }
}
