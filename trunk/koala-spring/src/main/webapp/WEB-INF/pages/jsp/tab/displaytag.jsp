<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglibs.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!The paramKey is ${pin},the page size is ${pageNumber}.</h1>
        <display:table name="${test}" pagesize="10" partialList="true" size="${totalNumber}" id="id" requestURI="displaytag.ftl" export="true" sort="external">
            <display:column property="id" sortName="id" sortable="true"/>
            <display:column property="name" sortName="name" sortable="true"/>
            <display:column property="version"/>
        </display:table>
    </body>
</html>
