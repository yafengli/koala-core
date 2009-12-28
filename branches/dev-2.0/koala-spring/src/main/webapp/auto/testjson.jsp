<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>jQuery Autocomplete Plugin</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/js/auto/jquery.autocomplete.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.min.js"></script>
    <script type='text/javascript'
            src="<%=request.getContextPath()%>/resources/js/auto/jquery.autocomplete.min.js"></script>
    <script type="text/javascript">
        $(function() {
            function format(mail) {
                return mail.name + " &lt;" + mail.to + "&gt";
            }

            $("#email").autocomplete('/kp/autojson.ftl', {
                multiple: true,
                dataType: "json",
                parse: function(data) {
                    return $.map(data, function(row) {
                        return {
                            data: row,
                            value: row.name,
                            result: row.name + " <" + row.to + ">"
                        }
                    });
                },
                formatItem: function(item) {
                    return format(item);
                }
            }).result(function(e, item) {
                $("#content").append("<p>selected " + format(item) + "</p>");
            });
        });
    </script>

</head>

<body>

<div id="content">

    <form autocomplete="off">
        <p>
            <label>E-Mail (remote json):</label><input id="email" type="text">
        </p>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
