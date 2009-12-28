<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title>
    <script src="<%=request.getContextPath()%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                type:"GET",
                url: "<%=request.getContextPath()%>/mapjson.ftl",
//                dataType:"json",
                data:"name=",
                success: function(data) {
                    alert(data);
                    /**
                     $.each(data.items, function(key, val) {
                     alert(data.items[key]);
                     });
                     */
                },
                error:function (data) {
                    alert("ERROR:" + data);
                }});
        });
    </script>
</head>
<body><H2>JSON Test!</H2></body>
</html>