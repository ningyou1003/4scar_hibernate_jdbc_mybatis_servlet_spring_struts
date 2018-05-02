var vm = avalon.define({
    $id: "dynamicDetailCtrl",
	data : {
		keyid : "",
		title : "",
		type : "",
		content : "",
		month : "",
		time : "",
		randomNum : randomNum,
		ispublic : "0"//默认否
	},
    keyid: "",
    submit: function () {
    	//alert(1);
        $("#base_form").trigger("validate");
    }
});
var isData = 0;//全局变量，上报前是否有记录，0无，1有
var kindeditor;
THISPAGE = {
	    
		init: function () {
	        this.initDom();
	        this.initEvent();
	        this.initData();
	    },
	    
	    initData:function(){
			var request = Public.urlRequest();
	        if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
	        	//$("#report").show();    
	        	vm.keyid = request.queryString["keyid"];
		        Public.ajaxPost(Public.rootPath() + "/dynamic/editData", "keyid="
							+ vm.keyid, function(json) {
						if (json.status == 001) {
							isData = 1;
							vm.data = json.data;
							kindeditor.html(vm.data.content);
							if(vm.data.status==0 || vm.data.status==3){//如果审核未通过，“上报”按钮显示
								var regionid=$.cookie("regionCode");
								if (regionid!=null && regionid=='450000000000') {
									$("#report").css("display","none");
								}else {
									$("#report").css("display","");
								}
								
							}
							//if(vm.data.status==3 || vm.data.status==1){//弃用
							if(vm.data.status==3 ){
								$("#report,#Button3").css({"background":"#ccc","cursor":"default"});
								$("#report,#Button3").attr("disabled","true");
							}
						} else {
							top.openeasydialog("error", "信息获取失败");
						}
					});
		}else{
			$("#report").css("display","");
		}
		},
		
		initEvent: function () {
		    $("#btback").click(function () {
		        window.location = "dynamicList.html";
		    });
		    $("#report").click(function () {
		    	if(isData==0){
		    		top.openeasydialog("warn", "请先保存数据！");
		    	}else{
		    		flowNewSend("n_dynamic", vm.data.keyid);
		    	}
		    	
		    });
		},
		initDom: function () {
		}				
};

function postData() {
	var url = null;
	vm.data.month=$("#month").val();
	vm.data.time=$("#Time").val();
	vm.data.content = kindeditor.html();
	if(vm.data.content == null ||vm.data.content == ""){
		top.openeasydialog("error", "内容不能为空");
		return;
	}
	if (vm.data.keyid != "" && vm.data.keyid != null && vm.data.keyid != undefined) {
		url = Public.rootPath() + "/dynamic/edit";
	} else {
		//alert($("#Time").val());
		//alert(vm.data.time);
		url = Public.rootPath() + "/dynamic/add";
		
	}
	Public.ajaxPost(url, vm.data.$model, function(json) {
		if (json.status == "001") {
			vm.data.keyid = json.data;
			isData = 1;//表示有记录
			top.openeasydialog("success", json.msg, function (item, dialog) {
                dialog.close();
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
		allowImageRemote : true,
		pasteType : 2,//HTML粘贴
		cssPath : Public.rootPath() + "/assets/js/plugins/kindeditor/plugins/image/imgAuto.css",//图片自适应编辑器
		allowFileManager: true
	});
}

$(document).ready(function() {
	KE();
	initValidator();
	THISPAGE.init();
	initUpload("dynamic", vm.keyid);
	
	
	
	var regionid=$.cookie("regionCode");
	if (regionid!=null && regionid=='450000000000') {
		$("#report").css("display","none");
	}else {
		$("#report").css("display","");
	}
	var request = Public.urlRequest();
	var status = request.queryString["status"];
	if (status!=null && status==0) {
		vm.data.keyid="";
		$("#flowApproved").attr("style","");
		$("#flowApproved").click(function() {
			flowApprove("n_dynamic", vm.data.keyid);
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

//$(function(){
//	var request = Public.urlRequest();
//	
////	 if (request.queryString["keyid"] != null
////				&& request.queryString["keyid"].length > 0 && request.queryString["status"] != null && request.queryString["status"]==0){
////		 $.post(Public.rootPath()+"/flow/readFlow",
////				 {keyid:request.queryString["keyid"]},
////				 function(json){
////					 //alert(json.status);
////					var $number=$(window.parent.parent.topFrame.document.getElementById("flow_todo_number"));
////					 //alert($number.html());
////					 $number.html($number.html()-1);
////				 });
////	 } 
//	 
//	 $("#btback").click(function(){
//		 window.history.go(-1); 
//		 window.location.reload(); 
//	 });
//});