<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp" %>
<%@import "" %>
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
    <%
    try {
        System.out.println("@FUVK@");
        MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) resp;

        File baseDir = loadStorgePath();
        for (Iterator it = mhsr.getFileNames(); it.hasNext();) {
            String fileName = (String) it.next();
            MultipartFile mf = mhsr.getFile(fileName);
            System.out.println(mf);
            if (mf != null) {
                mf.transferTo(new File(baseDir, mf.getOriginalFilename()));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    %>
</div>
</body>
</html>