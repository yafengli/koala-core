<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="images">
            <%
            java.util.List<String> ls = (java.util.List<String>) session.getAttribute("files");
            for (String name : ls) {
                if (name != null && name.trim().length() > 1) {
            %><img src="<%=request.getContextPath()%>/<%=name%>" width="80" height="60"/><%
                }
            }
            %>            
        </div>
    </body>
</html>
