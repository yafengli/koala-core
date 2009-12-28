<%@ page language="java"
         import="org.jfree.chart.*,org.jfree.data.general.DefaultPieDataset,org.jfree.chart.plot.PiePlot,java.awt.Font,org.jfree.chart.JFreeChart,org.jfree.chart.title.TextTitle,org.jfree.chart.title.LegendTitle,org.jfree.chart.labels.StandardPieToolTipGenerator,org.jfree.chart.urls.StandardPieURLGenerator,org.jfree.chart.entity.StandardEntityCollection,java.io.PrintWriter,org.jfree.chart.servlet.ServletUtilities"
         pageEncoding="utf-8"%>

<%
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Spring2.0宝典", 47000);
        data.setValue("轻量级J2EE企业应用实战", 38000);
        data.setValue("基于J2EE的Ajax宝典", 31000);
        data.setValue("Ajax in Action", 25000);
        data.setValue("JavaScript权威指南", 29000);

        JFreeChart chart =
                ChartFactory.createPieChart3D(
                "图书销量统计", data, true, true, false);

        chart.setTitle(new TextTitle("图书销量统计图", new Font("黑体", Font.ITALIC,
                22)));

        LegendTitle legend = chart.getLegend(0);
        legend.setItemFont(new Font("宋体", Font.BOLD, 18));
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("隶书", Font.BOLD, 12));
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        plot.setURLGenerator(new StandardPieURLGenerator("/pages/b.jsp"));

        StandardEntityCollection entityCollection = new StandardEntityCollection();
        ChartRenderingInfo info = new ChartRenderingInfo(entityCollection);
        PrintWriter pw = new PrintWriter(out);
        String filename = ServletUtilities.saveChartAsJPEG(chart, 720, 450,
                info, request.getSession());
        ChartUtilities.writeImageMap(pw, "map0", info, false);
%>

<img src="/DisplayChart?filename=<%=filename%>" width="720"
     height="450" usemap="#map0" 　/>

