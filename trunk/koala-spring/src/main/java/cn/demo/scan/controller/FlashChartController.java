package cn.demo.scan.controller;

import jofc2.model.Chart;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.Element;
import jofc2.model.elements.LineChart;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-1-26
 * Time: 13:37:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Scope("session")
public class FlashChartController {


    @RequestMapping("/ofc")
    public void createchart(HttpServletResponse resp, ModelMap model) {
        PrintWriter out = null;
        try {
            resp.setContentType("text/plain;charset=UTF-8");
            out = resp.getWriter();
            Chart chart = new Chart("Hello测试");
            BarChart bc = new BarChart();
            bc.setText("测试");
            bc.setColour("red");
            bc.setFontSize(16);
            bc.setKey_on_click("hello");
            bc.addValues(2, 6, 7, 9, 1, 7, 3, 9, 7);                
            chart.addElements(bc);
            LineChart.Dot dot=new LineChart.Dot(1);            
            out.write(chart.toString());

            out.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            out.close();
        }

    }
}
