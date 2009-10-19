<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<sf:form action="upload.ftl" modelAttribute="type" method="POST" enctype="multipart/form-data">
    <table>
        <tr>
            <td>First Name</td>
            <td><sf:input path="name"/></td>
        </tr>
        <tr>
            <td>Upload File:</td>
            <td><input type="file" name="upfile" id="_upf_"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="上传"/>
            </td>
        </tr>
    </table>
</sf:form>
</body>
</html>