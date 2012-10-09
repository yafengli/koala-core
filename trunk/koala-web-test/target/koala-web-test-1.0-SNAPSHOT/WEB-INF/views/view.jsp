<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
/* ラベル Y軸 */
.jQchart-labelY-canvasMyID {
	font-size: 12px;
}

/* label Data */
.jQchart-labelData-canvasMyID {
	font-size: 8px;
}

.jQchart-labelYunit {
	width: 100px;
}
</style>
<script type="text/javascript"
	src="http://lib.sinaapp.com/js/jquery/1.6/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/excanvas/excanvas-compressed.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.jqchart.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var charset = {
		config : {
			title : '用中文会死啊',
			labelX : [ "月", "火", "水", "木", "金", "土", "日" ],
			scaleY : {
				min : 0,
				max : 1000,
				gap : 200
			},
			line : {
				lineWidth : [ 2, 5 ],
				strokeStyle : [ 'red', 'green' ]
			}
		},
		data : []
	};
	$(document).ready(function() {
		$.getJSON('${pageContext.request.contextPath}/list', function(item) {
			$("#_aj").text(item[0] + "|" + item[1]);
			charset.data.push(item);
			$('#canvasMyID').jQchart(charset);
		});
	});
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/highcharts/highcharts.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/highcharts/js/themes/gray.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var chart1 = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				type : 'bar'
			},
			title : {
				text : '用中文能死啊'
			},
			xAxis : {
				categories : [ '打', '酱', '油' ]
			},
			yAxis : {
				title : {
					text : '用中文不能死'
				}
			},
			series : [ {
				name : '珍妮弗.安妮斯顿',
				data : [ 23, 19, 34 ]
			}, {
				name : '肖恩.康纳利',
				data : [ 51, 27, 43 ]
			} ]
		});
	});
</script>
<title>JSP Page</title>

</head>
<body>
	<h1>This is VIEW!</h1>
	<canvas id="canvasMyID" height="200"></canvas>
	<div id="_aj"
		style="color: green; font-weight: bold; background-color: #ffe4c4; width: 500">This
		is temp!</div>
	<div id="container" style="width: 100%; height: 400px"></div>
</body>
</html>
