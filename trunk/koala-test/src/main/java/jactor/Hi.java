package jactor;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.lpc.Request;

/**
 *
 * @author phoenixup
 */
public class Hi extends Request<String, Actor1> {

    public static final Hi Instance = new Hi();

    private Hi() {
    }

    @Override
    public boolean isTargetType(Actor actor) {
        return actor instanceof Actor1;
    }
}
