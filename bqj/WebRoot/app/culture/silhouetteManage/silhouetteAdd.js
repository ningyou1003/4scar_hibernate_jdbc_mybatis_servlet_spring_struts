var vm = avalon.define({
	$id : "silhouetteAddCtrl",
	data : {
		keyid : "",
		year : "",
		title : "",
		content : "",
		ispublic : "1",
		randomNum: randomNum1,
		randomNum1: randomNum3,
	},
	sourceid : "",
	selectType : ["否","是"],
	type : "",
	year : "",
	regionCode : "",
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		$("#base_form").trigger("validate");
	}
});
//var kindeditor;//编辑器
var saveData = false;
THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "silhouettePage.html?regionCode=" + vm.regionCode + "&year=" + vm.year;
		});
		$("#report").click(function() {
			if(saveData == true)
				flowSend("silhouette",vm.data.keyid);
			else
				top.openeasydialog("warn", "请先保存数据！");
		});
//		$("#btnUpload").click(function() {
//			startUploadImg();
//			top.openeasydialog("success","上传成功！", function(item, dialog){
//				dialog.close();
//				location.href=htmlRootPath+"/app/culture/silhouetteManage/silhouetteList.html";
//			});
//		});
	},
	initData : function() {
		var request = Public.urlRequest();
		vm.regionCode = request.queryString["regionCode"];
		vm.year = request.queryString["year"];
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			vm.sourceid = request.queryString["sourceid"];
			saveData = true;
			Public.ajaxPost(Public.rootPath() + "/silhouette/editData", "keyId="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					//if(vm.data.status==3 || vm.data.status==1){//弃用
					if(vm.data.status==3 ){
						$("#report,#Button1").css("background","gray");
						$("#report,#Button1").attr("disabled","true");
					}
					typeidToType(vm.data.ispublic);
//					kindeditor.html(vm.data.content);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} else {
			typeidToType(vm.data.ispublic);
			vm.data.year = vm.year;
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
//	vm.data.content = kindeditor.html();
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/silhouette/edit";
	}else{
		url= Public.rootPath() + "/silhouette/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			vm.data.keyid = json.data.silhouette;
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/culture/silhouetteManage/silhouettePage.html?regionCode=" + vm.regionCode + "&year=" + vm.year;
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

//function KE(){
//	kindeditor = KindEditor.create('textarea[name="content"]', {
//		resizeType : 1,
//		uploadJson : Public.rootPath() + "/ke/fileUpload",
//		fileManagerJson : Public.rootPath() + "/ke/fileManager",
//		allowPreviewEmoticons : false,
//		allowImageUpload : true,
//		allowImageRemote : false,
//		pasteType : 2,//HTML粘贴
//		cssPath : Public.rootPath() + "/assets/js/plugins/kindeditor/plugins/image/imgAuto.css",//图片自适应编辑器
//		/*items : [
//			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
//			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
//			'insertunorderedlist', '|', 'emoticons', 'image', 'link']*/
//	});
//}

$(document).ready(function(e) {
	// 需要先初始化表单验证配置
	initValidator();
//	KE();
	THISPAGE.init();
//	initUpload("silhouette",vm.data.keyid);
	silhouetteUpload("silhouette", vm.data.keyid, vm.sourceid);
	
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
			flowApprove("silhouette", vm.data.keyid);
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
