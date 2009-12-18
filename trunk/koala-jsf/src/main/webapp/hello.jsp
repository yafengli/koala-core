<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" uri="http://demo.cn/helloajax" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<f:view>
    <h3>alalalal[#{helloWorld.name}]</h3>
    <h:outputText value="#{helloWorld.name}"/>
    <h:form>
        <h:commandButton action="#{helloWorld.action}" value="Action"/>
    </h:form>
    <a:ajax id="Fuck" dname="#{helloWorld.name}"/>
</f:view>