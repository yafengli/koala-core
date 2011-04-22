<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
        <meta http-equiv="expires" content="0">
        <style type="text/css" media="all">
            @import url("<%=request.getContextPath()%>/resources/displaytag/css/maven-base.css"); @import url("<%=request.getContextPath()%>/resources/displaytag/css/maven-theme.css"); @import url("<%=request.getContextPath()%>/resources/displaytag/css/site.css"); @import
            url("<%=request.getContextPath()%>/resources/displaytag/css/screen.css");
        </style>
        <link rel="stylesheet" href="./css/print.css" type="text/css" media="print" />
        <decorator:head/>
    </head>
    <body>
        <table width="723" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <div style="font-size:20px;color:red">[DisplayTag]模板头</div>
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
                    <div style="font-size:20px;color:red">[DisplayTag]模板脚</div>
                </td>
            </tr>
        </table>
    </body>
</html>
