<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<html>
<head>
    <title>登陆页面！</title>
</head>
<body onload='document.f.j_username.focus();'>
<h3>Login with Username and Password</h3>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:if test="${not empty param.login_error}">
<c:set var="exKey" value="<%=AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY%>"/>
        <font color="red">
            Your login attempt was not successful, try again.<BR><BR>
            Reason: ${sessionScope[exKey].message}
        </font>
</c:if>
<form name='f' action='${ctx}/j_spring_security_check' method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='j_username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password'/></td>
        </tr>
        <tr>
            <td><input type='checkbox' name='_spring_security_remember_me'/></td>
            <td>Remember me on this computer.</td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit"/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="reset" type="reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>
