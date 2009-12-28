package cn.adv.interceptor;

import org.jboss.seam.annotations.intercept.Interceptors;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-31
 * Time: 14:30:01
 * To change this template use File | Settings | File Templates.
 */

@Target(java.lang.annotation.ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Interceptors(cn.adv.interceptor.LoggedInterceptor.class)
public @interface Logged {
}
