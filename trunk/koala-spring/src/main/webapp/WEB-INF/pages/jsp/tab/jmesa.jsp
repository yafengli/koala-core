<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglibs.jsp" %>
<html>
    <head>
        <title>JSP Page</title>
        <script type="text/javascript">
            function onInvokeAction(id) {
                setExportToLimit(id, '');
                var parameterString = createParameterStringForLimit(id);
                $.get('${pageContext.request.contextPath}/jmesa/ajax.ftl?ajax=false&' + parameterString, function(data) {
                    $("#views").html(data)
                });
            }

            function onInvokeExportAction(id) {
                var parameterString = createParameterStringForLimit(id);
                location.href = '${pageContext.request.contextPath}/jmesa/ajax.ftl?ajax=false&' + parameterString;
            }
        </script>
    </head>
    <body>
        <div>
            <h4>${param['name']}</h4>
        </div>
        <form id="_form_" name="presidentsForm"
              action="${pageContext.request.contextPath}/jmesa.ftl">
            <div id="views">${views}</div>
        </form>
    </body>
</html>
