package org.koala.orm.aop;

/**
 *
 * @author phoenixup
 */
public class Action {

    public void action() {
        System.out.println("Your Action!");
    }

    public String lookName(String name) {
        return String.format("Hello %s!", name);
    }
}
