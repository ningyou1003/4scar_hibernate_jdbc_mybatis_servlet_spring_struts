var swfu;
var randomNum = Math.random();
var realRelateId = randomNum;
var realRelateType = "";

function initUpload(relateType, relateId) {
    if(!relateId){
        realRelateId = randomNum;
    }else{
        realRelateId = relateId;
    }

    realRelateType = relateType;

    swfu = new SWFUpload({
        upload_url: Public.rootPath() + "/attachment/upload",
        post_params: { "relateType": relateType, "relateId": realRelateId },
        use_query_string: true,
        // File Upload Settings
        file_size_limit: "500 MB", // 文件大小控制
        file_types: "*.*",
        file_types_description: "All Files",
        file_upload_limit: "0",

        file_queue_error_handler: fileQueueError,
        file_dialog_complete_handler: fileDialogComplete, //选择好文件后提交
        file_queued_handler: fileQueued,
        upload_progress_handler: uploadProgress,
        upload_error_handler: uploadError,
        upload_success_handler: uploadSuccess,
        upload_complete_handler: uploadComplete,
        button_placeholder_id: "spanButtonPlaceholder",
        button_width: 180,
        button_height: 18,
        button_text: '<span class="button">浏览文件…</span>',
        button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
        button_text_top_padding: 0,
        button_text_left_padding: 18,
        button_disabled:true,
        button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
        button_cursor: SWFUpload.CURSOR.HAND,

        // Flash Settings
        flash_url: Public.rootPath() + "/swfupload/swfupload.swf",

        custom_settings: {
            upload_target: "divFileProgressContainer"
        },
        // Debug Settings
        debug: false  //是否显示调试窗口
    });

    attList();
};
function startUploadFile() {
    swfu.startUpload();
}
//上传完成事件
function uploadSuccess() {
    $("#thumbnails").find("tr").empty();
    attList(); //加载附件列表
}
//获取附件
function attList() {
    Public.ajaxPost(Public.rootPath() + '/attachment/list', {//获取附件
        'relateId': realRelateId,
        'relateType': realRelateType
    }, function (json) {
        if (json.status == 001) {
            var p = json.data;
            var len = p.length;
            if (len > 0) {
                $("#attTable").css("display", "table");
                var strHtml = "";
                for (var i = 0; i < len; i++) {
                    if (p[i].name.indexOf("pdf") >= 0 || p[i].name.indexOf("doc") >= 0 || p[i].name.indexOf("docx") >= 0 || p[i].name.indexOf("xls") >= 0 || p[i].name.indexOf("xlsx") >= 0) {
                        strHtml += "<li><a href=" + p[i].onlineEdit + " class='attlistC'>" + p[i].name + "</a></li>";
                    }
                    else {
                        strHtml += "<li><a href=" + p[i].url + " class='attlistC'>" + p[i].name + "</a>&nbsp;&nbsp;<a href=" + p[i].url + " >下载</a></li>";

                    }
                }
                $("#attList").html(strHtml);
            }
            else {
                $("#attTable").css("display", "none");
            }
            if (vm.todostate == 1 || vm.todostate == 3 || vm.todostate == 4 || vm.todostate == 5 || vm.todostate == 6) {
                $(".attlistC").attr("href", "javascript:#");
                $(".attlistC").attr("onclick", "javascript:#");
                $(".attlistC").removeAttr("onclick");
                $(".attlistC").css("color", "#999");
            }
        }

    });
}
//删除附件
function delattList(fileid) {
    Public.ajaxPost(Public.rootPath() + '/attachment/delete?attachmentId=' + fileid, "", function (json) {
        if (json.status == true) {
            top.openeasydialog("success", "附件删除成功");
            attList();
        }
    });
}