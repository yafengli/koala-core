<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            response.setHeader("P3P", "CP=\"CAO PSA OUR\"");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <script type="text/javascript" src="core.js"></script>        
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <input type="button" onclick="setCookie('hello','Y',1);" value="设置Cookie"/>
        <input type="button" onclick="alert(getCookie('hello'));" value="查看Cookie"/>
    </body>
</html>
