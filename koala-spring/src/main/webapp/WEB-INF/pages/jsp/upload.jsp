<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>Simple jsp page</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#_hello_").click(function() {
                alert("Processing...");
                $("#_hello_").attr("disabled", "disabled");                
            });
        });
    </script>
</head>
<body>
<div id="_test_">
    <sf:form action="upload.ftl" modelAttribute="type" method="POST" enctype="multipart/form-data">
        <table>
            <!--
            <tr>
                <td>截断文件：</td>
                <td>是：<sf:radiobutton path="stop" value="false"/>否：<sf:radiobutton path="stop" value="true"/></td>
            </tr>
            -->
            <tr>
                <td>文件选择：</td>
                <td><input type="file" name="upfile" id="_upf_"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input id="_hello_" type="submit" value="上传"/>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="swfupload/view.ftl" target="_blank">查看</a>
                </td>
            </tr>
        </table>
    </sf:form>
</div>
</body>
</html>