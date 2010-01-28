<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="/kp/resources/ofc2/swfobject.js" type="text/javascript"></script>
    <script type="text/javascript">
        swfobject.embedSWF("/kp/resources/ofc2/open-flash-chart.swf", "my_chart", "550", "200", "9.0.0", "/kp/resources/ofc2/expressInstall.swf",
        {"data-file":"/kp/ofc.ftl","loading":"数据读取中...","id":"my_chart_id"});
        function hello() {
            alert("Fuck");
        }
    </script>
</head>
<body>
<p>Hello World</p>
<div id="my_chart"></div>
</body>
</html>