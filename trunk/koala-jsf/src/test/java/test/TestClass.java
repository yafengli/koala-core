package test;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-21
 * Time: 16:38:36
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestClass {
    String id();
    
}
