<%@ page import="java.util.Enumeration" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>[helloj.jsp]</title>
</head>

<body>
<h1>[helloj.jsp]</h1>

<h2>${message}|${action}</h2>
<%
    for (Enumeration enu = session.getAttributeNames(); enu.hasMoreElements();) {
        String key = (String) enu.nextElement();
        Object value = session.getAttribute(key);
%><%=key%><%=value.toString()%><%
    }
%>
</body>
</html>
