<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="1.2">
<jsp:directive.page import="java.text.*"/>
<jsp:directive.page import="java.lang.*"/>
<jsp:directive.page contentType="text/html"/>
[<jsp:scriptlet>
<![CDATA[
double compa = 1000.0;
double compb = 900.0;
for (int i = 0; i<=30; i++) {
compa += ( Math.random() * 100 ) - 50;
compb += ( Math.random() * 100 ) - 50;
if ( i > 0 ) out.print( "," );
]]> 
</jsp:scriptlet>{"compa":<jsp:expression>compa</jsp:expression>,"compb":<jsp:expression>compb</jsp:expression>}<jsp:scriptlet>
<![CDATA[ }
]]>
</jsp:scriptlet>]
</jsp:root>

