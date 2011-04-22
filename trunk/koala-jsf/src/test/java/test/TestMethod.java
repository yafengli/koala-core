package test;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-21
 * Time: 16:49:45
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestMethod {
    String name();
    String id();
    String[] names();
}
