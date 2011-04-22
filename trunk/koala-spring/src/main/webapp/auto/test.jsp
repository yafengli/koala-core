<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<title>jQuery Autocomplete Plugin</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/js/auto/jquery.autocomplete.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.min.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/resources/js/auto/jquery.autocomplete.min.js"></script>
<script type='text/javascript' src="./localdata.js"></script>

<script type="text/javascript">
$().ready(function() {	
	$("#suggest1").focus().autocomplete(cities);	
	$("#suggest13").autocomplete(emails, {
		minChars: 0,
		width: 310,
		matchContains: "word",
		autoFill: false,
		formatItem: function(row, i, max) {
			return i + "/" + max + ": \"" + row.name + "\" [" + row.to + "]";
		},
		formatMatch: function(row, i, max) {
			return row.name + " " + row.to;
		},
		formatResult: function(row) {
			return row.to;
		}
	});

});
</script>	
</head>

<body>

<h1 id="banner"><a href="http://bassistance.de/jquery-plugins/jquery-plugin-autocomplete/">jQuery Autocomplete Plugin</a> Demo</h1>

<div id="content">	
	<form autocomplete="off">
		<p>
			<label>Single City (local):</label>
			<input type="text" id="suggest1" />	
			<input type="text" id="suggest13" />
		</p>				
		<input type="submit" value="Submit" />
	</form>
</body>
</html>
