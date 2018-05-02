var vm = avalon.define({
	$id : "mediaAddCtrl",
	data : {
		keyid : "",
		title : "",
		content : "",
		releasetime:"",
		ispublic : "1",
		randomNum: randomNum,
	},
	selectType : ["否","是"],
	type : "",
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		$("#base_form").trigger("validate");
	}
});
var kindeditor;//编辑器
var saveData = false;
THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "mediaList.html";
		});
		$("#report").click(function() {
			if(saveData == true)
				flowSend("media",vm.data.keyid);
			else
				top.openeasydialog("warn", "请先保存数据！");
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			saveData = true;
			Public.ajaxPost(Public.rootPath() + "/media/editData", "keyId="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					if(vm.data.status == 3 || vm.data.status == 1) {
						$("#report,#Button1").css("background","gray");
						$("#report,#Button1").attr("disabled","true");
					}
					typeidToType(vm.data.ispublic);
					kindeditor.html(vm.data.content);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} else {
			typeidToType(vm.data.ispublic);
			vm.data.releasetime = Public.getNowFormatDate();
		} 
	},
	initDom : function() {
	}
};

function typeidToType(typeid) {
	if(typeid == 0)
		vm.type = "否" ;
	else 
		vm.type = "是" ;
}

function postData() {
	var url=null;
	if(vm.type == "否") {
		vm.data.ispublic = 0;
	} else {
		vm.data.ispublic = 1;
	}
	vm.data.content = kindeditor.html();
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/media/edit";
	}else{
		url= Public.rootPath() + "/media/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			vm.data.keyid = json.data;
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
//				location.href=htmlRootPath+"/app/culture/mediaManage/mediaList.html";
				saveData = true;
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
	KindEditor.options.filterMode = false;
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
	initUpload("media", vm.data.keyid);
});
