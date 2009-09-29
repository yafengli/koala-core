<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
        <meta http-equiv="expires" content="0">
        <script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-1.2.6.min.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/demo.css">
        <title><decorator:head/></title>
    </head>
    <body>
        <table width="723" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <div style="font-size:20px;color:red">[BASE-header]模板头</div>
                </td>
            </tr>
            <tr>
                <td>
                    <div style="font-size:20px;color:red"><jsp:include page="/common/menu/menu.jsp" flush="true"/></div>
                </td>
            </tr>
            <tr>
                <td height="300" valign="top">
                    <decorator:body/>
                </td>
            </tr>
            <tr>
                <td>
                    <div style="font-size:20px;color:red">[BASE-footer]模板</div>
                </td>
            </tr>
        </table>
    </body>
</html>
