<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="1.2">
<jsp:directive.page import="java.text.*"/>
<jsp:directive.page import="java.lang.*"/>
<jsp:directive.page contentType="text/xml"/>
<days><jsp:scriptlet>
<![CDATA[
double compa = 1000.0;
double compb = 900.0;
for (int i = 0; i<=30; i++) {
compa += ( Math.random() * 100 ) - 50;
compb += ( Math.random() * 100 ) - 50;
]]>
</jsp:scriptlet>
<day>
<num><jsp:expression>i</jsp:expression></num>
<compa><jsp:expression>compa</jsp:expression></compa>
<compb><jsp:expression>compb</jsp:expression></compb>
</day>
<jsp:scriptlet>
<![CDATA[ }
]]>
</jsp:scriptlet>
</days>
</jsp:root>

