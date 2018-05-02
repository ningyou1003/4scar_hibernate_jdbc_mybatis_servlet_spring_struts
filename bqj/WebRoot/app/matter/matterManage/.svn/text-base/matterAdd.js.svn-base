var vm = avalon.define({
	$id : "matterAddCtrl",
	data : {
		keyid : "",
		title : "",
		status : "",
		content : "",
		publishtime : "",
		randomNum : randomNum
	},
	selected : "",
	status : ["待办","已办"],
	sendStatus : "",
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		$("#base_form").trigger("validate");
	}
});
var flag = 0;//记录是否已保存，0表示尚未保存，1表示已保存
var kindeditor;
THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "matterList.html?status="+vm.sendStatus;
		});
		$("#add").click(function() {
			window.location = "matterAdd.html?status="+vm.sendStatus ;
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		vm.sendStatus = request.queryString["status"];
		var keyId = request.queryString["keyid"];
		if (keyId != null && keyId.length > 0) {
			vm.data.keyid = keyId;
			Public.ajaxPost(Public.rootPath() + "/matter/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					flag = 1;
					vm.data=json.data;
					kindeditor.html(vm.data.content);
					vm.sendStatus = vm.data.status;
					selectStatus(vm.sendStatus );
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} else {
			selectStatus(vm.sendStatus);
			vm.data.publishtime = Public.getNowFormatDate();
            $("#publishtime").val(vm.data.publishtime);
		}
	},
	initDom : function() {
	}
};
function postData() {
	if(vm.selected=="待办"){
		vm.data.status = 0;
	} else {
		vm.data.status = 1;
	}
	vm.data.content = kindeditor.html();
	var url=null;
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/matter/edit";
	}else{
		url= Public.rootPath() + "/matter/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			vm.data.keyid = json.data;
			flag = 1;
			var dialog = top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/matter/matterManage/matterList.html?status="+vm.sendStatus;
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
function selectStatus(status){
	if(status==0){
		vm.selected = "待办";
	} else if(status==1){
		vm.selected = "已办";
	} 
}
function KE(){
	kindeditor = KindEditor.create('textarea[name="content"]', {
		resizeType : 1,
		uploadJson : Public.rootPath() + "/ke/fileUpload",
		fileManagerJson : Public.rootPath() + "/ke/fileManager",
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		allowImageRemote : true,
		pasteType : 2,//HTML粘贴
		cssPath : Public.rootPath() + "/assets/js/plugins/kindeditor/plugins/image/imgAuto.css",//图片自适应编辑器
		filterMode : false
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
	initUpload("activity", vm.data.keyid);
});
