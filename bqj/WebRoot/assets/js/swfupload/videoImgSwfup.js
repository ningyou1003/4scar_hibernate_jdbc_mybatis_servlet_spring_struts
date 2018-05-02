var swfu_video,swfu_img,swfu_sourceVideo;
var randomNum1 = Math.random();
var randomNum2 = Math.random();
var randomNum3 = Math.random();
var realRelateId_img = randomNum1;
var realRelateId_video = randomNum2;
var realRelateId_sourceVideo = randomNum3;
var realRelateType = "";


function silhouetteUpload(relateType, relateId_img, relateId_source) {
	if(!relateId_img && !relateId_source) {
		realRelateId_img = randomNum1;
		realRelateId_sourceVideo = randomNum3;
	} else {
		realRelateId_img = relateId_img;
		realRelateId_sourceVideo = relateId_source;
	}
	realRelateType = relateType;
	
	swfu_silhouette = new SWFUpload({
    	upload_url: Public.rootPath() + "/attachment/upload",
          post_params: { "relateType": realRelateType, "relateId": realRelateId_img },
            use_query_string: true,
            // File Upload Settings
            file_size_limit: "2 MB", // 文件大小控制
            file_types: "*.jpg;*.png;*.bmp;*.jpeg",
            file_types_description: "图片",

            file_queue_error_handler: fileQueueError_img,
            file_dialog_complete_handler: fileDialogComplete, //选择好文件后提交
            file_queued_handler: Queuedz_img,
            upload_progress_handler: uploadProgress,
            upload_error_handler: uploadError,
            upload_success_handler: uploadSuccess_img,
            upload_complete_handler: uploadComplete,
            button_placeholder_id: "ButtonPlaceholder_img",
            button_width: 180,
            button_height: 18,
            button_text: '<span class="button">浏览文件…</span>',
            button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
            button_text_top_padding: 0,
            button_text_left_padding: 18,
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor: SWFUpload.CURSOR.HAND,
            button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,

            // Flash Settings
            flash_url: Public.rootPath() + "/swfupload/swfupload.swf",

            custom_settings: {
                upload_target: "divFileProgressContainer"
            },
            // Debug Settings
            debug: false  //是否显示调试窗口
        });
	
	swfu_source = new SWFUpload({
	      upload_url: Public.rootPath() + "/attachment/upload",
	      post_params: { "relateType": realRelateType, "relateId": realRelateId_sourceVideo },
	      use_query_string: true,
	      // File Upload Settings
	      file_size_limit: "200 MB", // 文件大小控制
	      file_types: "*.zip",
	      file_types_description: "压缩包",
	      file_upload_limit: "1",

	      file_queue_error_handler: fileQueueError_sourceVideo,
	      file_dialog_complete_handler: fileDialogComplete, //选择好文件后提交
	      file_queued_handler: Queuedz_sourceVideo,
	      upload_progress_handler: uploadProgress,
	      upload_error_handler: uploadError,
	      upload_success_handler: uploadSuccess_sourceVideo,
	      upload_complete_handler: uploadComplete,
	      button_placeholder_id: "ButtonPlaceholder_sourceVideo",
	      button_width: 180,
	      button_height: 18,
	      button_text: '<span class="button">浏览文件…</span>',
	      button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
	      button_text_top_padding: 0,
	      button_text_left_padding: 18,
	      button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
	      button_cursor: SWFUpload.CURSOR.HAND,
	      button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,

	      // Flash Settings
	      flash_url: Public.rootPath() + "/swfupload/swfupload.swf",

	      custom_settings: {
	          upload_target: "divFileProgressContainer"
	      },
	      // Debug Settings
	      debug: false  //是否显示调试窗口
	  });
	
    attList_video(realRelateId_img, realRelateType,"attList_img");
    attList_video(realRelateId_sourceVideo, realRelateType,"attList_sourceVideo");
	
}

//墙报上传事件
function startUpload_silhouette() {
	swfu_silhouette.startUpload();
}
//墙报素材上传事件
function startUpload_source() {
	swfu_source.startUpload();
}


function videoUpload(relateType, imgId, videoId, sourceVideoId) {
	if(!imgId && !videoId && !sourceVideoId){
		realRelateId_img = randomNum1;
		realRelateId_video = randomNum2;
		realRelateId_sourceVideo = randomNum3;
    }else{
    	realRelateId_img = imgId;
    	realRelateId_video = videoId;
    	realRelateId_sourceVideo = sourceVideoId;
    }
    realRelateType = relateType;
    
    //播放视频对象
    swfu_video= new SWFUpload({
      upload_url: Public.rootPath() + "/attachment/upload",
      post_params: { "relateType": realRelateType, "relateId": realRelateId_video },
      use_query_string: true,
      // File Upload Settings
      file_size_limit: "400 MB", // 文件大小控制
      file_types: "*.mp4;*.flv",
      file_types_description: "视频",
      file_upload_limit: "1",

      file_queue_error_handler: fileQueueError_vd,
      file_dialog_complete_handler: fileDialogComplete, //选择好文件后提交
      file_queued_handler: Queuedz_video,
      upload_progress_handler: uploadProgress,
      upload_error_handler: uploadError,
      upload_success_handler: uploadSuccess_video,
      upload_complete_handler: uploadComplete,
      button_placeholder_id: "ButtonPlaceholder_video",
      button_width: 180,
      button_height: 18,
      button_text: '<span class="button">浏览文件…</span>',
      button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
      button_text_top_padding: 0,
      button_text_left_padding: 18,
      button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
      button_cursor: SWFUpload.CURSOR.HAND,
      button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,

      // Flash Settings
      flash_url: Public.rootPath() + "/swfupload/swfupload.swf",

      custom_settings: {
          upload_target: "divFileProgressContainer"
      },
      // Debug Settings
      debug: false  //是否显示调试窗口
  });
    //alert(123);
    //视频封面对象
    swfu_img = new SWFUpload({
    	
    	upload_url: Public.rootPath() + "/attachment/upload",
          post_params: { "relateType": realRelateType, "relateId": realRelateId_img },
            use_query_string: true,
            // File Upload Settings
            file_size_limit: "2 MB", // 文件大小控制
            file_types: "*.jpg;*.png;*.bmp;*.jpeg",
            file_types_description: "图片",
            file_upload_limit: "1",

            file_queue_error_handler: fileQueueError_img,
            file_dialog_complete_handler: fileDialogComplete, //选择好文件后提交
            file_queued_handler: Queuedz_img,
            upload_progress_handler: uploadProgress,
            upload_error_handler: uploadError,
            upload_success_handler: uploadSuccess_img,
            upload_complete_handler: uploadComplete,
            button_placeholder_id: "ButtonPlaceholder_img",
            button_width: 180,
            button_height: 18,
            button_text: '<span class="button">浏览文件…</span>',
            button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
            button_text_top_padding: 0,
            button_text_left_padding: 18,
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor: SWFUpload.CURSOR.HAND,
            button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,

            // Flash Settings
            flash_url: Public.rootPath() + "/swfupload/swfupload.swf",

            custom_settings: {
                upload_target: "divFileProgressContainer"
            },
            // Debug Settings
            debug: false  //是否显示调试窗口
        });
    

    //源视频对象
    swfu_sourceVideo = new SWFUpload({
      upload_url: Public.rootPath() + "/attachment/upload",
      post_params: { "relateType": realRelateType, "relateId": realRelateId_sourceVideo },
      use_query_string: true,
      // File Upload Settings
      file_size_limit: "700 MB", // 文件大小控制
      file_types: "*.zip",
      file_types_description: "压缩包",
      file_upload_limit: "1",

      file_queue_error_handler: fileQueueError_sourceVideo,
      file_dialog_complete_handler: fileDialogComplete, //选择好文件后提交
      file_queued_handler: Queuedz_sourceVideo,
      upload_progress_handler: uploadProgress,
      upload_error_handler: uploadError,
      upload_success_handler: uploadSuccess_sourceVideo,
      upload_complete_handler: uploadComplete,
      button_placeholder_id: "ButtonPlaceholder_sourceVideo",
      button_width: 180,
      button_height: 18,
      button_text: '<span class="button">浏览文件…</span>',
      button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
      button_text_top_padding: 0,
      button_text_left_padding: 18,
      button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
      button_cursor: SWFUpload.CURSOR.HAND,
      button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,

      // Flash Settings
      flash_url: Public.rootPath() + "/swfupload/swfupload.swf",

      custom_settings: {
          upload_target: "divFileProgressContainer"
      },
      // Debug Settings
      debug: false  //是否显示调试窗口
  });
    //alert(1);
    attList_video(realRelateId_img, realRelateType,"attList_img");
    //alert(12);
    attList_video(realRelateId_video, realRelateType,"attList_video");
    //alert(123);
    attList_video(realRelateId_sourceVideo, realRelateType,"attList_sourceVideo");
};

	/**********************************************************
	 * 上传事件
	 */

//视频封面上传事件
function startUpload_img() {
	swfu_img.startUpload();
}
//视频上传事件
function startUpload_video() {
	swfu_video.startUpload();
}
//视频原件上传事件
function startUpload_sourceVideo() {
	swfu_sourceVideo.startUpload();
}

	/**********************************************************
	 * 
	 * 	选择后触发事件
	 */

//视频封面选择后触发的事件
function Queuedz_img(file,status) {
	addReadyFileInfo_video(file.id,file.name,"成功加载到上传队列","infoTable_img");
}
//播放视频选择后触发事件
function Queuedz_video(file) {
	addReadyFileInfo_video(file.id,file.name,"成功加载到上传队列","infoTable_video");
}
//视频原件选择后触发的事件
function Queuedz_sourceVideo(file,status) {
	addReadyFileInfo_video(file.id,file.name,"成功加载到上传队列","infoTable_sourceVideo");
}

function addReadyFileInfo_video(fileid,fileName,message,infoTables,status){
	//用表格显示
	var infoTable = document.getElementById(infoTables);
	var row = infoTable.insertRow();
	row.id = fileid;
	var col1 = row.insertCell();
	var col2 = row.insertCell();
	var col3 = row.insertCell();
	var col4 = row.insertCell();
	col4.align = "right";
	col1.innerHTML = message+" : ";
	col2.innerHTML = fileName;
	if(status!=null&&status!=""){
		col3.innerHTML="<font color='red'>"+status+"</font>";
	}else{
		col3.innerHTML="";
	}
	col4.innerHTML = "<a href='javascript:deleteFile_video(\""+fileid+"\",\""+infoTables+"\")'>删除</a>";
	col1.style.width="150";
	col2.style.width="250";
	col3.style.width="80";
	col4.style.width="50";
}

function deleteFile_video(fileId,infoTables){
	//用表格显示
	var infoTable = document.getElementById(infoTables);
	var row = document.getElementById(fileId);
	infoTable.deleteRow(row.rowIndex);
}

	/***********************************
	 * 	完成事件
	 */

//上传视频封面完成事件
function uploadSuccess_img() {
    $("#thumbnail_img").find("tr").empty();
    attList_video(realRelateId_img, realRelateType,"attList_img");//加载附件列表
}
//上传视频完成事件
function uploadSuccess_video() {
    $("#thumbnail_video").find("tr").empty();
    attList_video(realRelateId_video, realRelateType,"attList_video"); //加载附件列表
}
//上传视频原件完成事件
function uploadSuccess_sourceVideo() {
    $("#thumbnail_sourceVideo").find("tr").empty();
    attList_video(realRelateId_sourceVideo, realRelateType,"attList_sourceVideo");//加载附件列表
}


//获取视频封面
function attList_video(relateId,relateType,attList) {
    Public.ajaxPost(Public.rootPath() + '/attachment/list', {//获取附件
        'relateId': relateId,
        'relateType': relateType
    }, function (json) {
        if (json.status == 001) {
            var p = json.data;
            var len = p.length;
            if (len > 0) {
                $("#attTable").css("display", "table");
                var strHtml = "";
                for (var i = 0; i < len; i++) {
                    /*if (p[i].name.indexOf("pdf") >= 0 || p[i].name.indexOf("doc") >= 0 || p[i].name.indexOf("docx") >= 0 || p[i].name.indexOf("xls") >= 0 || p[i].name.indexOf("xlsx") >= 0) {
                        strHtml += "<li><a href=" + p[i].onlineEdit + " class='attlistC'>" + p[i].name + "</a>&nbsp;&nbsp;<a href=" + p[i].url + " >下载</a>&nbsp;&nbsp;<a onclick=delattList('" + p[i].id + "') class='attlistC'>删除</a></li>";
                    }
                    else {
                        strHtml += "<li><a href=" + p[i].url + " class='attlistC'>" + p[i].name + "</a>&nbsp;&nbsp;<a href=" + p[i].url + " >下载</a>&nbsp;&nbsp;<a onclick=delattList('" + p[i].id + "') class='attlistC'>删除</a></li>";

                    }*/
                	//alert(p[i].name);
                	
                	if (p[i].name.indexOf(".jpg")>0 || p[i].name.indexOf(".JPG")>0 
                		|| p[i].name.indexOf(".png")>0 || p[i].name.indexOf(".PNG")>0
                		|| p[i].name.indexOf(".gif")>0 || p[i].name.indexOf(".GIF")>0) {
                		strHtml += "<li style=\"float:left;\"><img src=\"" + p[i].url + "\" style=\"heigth:100px;width:100px;\" title=\""+p[i].name+"\" alt=\""+p[i].name+"\" />";
                		//http://192.168.1.74:8080/bqj/attachment/download?attachmentId=d522ff72fa68481d9557ad5d69805df0
                		//strHtml += "<li><img src=\"http://192.168.1.74:8080/bqj/app/culture/videoManage//2017-05-05 16:19:50的屏幕截图.png\"/>";
					}else{
						strHtml += "<li><a href=\"" + p[i].url + "\" class='attlistC'>" + p[i].name + "</a>";
					}
                    strHtml += "&nbsp;&nbsp;<a target=\"_blank\" href=\"" + p[i].url + "\" >下载</a>&nbsp;&nbsp;<a onclick=delattList_video('" + p[i].keyid + "') class='attlistC' style=\"cursor: pointer;\">删除</a></li>";
                }
                $("#"+attList).html(strHtml);
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



/*function AttList() {
    Public.ajaxPost(Public.rootPath() + '/silhouette/list', {//获取附件
        'relateId': realRelateIds,
        'relateType': realRelateTypes
    }, function (json) {
        if (json.status == 001) {
            var p = json.data;
            var len = p.length;
            if (len > 0) {
                $("#attTable").css("display", "table");
                var strHtml = "";
                for (var i = 0; i < len; i++) {
                    if (p[i].name.indexOf("pdf") >= 0 || p[i].name.indexOf("doc") >= 0 || p[i].name.indexOf("docx") >= 0 || p[i].name.indexOf("xls") >= 0 || p[i].name.indexOf("xlsx") >= 0) {
                        strHtml += "<li><a href=" + p[i].onlineEdit + " class='attlistC'>" + p[i].name + "</a>&nbsp;&nbsp;<a href=" + p[i].url + " >下载</a>&nbsp;&nbsp;<a onclick=delattList('" + p[i].id + "') class='attlistC'>删除</a></li>";
                    }
                    else {
                        strHtml += "<li><a href=" + p[i].url + " class='attlistC'>" + p[i].name + "</a>&nbsp;&nbsp;<a href=" + p[i].url + " >下载</a>&nbsp;&nbsp;<a onclick=delattList('" + p[i].id + "') class='attlistC'>删除</a></li>";

                    }
                }
                $("#attLists").html(strHtml);
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
}*/

function infoAttList(relateType, relateId) {
    Public.ajaxPost(Public.rootPath() + '/attachment/list', {//获取附件
        'relateId': relateId,
        'relateType': relateType
    }, function (json) {
        if (json.status == 001) {
            var p = json.data;
            var len = p.length;
            if (len > 0) {
                $("#attTable").css("display", "table");
                var strHtml = "";
                for (var i = 0; i < len; i++) {
                    strHtml += "<li><a href=" + p[i].url + " class='attlistC'>" + p[i].name + "</a>&nbsp;&nbsp;<a href=" + p[i].url + " >下载</a></li>";
                }
                $("#attLists").html(strHtml);
            }
            else {
                $("#attTable").css("display", "none");
            }
        }
    });
}

	/******************************************
	 * 
	 * 选择错误触发事件
	 */

function fileQueueError_img(file, errorCode, message) {
	fileQueueError_video(file, errorCode, message, "infoTable_img");
}
function fileQueueError_vd(file, errorCode, message) {
	fileQueueError_video(file, errorCode, message, "infoTable_video");
}
function fileQueueError_sourceVideo(file, errorCode, message) {
	fileQueueError_video(file, errorCode, message, "infoTable_sourceVideo");
}

function fileQueueError_video(file, errorCode, message, infoTables) {
	try {
		var imageName = "<font color='red'>文件上传错误</font>";
		var errorName = "";
		if (errorCode === SWFUpload.errorCode_QUEUE_LIMIT_EXCEEDED) {
			errorName = "You have attempted to queue too many files.";
		}

		if (errorName !== "") {
			alert(errorName);
			return;
		}
		
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			imageName = "<font color='red'>文件大小为0</font>";
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			imageName = "<font color='red'>文件大小超过限制</font>";
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
		default:
			alert(message);
			break;
		}
		addReadyFileInfo_video(file.id,file.name,imageName,infoTables,"无法上传");

	} catch (ex) {
		this.debug(ex);
	}
}

	
//删除
function delattList_video(fileid) {
    Public.ajaxPost(Public.rootPath() + '/attachment/delete?attachmentId=' + fileid, "", function (json) {
        if (json.status == true) {
            top.openeasydialog("success", "附件删除成功");
            attList_video(realRelateId_img, realRelateType,"attList_video");
            attList_video(realRelateId_video, realRelateType,"attList_img");
            attList_video(realRelateId_sourceVideo, realRelateType,"attList_sourceVideo");
        }
    });
}

