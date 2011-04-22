package cn.demo.scan.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.demo.support.WebBeanDefinition;

/**
 * @author yafengli
 */
@Controller
@Scope(WebBeanDefinition.SCOPE_SESSION)
public class JFreechartController {

    @RequestMapping("/jfreechart.ftl")
    public void jfreechartdemo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("img/png");
        DefaultPieDataset pie = new DefaultPieDataset();
        pie.setValue("Nokia", Integer.valueOf(60));
        pie.setValue("Sony", Integer.valueOf(20));
        pie.setValue("LG", Integer.valueOf(10));
        pie.setValue("ChangHong", Integer.valueOf(10));
        JFreeChart chart = ChartFactory.createPieChart("Demo", pie, true, true, true);
        ChartUtilities.writeChartAsPNG(resp.getOutputStream(), chart, 400, 400);
        resp.getOutputStream().close();
    }
}
