package cn.demo.scan.controller;

import cn.demo.dao.DemoPersonDao;
import cn.demo.pojo.DemoPerson;
import cn.demo.webservice.HelloWorld;
import cn.demo.support.WebBeanDefinition;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author yafengli
 */
@Controller
@Scope(WebBeanDefinition.SCOPE_SESSION)
@SessionAttributes({"test", "date"})
public class DemoContorller {
    public static final StringBuilder fsb = new StringBuilder();

    static {
        fsb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        fsb.append("<RESPONSE>");
        fsb.append("<SERIAL_NO>%s</SERIAL_NO>");
        fsb.append("<RESULT>%s</RESULT>");
        fsb.append("<DESC>%s</DESC></RESPONSE>");
    }

    @Autowired
    private Validator validator;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DemoPersonDao demoPersonDaoImpl;

    @RequestMapping("/aindex.ftl")
    public ModelAndView index(@RequestParam(required = false, value = "id") String id) {
        ModelAndView mav = new ModelAndView("aindex");
        Map map = new HashMap();
        ResourceBundle rb = ResourceBundle.getBundle("sql");
        String ssql = rb.getString("demo.person.sql");
        DemoPerson dp = demoPersonDaoImpl.findForObject(ssql, 1L);
        map.put("message", String.format("This is cn.neto message!The id is [%s]", dp.getName()));
        mav.addAllObjects(map);
        return mav;
    }

    @RequestMapping("/helloj.ftl")
    public ModelAndView helloj(@RequestParam(required = false, value = "id") String id, HttpServletRequest request, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView("helloj");
        Map map = new HashMap();
        map.put("message", String.format("This is cn.neto message!The id is [%s]", id));
        mav.addAllObjects(map);
        return mav;
    }

    @RequestMapping("/hellof.ftl")
    public ModelAndView hellof(@RequestParam(required = false, value = "id") String id) {
        ModelAndView mav = new ModelAndView("hellof");
        Map map = new HashMap();
        map.put("message", String.format("This is cn.neto message!The id is [%s]", id));
        mav.addAllObjects(map);
        return mav;
    }

    @RequestMapping("/webservice.ftl")
    public ModelAndView webservice(@RequestParam(required = false, value = "name") String name, HttpServletRequest req, HttpServletResponse resp) {

        for (Enumeration<String> enu = req.getParameterNames(); enu.hasMoreElements();) {
            String param = enu.nextElement();
            System.out.printf("[%s=%s]\n", param, req.getParameter(param));
        }
        ModelAndView mav = new ModelAndView("webservice");
        mav.addObject("message", String.format("[ds:%s][va:%s]", dataSource.toString(), validator.toString()));
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
        HelloWorld helloWorldClient = (HelloWorld) ctx.getBean("helloWorldClient");
        mav.addObject("message2", helloWorldClient.sayHello(name));
        return mav;

    }

    @RequestMapping("/testdemo.ftl")
    public void index(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println("Test");
            BufferedReader in = null;
            PrintWriter writer = resp.getWriter();
            in = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            org.dom4j.Document doc = DocumentHelper.parseText(builder.toString());
            Element root = doc.getRootElement();
            Node action = root.selectSingleNode("ACTION");
            Node serial = root.selectSingleNode("SERIAL_NO");
            Node province = root.selectSingleNode("PROVINCE");

            System.out.printf("[%s,%s,%s]\n", action.getText(), serial.getText(), province.getText());

            resp.setContentType("text/xml;charset=UTF-8");
            String str = String.format(fsb.toString(), serial.getText(), 0, "successful");
            System.out.printf("Str:[%s]\n", str);
            writer.write(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/json.ftl")
    public void json(HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        double compa = 1000.0;
        double compb = 900.0;
        JSONObject data = new JSONObject();
        JSONArray bss = new JSONArray();

        for (int i = 0; i <= 30; i++) {
            try {
                compa += (Math.random() * 100) - 50;
                compb += (Math.random() * 100) - 50;
                if (i > 0) {
                    JSONObject item = new JSONObject();
                    item.put(String.valueOf(i), compa);
                    bss.put(item);
                }
                data.put("items", bss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.getWriter().write(data.toString());
    }
}

