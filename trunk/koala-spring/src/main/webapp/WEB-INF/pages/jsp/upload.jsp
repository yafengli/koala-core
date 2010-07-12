<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Uploadify Example Script</title>
    <link href="${pageContext.request.contextPath}/resources/uploadify/css/default.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/uploadify/css/uploadify.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>    
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/upload.view" enctype="multipart/form-data">
        文件:<input type="file" name="upfile"/></br>
        <input type="submit" value="提交"/> 
    </form>
</div>
</body>
</html>