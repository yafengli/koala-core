<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>[aindex.jsp]</title>
</head>

<body>
<p>欢迎使用，这是[aindex.jsp].</p>

<h3>${message}</h3>

<sf:form commandName="account" action="${pageContext.request.contextPath}/aindex.view" method="POST">
    <sf:input path="id"/><sf:errors path="id"/><br/>
    <sf:input path="name"/><sf:errors path="name"/><br/>
    <sf:input path="time"/><sf:errors path="time"/><br/>
    <input type="submit" value="Submit"/>
</sf:form>
</body>
</html>
