package cn.adv.interceptor;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-31
 * Time: 15:56:35
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggedAdd {
    String id() default "";
    String name() default "";
}
