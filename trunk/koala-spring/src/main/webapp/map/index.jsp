<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Control Initialization</title>
    <script src="<%=request.getContextPath()%>/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true_or_false&amp;key=ABQIAAAAp-dWSeKyIx1T5fUUpUsJIhRR56aN24vpfXkrPw8JXcyA4e9JxBSWegw2RLpfLvK0VJjVwOG5MLUxMA" type="text/javascript"></script>
    <script type="text/javascript">
        //<![CDATA[
        var map ;
        // 为所有的标注创建一个“基准”图标，并指定它的阴影、尺寸、锚点等
        var baseIcon = new GIcon();
        baseIcon.shadow = "http://ditu.google.com/mapfiles/shadow50.png";
        baseIcon.iconSize = new GSize(20, 34);
        baseIcon.shadowSize = new GSize(37, 34);
        baseIcon.iconAnchor = new GPoint(9, 34);
        baseIcon.infoWindowAnchor = new GPoint(9, 2);
        baseIcon.infoShadowAnchor = new GPoint(18, 25);

        function initialize() {
            if (GBrowserIsCompatible()) {
                map = new GMap2(document.getElementById("map_canvas"),
                { size: new GSize(640, 320) });
                map.setCenter(new GLatLng(31.83583333, 119.7951389), 11);
                var customUI = map.getDefaultUI();
                // Remove MapType.G_HYBRID_MAP
                customUI.maptypes.hybrid = false;
                map.setUI(customUI);
            }
        }
        // 创建一个标注，它的气泡提示窗口会根据标注编号显示对应的英文字母
        function createMarker(item, index) {
            //创建一个坐标点
            var point = new GLatLng(item.longitude, item.latitude);
            // 利用我们的图标类，为这个标注创建一个带字母的图标
            var letter = String.fromCharCode("A".charCodeAt(0) + index);
            var icon = new GIcon(baseIcon);
            icon.image = "http://ditu.google.com/mapfiles/marker" + letter + ".png";
            var marker = new GMarker(point, icon);

            GEvent.addListener(marker, "click", function() {
                marker.openInfoWindowHtml(item.msg);
            });
            return marker;
        }
        $(document).ready(function() {
            initialize();
            $.ajax({
                type:"GET",
                url: "<%=request.getContextPath()%>/mapjson.ftl",
                dataType:'json',
                success: function(data) {
                    $("#results").append(data);
                    $.each(data.items, function(i, item) {
                        // 在地图的位置添加标注
                        map.addOverlay(createMarker(item, i));
                        //alert(item.longitude + "|" + item.latitude + "|" + item.msg);
                    });
                },
                error:function (data) {
                    alert("ERROR:地图数据异常，请稍后访问！");
                }});
        });
        //]]>
    </script>
</head>
<body onunload="GUnload()">
<div id="map_canvas" style="width: 640px; height: 320px"></div>
<p/>
<div id="results">
</div>
</body>
</html> 

