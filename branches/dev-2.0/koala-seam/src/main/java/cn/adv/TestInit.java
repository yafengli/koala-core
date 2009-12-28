package cn.adv;

import org.jboss.seam.annotations.*;
import org.jboss.seam.log.Log;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-25
 * Time: 16:51:58
 * To change this template use File | Settings | File Templates.
 */
@Startup
@Name("testInit")
@Scope(org.jboss.seam.ScopeType.APPLICATION)
public class TestInit {
    @Logger
    private Log logger;
    private String Desc;

    public void sayHello() {
        logger.info("Hello INIT()...");
    }

    public String getDesc() {
        return "Hello Desc.";
    }

    public void tlog(Object obj, Object... objs) {
        logger.info(obj, objs);
    }

    @Factory("testFac")
    public String getFac() {
        return "Hello testFactory!";
    }
}
