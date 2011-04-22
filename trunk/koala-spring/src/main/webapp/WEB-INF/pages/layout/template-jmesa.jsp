<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <jsp:include page="/common/menu/style.jsp" flush="false"/>
        <decorator:head/>
    </head>
    <body>
        <table width="723" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="300" valign="top">
                    <decorator:body/>
                </td>
            </tr>
        </table>
    </body>
</html>
