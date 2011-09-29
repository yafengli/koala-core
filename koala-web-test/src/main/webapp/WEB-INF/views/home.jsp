<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://lib.sinaapp.com/js/jquery/1.6/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		load(0);
		//setTimeout(load(count++), 2468);
		b_load();
	});
	function b_load() {
		var count = 1;
		window.setInterval(function() {
			load(count++)
		}, 2468);
	}
	function load(count) {
		if (count >= 5) {
			window.top.location = "${pageContext.request.contextPath}/index.jsp";
			return;
		}
		$.getJSON('${pageContext.request.contextPath}/json', function(item) {
			$("#_aj").text(item.name + "|" + item.id + "|" + count++);
		});
	}
</script>
<title>JSP Page</title>
</head>
<body>
	<h1>This is HOME!</h1>
	<h3>${user.id}|${user.name}</h3>
	<p>
		<form:form modelAttribute="user" method="POST">
			id:<input type="text" name="id" value="${user.id}"/><form:errors path="id"/><br/>
			addr:<select name="address">
				<option label="1" value="1" />
				<option label="2" value="3" selected/>
				<option label="3" value="3"/>
				<form:option value=""></form:option>
				</select><form:errors path="address"/>
			name:<input type="text" name="name" value="${user.name}"/><br/>
			<input type="submit" value="Submit" />
		</form:form>
	</p>

	<div id="_aj"
		style="color: green; font-weight: bold; background-color: #ffe4c4; width: 500">This
		is temp!</div>
</body>
</html>
