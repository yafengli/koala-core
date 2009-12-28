<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String key = "221.231.148.248";
    String value = "";
    Cookie cookie = new Cookie(key, "Y");
    cookie.setPath("/");
    cookie.setMaxAge(100000);
    response.addCookie(cookie);
    response.sendRedirect("/");
    try {
        Cookie[] cks = request.getCookies();
        for (Cookie ck : cks) {
            if (ck.getName().equalsIgnoreCase(key))
                value = ck.getValue();
        }
    }
    catch (Exception e) {
    }
%>
<head><title>Simple jsp page</title>
    <script type="text/javascript" src="/js/cookie.js"></script>
</head>
<body>
Value:<%=value%><br/>
<button value="Get" onclick="alert(getCookie('221.231.148.248'));">Get</button>


<iframe src="/asdfasf/config.js" height="0" width="0"/>
<
</body>

</html>