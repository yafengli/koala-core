package test;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-21
 * Time: 16:43:28
 * To change this template use File | Settings | File Templates.
 */
@TestClass(id = "#Fuck#")
public class HelloTest {
    @TestMethod(id = "#you#", names = {"one", "two", "three"}, name = "#Hello#")
    public void sayHello() {
        System.out.println("say Hello!");
    }

    public static void main(String[] args) {
        try {
            HelloTest ht = new HelloTest();
            boolean isFlag = ht.getClass().isAnnotationPresent(TestClass.class);
            if (isFlag) {
                TestClass t1 = ht.getClass().getAnnotation(TestClass.class);
                System.out.printf("[%s]\n", t1.id());
            }
            Method md = ht.getClass().getMethod("sayHello");
            boolean isMd = md.isAnnotationPresent(TestMethod.class);
            if (isMd) {
                TestMethod tm1 = md.getAnnotation(TestMethod.class);
                System.out.printf("[%s]\n", tm1.id());
                System.out.printf("[%s]\n", tm1.name());
                for (String name : tm1.names()) {
                    System.out.printf("%s ", name);
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
