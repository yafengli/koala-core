package cn.adv;

import org.jboss.seam.annotations.Unwrap;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.ScopeType;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-25
 * Time: 17:20:13
 * To change this template use File | Settings | File Templates.
 */
@Name("testWrap")
@Startup
@Scope(ScopeType.APPLICATION)
public class TestWrap {
    @Unwrap
    public String getHello(){
        return "Hello Unwrap Hello.";
    }
}
