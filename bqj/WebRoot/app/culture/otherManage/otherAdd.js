var vm = avalon.define({
	$id : "otherAddCtrl",
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
			window.location = "otherList.html";
		});
		$("#report").click(function() {
			if(saveData == true)
				flowSend("other",vm.data.keyid);
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
			Public.ajaxPost(Public.rootPath() + "/other/editData", "keyId="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					//if(vm.data.status==3 || vm.data.status==1){//弃用
					if(vm.data.status==3 ){
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
		url= Public.rootPath() + "/other/edit";
	}else{
		url= Public.rootPath() + "/other/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			vm.data.keyid = json.data;
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
//				location.href=htmlRootPath+"/app/culture/otherManage/otherList.html";
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
	initUpload("other", vm.data.keyid);
	
//	$("#btback").click(function(){
//		 window.history.go(-1); 
//		 window.location.reload(); 
//	 });
	
	var regionid=$.cookie("regionCode");
	if (regionid!=null && regionid=='450000000000') {
		$("#report").css("display","none");
	}else {
		$("#report").css("display","");
	}
	var request = Public.urlRequest();
	var status = request.queryString["status"];
	if (status!=null && status==0) {
		$("#flowApproved").attr("style","");
		$("#flowApproved").click(function() {
			flowApprove("other", vm.data.keyid);
			var $number=$(window.parent.parent.topFrame.document.getElementById("flow_todo_number"));
			 $number.html($number.html()-1);
		});
		$("#noflowApproved").css("display","");
		
		$("#btback").click(function() {
			 window.location = "../../flow/flowManage/flowList.html?t="+Math.random();
		});
		$("#noflowApproved").click(function() {
			readFlow(vm.data.keyid);
			var $number=$(window.parent.parent.topFrame.document.getElementById("flow_todo_number"));
			 $number.html($number.html()-1);
		});
//		$("#add").css("display","none");
	}
});
