package org.helloworld;

import hellospring.ConfigBean;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author phoenixup
 */
public class JsonTest {
    ApplicationContext ctx=null;

    @Before
    public void init(){
        ctx=new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
    }
    @Test
    public void testSp(){
        ConfigBean config=ctx.getBean("configBean",ConfigBean.class);
        System.out.printf("[%s]",config.getName());
    }

    public static final String str = "{\"name\":\"hello\",\"address\":[{\"id\":1,\"len\":2},{\"id\":3,\"len\":4}]}";

    //  @Test
    public void testJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Bean bean = mapper.readValue(str, Bean.class);
            System.out.printf("[%s]\n", bean.getName());
            for(Address addres:bean.getAddress()){
                System.out.printf("[id:%d,len=%d]\n", addres.getId(),addres.getLen());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Bean {

    private String name;
    private List<Address> address;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
