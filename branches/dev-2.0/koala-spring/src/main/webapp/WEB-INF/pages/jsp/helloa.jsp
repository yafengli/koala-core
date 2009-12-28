<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>[helloa.jsp]</title>
    <style type="text/css">
        @import "<%=request.getContextPath()%>/resources/js/jquery/ui/tabs/tab.css";
    </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/ui/tabs/tab.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/demo.js"></script>
    <script type="text/javascript">
        //Tabs
        var tab = null;
        $(document).ready(
                function() {
                    $("#firstName-label").hover(
                            function() {
                                $("#firstName-label").addClass("text_label_green");
                            },
                            function() {
                                $("#firstName-label").removeClass("text_label_green")
                            }
                            );
                    $("#lastName-label").addClass("text_label");
                },
                function() {
                    tab = new TabView({
                        containerId:"tab_menu",
                        pageid:"page",
                        cid:"tab_po",
                        position:"top"
                    });
                    tab.add({
                        id :'tab1_id_index1',
                        title :"百度主页",
                        url :"http://www.baidu.com",
                        isClosed :false
                    });
                    tab.add({
                        id:"tab1_id_index2",
                        title:"显示",
                        url:"#"
                    })
                }
                )
    </script>
</head>
<body>
<div id="example">
    <ul>
        <li><a href="#example-one"><span>one</span></a></li>
        <li><a href="#example-two"><span>two</span></a></li>
    </ul>

    <div id="example-one">
        <h1>[helloa.jsp]</h1>

        <h2>${message}|${action}|session:${pet}|${test.firstName}|${test.lastName}</h2>
        <sp:bind path="test.*">
        <font color="#FF0000"> <c:forEach
                items="${status.errorMessages}" var="error">
            错误: <c:out value="${error}"/>
            <br>
        </c:forEach> </font>
            </sp:bind>
    </div>
    <div id="example-two">
        <sf:form modelAttribute="test" method="POST">
            <table>
                <tr>
                    <td><label id="firstName-label">First Name:</label></td>
                    <td><sf:input path="firstName"/></td>
                    <td><sf:errors path="firstName"/></td>
                </tr>
                <tr>
                    <td><label id="lastName-label">Last Name:</label></td>
                    <td><sf:input path="lastName"/></td>
                    <td><sf:errors path="lastName"/></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" value="Save Changes"/>
                    </td>
                </tr>
            </table>
        </sf:form>
    </div>
</div>
</body>
</html>
