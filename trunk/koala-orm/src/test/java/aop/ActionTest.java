package aop;

import org.koala.orm.aop.Action;
import org.testng.annotations.Test;

/**
 * @author phoenixup
 */
public class ActionTest {

    @Test
    public void testAction() {
        Action action = new Action();
        action.action();
        String fk=action.lookName("FUCK!");
    }
}
