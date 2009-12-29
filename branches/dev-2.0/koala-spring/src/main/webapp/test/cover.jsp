<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
	<head>
		<style>
			#sh {
				position:fixed;
				top:0;
				left:0;
				bottom:0;
				right:0;
				z-index:999;
				/*透明*/
				filter:alpha(opacity=60);     /* IE */ 
				opacity: 0.6;     /* 支持CSS3的浏览器（FF 1.5也支持）*/
				-moz-opacity: 0.6; /* Moz + FF */ 
			}
			#ct{	
				position:fixed; 
				top:30%;
				left:50%;
			}

		</style>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery/jquery.text.min.js" ></script>
		<script type="text/javascript">
			var conf={'dvid':'#sh', 'dvid_bg':'#cccccc'};
			$(document).ready(function(){
				$("#open").click(function(){
					$.over_lay(conf);
				});
				$("#close").click(function(){
					$.over_close(conf);
				});
			});
</script>
</head>
<body>
	<div id="hd">
		<a href="#" id="open">fuck</a>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<h3>Hello World!</h3>
	</div>
	<div id="sh" style="display:none"> 
		<div id="ct">
			<a href="#" id="close"><img src="loginWait.gif"/></a>
		</div>	
	</div>	
</body>
</html>
