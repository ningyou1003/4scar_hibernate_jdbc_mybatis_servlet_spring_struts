var vm = avalon.define({
	$id : "stipulateAddCtrl",
	data : {
		keyid : "",
		title : "",
		content : "",
		releasetime:"",
		randomNum: randomNum,
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		$("#base_form").trigger("validate");
	}
});
var kindeditor;//编辑器
THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "stipulateList.html";
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/stipulate/editData", "keyId="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					kindeditor.html(vm.data.content);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} else {
			vm.data.releasetime = Public.getNowFormatDate();
		}
	},
	initDom : function() {
	}
};
function postData() {
	var url=null;
	vm.data.content = kindeditor.html();
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/stipulate/edit";
	}else{
		url= Public.rootPath() + "/stipulate/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/law/stipulateManage/stipulateList.html";
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
		pasteType : 2,//HTML粘贴
		filterMode : false,
		cssPath : Public.rootPath() + "/assets/js/plugins/kindeditor/plugins/image/imgAuto.css",//图片自适应编辑器
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
	initUpload("stipulate", vm.data.keyid);
});
