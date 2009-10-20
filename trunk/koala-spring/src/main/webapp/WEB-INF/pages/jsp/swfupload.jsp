<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp" %>
<html>
    <head>
        <title>SWFUpload Demos - Simple Demo</title>
        <link href="<%=request.getContextPath()%>/resources/upload/css/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="http://www.swfupload.org/swfupload.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/swfupload/swfupload.js"></script>
        <%--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/js/swfupload.queue.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/js/fileprogress.js"></script>--%>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/upload/js/handlers.js"></script>
        <script type="text/javascript">
            var swfu;

            window.onload = function() {
                var settings1={
                    // Backend Settings
                    upload_url: "<%=request.getContextPath()%>/swfupload.ftl",
                    post_params: {"sessionid" : "${user}"},

                    // File Upload Settings
                    file_size_limit : "2 MB",	// 2MB
                    file_types : "*.jpg;*.gif;*.png",
                    file_types_description : "JPG Images",
                    file_upload_limit : "0",

                    // Event Handler Settings - these functions as defined in Handlers.js
                    //  The handlers are not part of SWFUpload but are part of my website and control how
                    //  my website reacts to the SWFUpload events.
                    file_queue_error_handler : fileQueueError,
                    file_dialog_complete_handler : fileDialogComplete,
                    upload_progress_handler : uploadProgress,
                    upload_error_handler : uploadError,
                    upload_success_handler : uploadSuccess,
                    upload_complete_handler : uploadComplete,

                    // Button Settings
                    button_image_url : "<%=request.getContextPath()%>/resources/upload/images/SmallSpyGlassWithTransperancy_17x18.png",
                    button_placeholder_id : "spanButtonPlaceholder",
                    button_width: 180,
                    button_height: 18,
                    button_text : '<span class="button">Select Images <span class="buttonSmall">(2 MB Max)</span></span>',
                    button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
                    button_text_top_padding: 0,
                    button_text_left_padding: 18,
                    button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
                    button_cursor: SWFUpload.CURSOR.HAND,

                    // Flash Settings
                    flash_url : "<%=request.getContextPath()%>/resources/upload/swfupload/swfupload.swf",
                    custom_settings : {
                        upload_target : "divFileProgressContainer"
                    },

                    // Debug Settings
                    debug: false
                };
                var settings = {
                    flash_url : "<%=request.getContextPath()%>/resources/upload/swfupload/swfupload.swf",
                    upload_url: "<%=request.getContextPath()%>/swfupload.ftl",
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
                    button_image_url: "<%=request.getContextPath()%>/resources/upload/images/TestImageNoText_65x29.png",
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

                <%--swfu = new SWFUpload(settings);--%>
                swfu = new SWFUpload(settings1);
            };
        </script>
    </head>
    <body>
        <div id="content">
            <h2>Simple Demo</h2>
            <form id="form1" action="index.php" method="post" enctype="multipart/form-data">
                <div class="fieldset flash" id="fsUploadProgress">
                    <span class="legend">上传队列</span>
                </div>
                <div id="divStatus">0个文件被上传</div>
                <div>
                    <span id="spanButtonPlaceHolder"></span>
                    <input id="btnCancel" type="button" value="Cancel All Uploads" onclick="swfu.cancelQueue();" disabled="disabled" style="margin-left: 2px; font-size: 8pt; height: 29px;" />
                </div>
                <div id="divFileProgressContainer" style="height: 75px;"></div>
            </form>
        </div>       
    </body>
</html>