var vm = avalon.define({
	$id : "areaaddCtrl",
	data : {
		id:"",
//		code : "",
//		name : "",
		regioncode : "",//区域编码
		region : "",//区域名称
		parentcode : "",//父级区域编码
		parent : "",//父级区域名称
		poperlevel : "",//父级区域等级
		demonstration : "",
		parentarea:"",
		operlevel : "",
		openzoneflag : "",
		bbw_flag : "",
		orderid:""
	},
	areaId : "",
	parentList : {},
	//选择点击的类型，分别是成员单位及其职责，工作小组(工作站)名单，工作小组办公室成员名单，文化综合支队
	memberType : "",
	member0: [],
	memberIds0: [],
	member: [],
	memberIds: [],
	member2: [],
	memberIds2: [],
	member3: [],
	memberIds3: [],

	submit : function(){
		$("#base_form").trigger("validate");
	}
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData(); //初始化数据
	},
	
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["areaId"] != null
				&& request.queryString["areaId"].length > 0) {
			vm.areaId = request.queryString["areaId"];
			Public.ajaxPost(Public.rootPath() + "/area/editData", "areaId="
					+ vm.areaId, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					loadAllParentArea(json.data.regioncode);
					if(vm.data.demonstration==1){
						vm.data.demonstration='1';
					}else{
						vm.data.demonstration='0';
					}
					loadMember(0);//成员单位
					loadMember(1);
					loadMember(2);
					loadMember(3);
				} else {
					top.openeasydialog("error", "获取区域信息失败");
				}
			});
		} else {
			
		}
	},
	
	initEvent : function() {
		$("#btback").click(function() {
			window.history.go(-1); 
			 window.location.reload(); 
		});
		// ------------上级区域选择
		$("#ParentList").click(
				function() {
					top.openbuttondialog(
							"选择上级区域", 400, 400,
							"system/areaManage/regionTreePick.html", false,
							function(item, dialog) {
								vm.data.poperlevel = dialog.frame.$("#operlevel").val();
								if(vm.data.poperlevel==4){
									top.openeasydialog("warn", "该区域不可添加下级区域");
								}else{
									vm.data.parentcode = dialog.frame.$("#regioncode").val();
									vm.data.parent = dialog.frame.$("#region").val();
									dialog.hide();
									vm.data.operlevel = ++vm.data.poperlevel ;
									$("#ParentList").isValid();
								}
								
							});
				});
	},

	initDom : function() {
		document.getElementById('areaName').focus();//在页面显示后 光标会在areaName文本框里。获得焦点
	}

};
function clickOperLevel(){
	if(vm.data.parentcode.length==2&&vm.data.parentcode!="00"){
		if(vm.data.operlevel==1){
			top.openeasydialog("error", "上级是省级区域，请选择省级以下的区域级别");
			vm.data.operlevel=2;
		}
	}else if(vm.data.parentcode.length==4){
		if(vm.data.operlevel==1||vm.data.operlevel==2){
			top.openeasydialog("error", "上级是市级区域，请选择市级以下的区域级别");
			vm.data.operlevel=3;
		}
	}else if(vm.data.parentcode.length==7){
		
	}
	
}

function postData() {
	
	var url = null;
	if (vm.areaId != "" && vm.areaId != null && vm.areaId != undefined) {
		url = Public.rootPath() + "/area/edit";
	} else {
		url = Public.rootPath() + "/area/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(json) {
		var request = Public.urlRequest();
		keyid = request.queryString["keyid"];
		if (json.status == 001) {
			var dialog = top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/manage/manage/areaList.html?keyid="+keyid;
				parent.cums_leftFrame.location.reload();
				window.parent.parent.frames["rightFrame"].frames["showmessage"].location.reload();
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});

}

function initValidator() {
	$("#base_form").validator({
		rules : {
			regionCode : [/^[0-9]+$/,"区域编码只能由数字组成"]
		},
		messages : {
			required : "请填写{0}"
		},
		display : function(e) {
			var text = $(e).closest(".row-item").find("label").text().trim();
			return text.substring(0, text.length - 2);
		},
		valid : function() {
			postData();
		},
		ignore : ":hidden",
		theme : "yellow_bottom",
		timely : 1,
		stopOnError : true
	});
}

$(document).ready(function(e) {
	// 需要先初始化表单验证配置
	initValidator();
	THISPAGE.init();
	attList();
	
	initUpload0(vm.areaId, "0");
	initUpload1(vm.areaId, "1");
	initUpload2(vm.areaId, "2");
	initUpload3(vm.areaId, "3");
});

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
        file_size_limit: "5MB", // 文件大小控制
        file_types: "*.xls;*.xlsx",
        file_types_description: "Excel Files",
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

//获取附件
function attList() {
    Public.ajaxPost(Public.rootPath() + '/attachment/list', {//获取附件
        'relateId': "manageTemplate",
        'relateType': "manageTemplate"
    }, function (json) {
        if (json.status == 001) {
            var p = json.data;
            var len = p.length;
            if (len > 0) {
            	var a = "<a target=\"_blank\" href=\""+p[0].url+"\" ><span><img src=\"../../../images/download.png\" /></span>下载模板</a>";
            	$("#download").html(a);
            }
        }
    });
}

function addMember(type) {
	var title = "工作小组（工作站）成员名单";
	var url = "manage/manage/member.html";
	if(type==0){
		title = "成员单位及其职责";
		url = "manage/manage/member0.html";
	}
	if(type==2){
		title = "工作小组办公室成员名单";
		url = "manage/manage/member2.html";
	}
	if(type==3){
		title = "文化市场综合执法支队";
		url = "manage/manage/member3.html";
	}
	top.openbuttondialog(title,
			500, 600,
			url, false, function (item, dialog) {
				Public.ajaxPost(Public.rootPath() + '/manage/addMember', {
					'regionCode': vm.data.regioncode,
					'job': dialog.frame.$("#job").val(),
					'name': dialog.frame.$("#name").val(),
					'job2': dialog.frame.$("#job2").val(),
					'phone': dialog.frame.$("#phone").val(),
					'remark': dialog.frame.$("#remark").val(),
					'orderid': dialog.frame.$("#orderid").val(),
					'type': type
				}, function (json) {
					if (json.status == "001") {
						dialog.close();
						loadMember(type);
					} else {
						alert(json.msg);
					}
				});
			});
}

function loadMember(type) {
	Public.ajaxPost(Public.rootPath() + '/manage/loadMember', {
		'regionCode': vm.data.regioncode,
		'type': type
	}, function (json) {
		if (json.status == "001") {
			if(type==0){
				vm.member0 = json.data;
			}
			if(type==1){
				vm.member = json.data;
			}
			if(type==2){
				vm.member2 = json.data;
			}
			if(type==3){
				vm.member3 = json.data;
			}
		} else {
			alert(json.msg);
		}
	});
}

function editMember(type, keyid) {
	var msg,title,Ids,type;
	if(type==0){
		Ids = vm.memberIds0;
		title = "成员单位及其职责";
		type = 0;
		page = "member0.html";
	}
	if(type==1){
		Ids = vm.memberIds;
		title = "工作小组（工作站）成员名单";
		type = 1;
		page = "member.html";
	}
	if(type==2){
		Ids = vm.memberIds2;
		title = "工作小组办公室成员名单";
		type = 2;
		page = "member2.html";
	}
	if(type==3){
		Ids = vm.memberIds3;
		title = "文化市场综合执法支队";
		type = 3;
		page = "member3.html";
	}
	/*if (Ids.length == 0) {
		msg = "请选择要修改的成员";
		top.openeasydialog("warn", msg);
		return;
	} else if (Ids.length > 1) {
		msg = "请不要同时选择多个成员";
		top.openeasydialog("warn", msg);
		return;
	}*/
	top.openbuttondialog(title,
			450, 500,
			"manage/manage/"+page+"?option=edit&id=" + keyid, false, function (item, dialog) {
				Public.ajaxPost(Public.rootPath() + '/manage/addMember', {
					'keyId':keyid,
					'regionCode': vm.data.regioncode,
					'job': dialog.frame.$("#job").val(),
					'name': dialog.frame.$("#name").val(),
					'job2': dialog.frame.$("#job2").val(),
					'phone': dialog.frame.$("#phone").val(),
					'remark': dialog.frame.$("#remark").val(),
					'orderid': dialog.frame.$("#orderid").val(),
					'type': type
				}, function (json) {
					if (json.status == "001") {
						dialog.close();
						loadMember(type);
					} else {
						alert(json.msg);
					}
				});
			});
}

function deleteMember(type) {
	var msg,title,Ids;
	if(type==0){
		Ids = vm.memberIds0;
		title = "成员单位及其职责";
	}
	if(type==1){
		Ids = vm.memberIds;
		title = "工作小组（工作站）成员名单";
	}
	if(type==2){
		Ids = vm.memberIds2;
		title = "工作小组办公室成员名单";
	}
	if(type==3){
		Ids = vm.memberIds3;
		title = "文化市场综合执法支队";
	}
	if (Ids.length == 0) {
		msg = "请选择要删除的成员";
		top.openeasydialog("warn", msg);
		return;
	} else {
		msg = "确认要删除成员吗？";
	}
	top.openeasydialog("warning", msg, function (t) {
		if (t) {
			Public.ajaxPost(Public.rootPath() + "/manage/deleteMember", "keyids=" + Ids, function (json) {
				if (json.status == '001') {
					loadMember(type);
				} else {
//					window.parent.parent.parent.parent.parent.openeasydialog("error", "删除出错！");
					top.openeasydialog("error", "删除出错！");
				}
			});
		}
	});
}

function initUpload0(regionCode, type) {
	
    swfu0 = new SWFUpload({
        upload_url: Public.rootPath() + "/manage/importExcel",
        post_params: { "regionCode": regionCode, "type": type },
        use_query_string: true,
        // File Upload Settings
        file_size_limit: "5MB", // 文件大小控制
        file_types: "*.xls;*.xlsx",
        file_types_description: "Excel Files",
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
        button_text: '<span class="button" >选择文件…</span>',
        button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
        button_text_top_padding: 0,
        button_text_left_padding: 18,
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
    
};

/*2016-12-2 Excel导入转html 开始*/
function initUpload1(regionCode, type) {
	
    swfu1 = new SWFUpload({
        upload_url: Public.rootPath() + "/manage/importExcel",
        post_params: { "regionCode": regionCode, "type": type },
        use_query_string: true,
        // File Upload Settings
        file_size_limit: "5MB", // 文件大小控制
        file_types: "*.xls;*.xlsx",
        file_types_description: "Excel Files",
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
        button_text: '<span class="button" >选择文件…</span>',
        button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
        button_text_top_padding: 0,
        button_text_left_padding: 18,
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
    
};
function initUpload2(regionCode, type) {
	
    swfu2 = new SWFUpload({
        upload_url: Public.rootPath() + "/manage/importExcel",
        post_params: { "regionCode": regionCode, "type": type },
        use_query_string: true,
        // File Upload Settings
        file_size_limit: "5MB", // 文件大小控制
        file_types: "*.xls;*.xlsx",
        file_types_description: "Excel Files",
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
        button_text: '<span class="button" >选择文件…</span>',
        button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
        button_text_top_padding: 0,
        button_text_left_padding: 18,
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
    
};
function initUpload3(regionCode, type) {
	
    swfu3 = new SWFUpload({
        upload_url: Public.rootPath() + "/manage/importExcel",
        post_params: { "regionCode": regionCode, "type": type },
        use_query_string: true,
        // File Upload Settings
        file_size_limit: "5MB", // 文件大小控制
        file_types: "*.xls;*.xlsx",
        file_types_description: "Excel Files",
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
        button_text: '<span class="button" >选择文件…</span>',
        button_text_style: '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt;} .buttonSmall { font-size: 10pt; }',
        button_text_top_padding: 0,
        button_text_left_padding: 18,
        
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
    
};
//动态给import块中的table赋id为infoTable

function clickImportBtn(type){
	vm.memberType = type;
	
	switch(type){
	case 0 :
		
		$(".sel_div table").attr("name","table0");
		if ($("#sel_div0").is(":hidden")) {
			$("#sel_div0").fadeIn();
		} else {
			$("#sel_div0").fadeOut();
		}
		//加载SWFUpload插件，实现文件上传flash按钮
		initUpload0(vm.areaId, vm.memberType);
		break;
	case 1:
		
		$(".sel_div table").attr("name","table1");
		if ($("#sel_div1").is(":hidden")) {
			$("#sel_div1").fadeIn();
		} else {
			$("#sel_div1").fadeOut();
		}
		initUpload1(vm.areaId, vm.memberType);
		break;
	case 2:
		
		$(".sel_div table").attr("name","table2");
		if ($("#sel_div2").is(":hidden")) {
			$("#sel_div2").fadeIn();
		} else {
			$("#sel_div2").fadeOut();
		}
		initUpload2(vm.areaId, vm.memberType);
		break;
	case 3:
		
		$(".sel_div table").attr("name","table3");
		if ($("#sel_div3").is(":hidden")) {
			$("#sel_div3").fadeIn();
		} else {
			$("#sel_div3").fadeOut();
		}
		initUpload3(vm.areaId, vm.memberType);
		break;
	default:
		break;
	}
	
}

function loadAllParentArea(regioncode){
	
    Public.ajaxPost(Public.rootPath() + '/area/loadAllParentArea?regioncode=' + regioncode, {
}, function (json) {
    if (json.status == 1) {
    	var str="";
        for(var i=0;i<json.data.length-1;i++){
        	str+=json.data[i].region+">";
        }
        $("#parentArea").html(str);
    }
});
}

function startUploadFile() {
    swfu0.startUpload();
    //uploadSuccess();
}
function startUploadFile1() {
    swfu1.startUpload();
}
function startUploadFile2() {
    swfu2.startUpload();
}
function startUploadFile3() {
    swfu3.startUpload();
}
/*2016-12-2 Excel导入转html 结束*/

function uploadSuccess(){
	//上传成功将获取的文件列表删除
	 $("#thumbnails table").find("tr").empty();
	$(".sel_div").fadeOut();

	loadMember(vm.memberType);
}


function exportExcel(type){
	window.location.href=Public.rootPath() + "/manage/exportExcel?type=" + type + "&regionCode=" + vm.areaId;
}

function exportExcelModel(type){
	window.location.href=Public.rootPath()+"/manage/exportExcelModel?type="+type+"&regionCode="+vm.areaId;
}
