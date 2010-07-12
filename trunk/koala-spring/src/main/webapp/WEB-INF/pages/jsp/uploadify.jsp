<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Uploadify Example Script</title>
    <link href="${pageContext.request.contextPath}/resources/uploadify/css/default.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/uploadify/css/uploadify.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/uploadify/scripts/swfobject.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/uploadify/scripts/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#uploadify").uploadify({
                'uploader'       : '${pageContext.request.contextPath}/resources/uploadify/scripts/uploadify.swf',
                'script'         : '${pageContext.request.contextPath}/uploadify.view',
                'cancelImg'      : '${pageContext.request.contextPath}/resources/uploadify/cancel.png',
                'folder'         : 'uploads',
                'queueID'        : 'fileQueue',
                'auto'           : false,
                'multi'          : true
            });
        });
    </script>
</head>
<body>
<div id="fileQueue"></div>
<input type="file" name="uploadify" id="uploadify"/>
<button onclick="javascript:$('#uploadify').uploadifyUpload();">上传文件</button>
|
<button onclick="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</button>| <a href="${pageContext.request.contextPath}/swf/swfupload/view" target="_blank">查看</a>
</body>
</html>