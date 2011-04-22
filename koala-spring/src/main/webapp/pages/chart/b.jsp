<%@ page language="java"
    import="org.jfree.chart.*,java.awt.Font,org.jfree.chart.JFreeChart,org.jfree.chart.title.TextTitle,org.jfree.chart.title.LegendTitle,org.jfree.chart.servlet.ServletUtilities,org.jfree.chart.plot.PlotOrientation,org.jfree.chart.plot.CategoryPlot,org.jfree.chart.axis.NumberAxis,org.jfree.chart.axis.CategoryAxis,org.jfree.chart.axis.CategoryLabelPositions,org.jfree.data.category.CategoryDataset,org.jfree.data.category.DefaultCategoryDataset"
    pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'b.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    </head>

    <body>
        <%
           
            CategoryDataset dataset;
            String queryString = request.getQueryString();
            queryString = java.net.URLDecoder.decode(queryString, "UTF-8");
            String category = null;
            String[] queryStrings=queryString.split("&");
            for (int i = 0; i < queryStrings.length; i++) {
            String[] nameValue = queryStrings[i].split("=");
            category = nameValue[0].equals("category") ? nameValue[1]
            : category;
            } 
            
            String subTitle = "";
            if (category.equals("Spring2.0宝典")
                    || category.equals("轻量级J2EE企业应用实战")
                    || category.equals("基于J2EE的Ajax宝典")) {
                dataset = leeGetDataSet();
                subTitle = "作者李刚的";
            } else {
                dataset = getDataSet();
                subTitle = "其它";
            }
            String title = subTitle + "图书的销售情况";
            JFreeChart chart = ChartFactory.createBarChart3D(title, "月份", "销量",
                    dataset, PlotOrientation.VERTICAL, true, false, false);
            chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC,
                    22)));
            LegendTitle legend = chart.getLegend(0);
            legend.setItemFont(new Font("宋体", Font.BOLD, 20));//设置图例的字体
            CategoryPlot plot = (CategoryPlot) chart.getPlot();

            CategoryAxis categoryAxis = plot.getDomainAxis();//取得横轴
            categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));//设置横轴显示标签的字体
            categoryAxis
                    .setCategoryLabelPositions(CategoryLabelPositions.UP_45);//分类标签以４５度倾斜
            categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 18));//分类标签字体

            NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();//取得纵轴
            numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));

            String filename = ServletUtilities.saveChartAsPNG(chart, 650, 390,
                    null, session);
        %>

        <img src="DisplayChart?filename=<%=filename%>" width="720"
            height="450" border="0" 　/>

    </body>
    <%!private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(47000, "Ajax in Action", "06年10月");
        dataset.addValue(38000, "Ajax in Action", "06年11月");
        dataset.addValue(31000, "Ajax in Action", "06年12月");
        dataset.addValue(25000, "Ajax in Action", "07年01月");
        dataset.addValue(45000, "Ajax in Action", "07年02月");
        dataset.addValue(28000, "Ajax in Action", "07年03月");
        dataset.addValue(42000, "JavaScript权威指南", "06年10月");
        dataset.addValue(31000, "JavaScript权威指南", "06年11月");
        dataset.addValue(41000, "JavaScript权威指南", "06年12月");
        dataset.addValue(35000, "JavaScript权威指南", "07年01月");
        dataset.addValue(25000, "JavaScript权威指南", "07年02月");
        dataset.addValue(18000, "JavaScript权威指南", "07年03月");
        return dataset;
    }

    private static CategoryDataset leeGetDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(42000, "Spring2.0宝典", "06年10月");
        dataset.addValue(18000, "Spring2.0宝典", "06年11月");
        dataset.addValue(51000, "Spring2.0宝典", "06年12月");
        dataset.addValue(25000, "Spring2.0宝典", "07年01月");
        dataset.addValue(35000, "Spring2.0宝典", "07年02月");
        dataset.addValue(26000, "Spring2.0宝典", "07年03月");
        dataset.addValue(47000, "轻量级J2EE企业应用实战", "06年10月");
        dataset.addValue(38000, "轻量级J2EE企业应用实战", "06年11月");
        dataset.addValue(31000, "轻量级J2EE企业应用实战", "06年12月");
        dataset.addValue(25000, "轻量级J2EE企业应用实战", "07年01月");
        dataset.addValue(25000, "轻量级J2EE企业应用实战", "07年02月");
        dataset.addValue(25000, "轻量级J2EE企业应用实战", "07年03月");
        dataset.addValue(43000, "基于J2EE的Ajax宝典", "06年10月");
        dataset.addValue(28000, "基于J2EE的Ajax宝典", "06年11月");
        dataset.addValue(37000, "基于J2EE的Ajax宝典", "06年12月");
        dataset.addValue(20000, "基于J2EE的Ajax宝典", "07年01月");
        dataset.addValue(35000, "基于J2EE的Ajax宝典", "07年02月");
        dataset.addValue(15000, "基于J2EE的Ajax宝典", "07年03月");
        return dataset;
    }%>

</html>