package jactor;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.lpc.Request;

/**
 *
 * @author phoenixup
 */
public class GreeterImpl extends Request<String, Greeter> {

    public static final GreeterImpl Instance = new GreeterImpl();

    private GreeterImpl() {
    }

    @Override
    public boolean isTargetType(Actor actor) {
        return actor instanceof Actor2;
    }
}
