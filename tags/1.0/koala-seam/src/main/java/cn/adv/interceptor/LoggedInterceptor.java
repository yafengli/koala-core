package cn.adv.interceptor;

import cn.adv.TestInit;
import org.apache.commons.lang.StringUtils;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.intercept.AroundInvoke;
import org.jboss.seam.core.Interpolator;
import org.jboss.seam.intercept.InvocationContext;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-31
 * Time: 14:02:05
 * To change this template use File | Settings | File Templates.
 */
public class LoggedInterceptor {

    private static boolean isLogged = false;

    @AroundInvoke
    public Object checkLogged(InvocationContext ic) throws Exception {
        LoggedAdd la = ic.getMethod().getAnnotation(LoggedAdd.class);
        TestInit ti = (TestInit) Component.getInstance("testInit");
        Interpolator interpolator = (Interpolator) Component.getInstance("org.jboss.seam.core.interpolator");
        ti.tlog("[Interceptor]starting...");
        if (isLogged) {
            isLogged = false;
            if (StringUtils.isNotEmpty(la.name())) {
                ti.tlog("The [id, name] is  [#0, #1]", la.id(), interpolator.interpolate(la.name()));
            }
            return ic.proceed();
        } else {
            isLogged = true;
            ti.tlog("It's error.");
            return null;
        }
    }
}
