var vm = avalon.define({
	$id : "noticeAddCtrl",
	data : {
		keyid : "",
		title : "",
		content : "",
		publishtime : "",
		randomNum : randomNum
	},
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		$("#base_form").trigger("validate");
	}
});
var kindeditor;

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "noticeList.html";
		});
		$("#add").click(function() {
			window.location = "noticeAdd.html" ;
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/docNotice/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					kindeditor.html(vm.data.content);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} 
		/*else {
			
			vm.data.publishtime = Public.getNowFormatDate();
            $("#publishtime").val(vm.data.publishtime);
		}*/

	},
	initDom : function() {
	}
};
function postData() {
	var url=null;
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/docNotice/edit";
	}else{
		url= Public.rootPath() + "/docNotice/add";
	}
	vm.data.content = kindeditor.html();
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/docNotice/noticeManage/noticeList.html";
				window.parent.parent.frames["rightFrame"].frames["showmessage"].location.reload();
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
}
function initValidator() {
	$("#base_form").validator({
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
function KE(){
	kindeditor = KindEditor.create('textarea[name="content"]', {
		resizeType : 1,
		uploadJson : Public.rootPath() + "/ke/fileUpload",
		fileManagerJson : Public.rootPath() + "/ke/fileManager",
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		allowImageRemote : false,
		cssPath : Public.rootPath() + "/assets/js/plugins/kindeditor/plugins/image/imgAuto.css",//图片自适应编辑器
		filterMode : false,
		/*items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']*/
	});
}
$(document).ready(function(e) {
	// 需要先初始化表单验证配置
	initValidator();
	KE();
	THISPAGE.init();
	initUpload("d_notice", vm.data.keyid);
	
	var request = Public.urlRequest();
	var status = request.queryString["status"];
	if (status!=null && status==0) {
		$("#flowApproved").attr("style","");
		$("#flowApproved").click(function() {
			flowApprove("d_notice", vm.data.keyid);
			var $number=$(window.parent.parent.topFrame.document.getElementById("flow_todo_number"));
			 $number.html($number.html()-1);
		});
		//注释掉审核未通过按钮，上级下发的通知和文件不能不接收
//		$("#noflowApproved").css("display","");
		
		$("#btback").click(function() {
			 window.location = "../../flow/flowManage/flowList.html?t="+Math.random();
		});
//		$("#noflowApproved").click(function() {
//			readFlow(vm.data.keyid);
//			var $number=$(window.parent.parent.topFrame.document.getElementById("flow_todo_number"));
//			 $number.html($number.html()-1);
//		});
		$("#add").css("display","none");
	}
});
