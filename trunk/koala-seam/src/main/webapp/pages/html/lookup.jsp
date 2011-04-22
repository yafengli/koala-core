<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://code.google.com/p/jmesa" prefix="jmesa" %>
<html>
    <head>
        <style type="text/css">
            @import url("/adv/resources/css/jmesa.css");
        </style>
        <script type="text/javascript" src="/adv/resources/js/jmesa/jmesa.js"></script>
        <script type="text/javascript" src="/adv/resources/js/jquery/jquery.js"></script>        
    </head>
    <body>
        <center>
            <form name="presidentsForm" action="/adv/demo.lookup">
                ${presidents}
            </form>
            <script type="text/javascript">
                function onInvokeAction(id) {
                    setExportToLimit(id, '');
                    createHiddenInputFieldsForLimitAndSubmit(id);
                }
                function onInvokeExportAction(id) {
                    var parameterString = createParameterStringForLimit(id);
                    location.href = '/adv/demo.lookup?' + parameterString;
                }
            </script>
        </center>
    </body>
</html>
