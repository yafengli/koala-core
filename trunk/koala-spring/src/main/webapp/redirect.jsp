<%@page contentType="text/html" pageEncoding="UTF-8" %>
<table width="100%">
    <tr>
        <td>
            <div>
                <span style="font-size:15;color:red">不使用@Annotation的Spring MVC演示程序</span>
            </div>
            <a href="index.htm">ParameterizableViewController演示</a><br/>
            <a href="muli.htm?method=logIn">MultiActionController演示-由[方法名]决定代理类</a><br/>
            <a href="muli.htm?method=logOut">MultiActionController演示-由[方法名]决定代理类</a><br/>
            <a href="registroy.htm">SimpleFormController演示</a><br/>
            <hr/>
            <div>
                <span style="font-size:15;color:red">
                    使用@Annotation注解的Spring	MVC演示
                </span>
            </div>
            <a href="aindex.ftl?id=id">返回ModelAndView演示</a><br/>
            <a href="hellof.ftl?id=id">返回空-1使用FreeMarkerView</a><br/>
            <a href="helloj.ftl?id=id">返回空-2使用JstlView</a><br/>
            <a href="helloa.ftl?id=id">返回String为View名</a><br/>
            <hr/>
            <div><span>分页</span></div>
            <a href="displaytag.ftl">DisplayTag</a><br/>
            <a href="list.ftl?pageNumber=1&pageSize=15">FreeMarker-宏</a><br/>
            <a href="jmesa.ftl?name=hello">Jmesa-分页</a><br/>
            <hr/>
            <div><span>Web Flow</span></div>
            <a href="main.htm">含子流程</a><br/>
            <hr/>
            <div><span>Web Service</span></div>
            <a href="services/HelloWorld?wsdl">Web Service WSDL</a><br/>
            <a href="webservice.ftl?name=Test">Test Web Service</a><br/>

            <div><span>UrlRewrite</span></div>
            <a href="hello/123/test.shtml">Test Web Service,using the param 'hello' by urlrewrite.</a><br/>
            <hr/>
            <div><span>认证授权</span></div>
            <a href="index.jsp">Spring Security</a><br/>
            <hr/>
            <div><span>JSON</span></div>
            <a href="mapjson.ftl">Map Json String</a><br/>
            <a href="autojson.ftl">Auto Json String</a>
            <a href="pages/json.jsp">JQuery Ajax Json</a>
            <hr/>
            <div><span>JS+CSS</span></div>
            <a href="pages/js_demo.jsp">JQuery</a><br/>
            <hr/>
            <a href="testdemo.ftl">Test Xml Demo</a><br/>
            <hr/>
            <a href="upload.ftl">上传文件-大文件断点续传</a><br/>
            <a href="swfupload.ftl">上传文件-Flash</a><br/>
            <a href="uploadify.ftl">上传文件-JQuery</a>
            <hr/>
            <a href="auto/test.jsp">自动完成</a>
            <hr/>
            <a href="map/index.jsp">Google Map</a><br/>
        </td>
        <td>
            <div><span>JFreeChart</span></div>
            <img src="jfreechart.ftl" alt="JFreeChart-图表"/></td>
        <td>
            <div>Flash Chart</div>
            <jsp:include page="/ofc.jsp" flush="true"/>
        </td>
    </tr>
</table>


