package cn.adv;

import cn.adv.interceptor.Logged;
import cn.adv.interceptor.LoggedAdd;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Context;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Name("helloworld")
@Logged
public class HelloWorld {
    private String id;

    @Logger
    private Log log;

    @In
    TestInit testInit;
    @In
    FacesMessages facesMessages;
    @In
    Context pageContext;

    @LoggedAdd(id="one",name="#{helloworld.id}")
    public void sayHello() {
        //implement your business logic here
        if (testInit != null) {
            log.info("the testInit is #{testInit.desc}!");
        }
        for (String name : pageContext.getNames()) {
            Object obj = pageContext.get(name);
            log.info("this #0 is #1", name, obj);
        }
        log.info("helloworld.sayHello() action called");
        facesMessages.add("sayHello");
    }

    public String getId() {
        if(id==null)
            id="hid";
        return id;
    }

    //add additional action methods

}
