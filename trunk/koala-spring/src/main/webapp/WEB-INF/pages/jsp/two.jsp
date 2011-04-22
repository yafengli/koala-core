<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Spring Web MVC project</title>
</head>

<body>
<h1>Two</h1>
<h2>${message}</h2>
<h1>Order</h1>
<a href="${flowExecutionUrl}&_eventId=toend">End</a>
<br />
<a href="${flowExecutionUrl}&_eventId=toone">One</a><br/>
<a href="${flowExecutionUrl}&_eventId=tosub">Tosub</a>

</body>
</html>
