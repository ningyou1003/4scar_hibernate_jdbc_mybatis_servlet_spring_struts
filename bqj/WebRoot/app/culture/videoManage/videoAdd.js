var vm = avalon.define({
	$id : "videoAddCtrl",
	data : {
		keyid : "",
		title : "",
		typeid : "",
		ispublic : "1",
		imgID: randomNum1,
		videoID: randomNum2,
		videoSourceID: randomNum3,
	},
	videoimgid : "",
	videosourceid : "",
	publicType : ["否","是"],
	type : "",
	videoTypes : ["专题片","新闻报道","其他"],
	selectType : "",
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		$("#base_form").trigger("validate");
	}
});

var saveData = false;
THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "videoList.html?typeid=" + vm.data.typeid;
		});
		$("#report").click(function() {
			if(saveData == true)
				flowSend("video",vm.data.keyid);
			else
				top.openeasydialog("warn", "请先保存数据！");
		});
/*		$("#btnUpload").click(function() {
			startUploadVideoImg();
			startUploadFile();
		});*/
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			vm.videoimgid = request.queryString["videoimgid"];
			vm.videosourceid = request.queryString["videosourceid"];
			saveData = true;
			Public.ajaxPost(Public.rootPath() + "/video/editData", "keyId="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					//if(vm.data.status==3 || vm.data.status==1){//弃用
					if(vm.data.status==3 ){
						$("#report,#Button1").css("background","gray");
						$("#report,#Button1").attr("disabled","true");
					}
					typeidToType(vm.data.typeid);
					typeidToPublicType(vm.data.ispublic);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} else {
			vm.data.typeid = request.queryString["typeid"];
			typeidToType(vm.data.typeid);
			typeidToPublicType(vm.data.ispublic);
		}
	},
	initDom : function() {
	}
};

function typeidToPublicType(typeid) {
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
	if(vm.selectType == "专题片")
		vm.data.typeid = 1;
	else if(vm.selectType == "新闻报道")
		vm.data.typeid = 2;
	else 
		vm.data.typeid = 3;
	
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/video/edit";
	}else{
		url= Public.rootPath() + "/video/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
//			vm.data.keyid = json.data.video;
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
//				saveData = true;
				location.href=htmlRootPath+"/app/culture/videoManage/videoList.html?typeid=" + vm.data.typeid;
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
}

function typeidToType(typeid) {
	if(typeid == 1)
		vm.selectType = "专题片" ;
	else if (typeid == 2)
		vm.selectType = "新闻报道" ;
	else 
		vm.selectType = "其他" ;
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



$(document).ready(function(e) {
	// 需要先初始化表单验证配置
	initValidator();
	THISPAGE.init();
//	initUpload("video", vm.data.keyid);
	videoUpload("video", vm.videoimgid, vm.data.keyid, vm.videosourceid);
	
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
			flowApprove("video", vm.data.keyid);
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
