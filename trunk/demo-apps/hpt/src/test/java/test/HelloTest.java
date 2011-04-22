package test;

import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

public class HelloTest<T extends Object, K extends Object> {

    public T getItem(K k) {
        try {
            return (T) k.getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Test
    public void test() {
        try {
            Velocity.init();
            Template tp = Velocity.getTemplate("hello.vm");
            VelocityContext ctx = new VelocityContext();
            ctx.put("name", "牛B");
            StringWriter writer = new StringWriter();
            tp.merge(ctx, writer);
            System.out.println(writer.toString());
            HelloTest<String, String> tt = new HelloTest<String, String>();
            System.out.println(tt.getItem("fuck"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
