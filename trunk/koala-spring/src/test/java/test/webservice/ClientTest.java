/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.webservice;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.demo.webservice.HelloWorld;

/**
 *
 * @author Administrator
 */
public class ClientTest {

    public static final QName sq = new QName("http://www.test.cn/", "HelloWorldService");
    public static final QName pq = new QName("http://www.test.cn/", "HelloWorldPort");
    public static final String address = "http://localhost:8080/kp/services/HelloWorld";
    ApplicationContext ctx = null;
    public static final StringBuilder bd = new StringBuilder();

    static {
        bd.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        bd.append("<REQUEST>");
        bd.append("<ACTION>CREATE</ACTION>");
        bd.append("<SERIAL_NO>34567ASDFASF</SERIAL_NO>");
        bd.append("<ORDER_NO>ZDSAFD</ORDER_NO>");
        bd.append("<PROVINCE>河南</PROVINCE>");
        bd.append("<CITYNAME>... ...</CITYNAME>");
        bd.append("<ADSL_ACCOUNT>… …</ADSL_ACCOUNT>");
        bd.append("<MAN_PASSWORD>… … </MAN_PASSWORD>");
        bd.append("<NAME>…</NAME>");
        bd.append("<GENDER>…</GENDER>");
        bd.append("<ADDRESS>…</ADDRESS>");
        bd.append("<HOME_PHONE></HOME_PHONE>");
        bd.append("<MOBILE_PHONE>…</MOBILE_PHONE>");
        bd.append("<XLT_PHONE>...</XLT_PHONE>");
        bd.append("<FEETYPE>0/1</FEETYPE>");
        bd.append("<AMOUNT>…</AMOUNT>");
        bd.append("</REQUEST>");
    }

//    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:client.xml"});
    }

//    @Test
    public void testClient() {
        try {
            Service service = Service.create(sq);
            service.addPort(pq, SOAPBinding.SOAP11HTTP_BINDING, address);
            HelloWorld hw = (HelloWorld) service.getPort(HelloWorld.class);
            System.out.printf("Test1 [%s]", hw.sayHello("Bull shit!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void testSpring() {
        HelloWorld client = (HelloWorld) ctx.getBean("helloWorldClient");
        System.out.printf("Test2 [%s]", client.sayHello("[占领天涯]"));
    }

    @Test
    public void testHttp() {
        try {
            URL url = new URL("http://localhost:8080/kp/testdemo.ftl");
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            
            uc.setDoOutput(true);
            uc.setRequestMethod("POST");
            uc.setRequestProperty("Content-type", "text/xml");
            PrintWriter writer = new PrintWriter(uc.getOutputStream());
            writer.write(bd.toString());
            writer.flush();
            writer.close();
            uc.connect();
            int code =uc.getResponseCode();
            System.out.printf("[%s]\n",code);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
