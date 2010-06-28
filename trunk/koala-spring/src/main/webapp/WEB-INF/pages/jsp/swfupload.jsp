<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp" %>
<html>
    <head>
        <title>SWFUpload Demos - Simple Demo</title>
        <link href="${pageContext.request.contextPath}/resources/upload/css/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="http://www.swfupload.org/swfupload.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/swfupload/swfupload.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/js/swfupload.queue.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/js/fileprogress.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/js/handlers.js"></script>
        <script type="text/javascript">
            var swfu;
            window.onload = function() {               
                var settings = {
                    flash_url : "${pageContext.request.contextPath}/resources/upload/swfupload/swfupload.swf",
                    upload_url: "${pageContext.request.contextPath}/swf/swfupload",
                    post_params: {"sessionid" : "${user}"},
                    file_size_limit : "100 MB",
                    file_types : "*.jpg;*.gif;*.png",
                    file_types_description : "Image Files",
                    file_upload_limit : 100,
                    file_queue_limit : 0,
                    custom_settings : {
                        upload_target : "divFileProgressContainer",
                        progressTarget : "fsUploadProgress",
                        cancelButtonId : "btnCancel"
                    },
                    debug: false,

                    // Button settings
                    button_image_url: "${pageContext.request.contextPath}/resources/upload/images/TestImageNoText_65x29.png",
                    button_width: "60",
                    button_height: "29",
                    button_placeholder_id: "spanButtonPlaceHolder",
                    button_text: '<span class="theFont">选择</span>',
                    button_text_style: ".theFont { font-size: 16; }",
                    button_text_left_padding: 12,
                    button_text_top_padding: 3,

                    // The event handler functions are defined in handlers.js
                    file_queued_handler : fileQueued,
                    file_queue_error_handler : fileQueueError,
                    file_dialog_complete_handler : fileDialogComplete,
                    upload_start_handler : uploadStart,
                    upload_progress_handler : uploadProgress,
                    upload_error_handler : uploadError,
                    upload_success_handler : uploadSuccess,
                    upload_complete_handler : uploadComplete,
                    queue_complete_handler : queueComplete	// Queue plugin event
                };

                swfu = new SWFUpload(settings);
            };
        </script>
    </head>
    <body>
        <div id="content">
            <form id="form1" action="${pageContext.request.contextPath}/views/swf/swfupload" enctype="multipart/form-data" method="post">
                <div class="fieldset flash" id="fsUploadProgress">
                    <span class="legend">上传队列</span>
                </div>
                <div id="divStatus">0个文件被上传</div>
                <div>
                    <span id="spanButtonPlaceHolder"></span>
                    <input id="btnCancel" type="button" value="取消" onclick="swfu.cancelQueue();" disabled="disabled" style="margin-left: 2px; font-size: 8pt; height: 29px;" />
                    <a href="${pageContext.request.contextPath}/swf/swfupload/view" target="_blank">查看</a>
                </div>
            </form>
        </div>
        
    </body>
</html>