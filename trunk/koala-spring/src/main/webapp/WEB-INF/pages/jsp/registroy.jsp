<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>My JSP 'index.jsp' starting page</title>
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

        <sp:bind path="command.*">
            <font color="#FF0000"> <c:forEach
                    items="${status.errorMessages}" var="error">
                    错误: <c:out value="${error}"/>
                    <br>
            </c:forEach> </font>
        </sp:bind>
        <sf:form  modelAttribute="command">
            name:<sf:input path="name"/><sf:errors path="name"/><br/>
            sex:<sf:input path="sex"/><sf:errors path="sex"/> <br/>
            age:<sf:input path="age"/><sf:errors path="age"/> <br/>
            birthday:<sf:input path="birthday"/><sf:errors path="birthday"/> <br/>
            <input type="submit" value="submit"/>
        </sf:form>
        <form name="hello" action="${pageContext.request.contextPath}/registroy.htm"
              method="post">name:<input type="text" name="name"/><br/>
            sex:<input type="text" name="sex"/><br/>
            age:<input type="text" name="age"/><br/>
            birthday:<input type="text" name="birthday"/><br/>
            <input type="submit" value="submit"/></form>
    </body>
</html>
