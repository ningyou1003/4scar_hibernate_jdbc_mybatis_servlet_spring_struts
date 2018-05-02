var vm = avalon.define({
    $id: "alarmDetailCtrl",
	data : {
		keyid : "",
		title : "",
		afeedbacker : "",
		atime :"",
		aphone : "",
		adescribe : "",
		randomNum : randomNum,
		realname : "",
		gender : "",
		aliases : "",
		email : "",
		postcode : "",
		address : "",
		object : "",
		objecttel : "",
		objectadd : "",
		opinion : "",
		opiniontime : ""
	},
    keyid: "",
    submit: function () {
        $("#base_form").trigger("validate");
    }
});
var isData = 0;//全局变量，上报前是否有记录，0无，1有
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
	        	vm.keyid = request.queryString["keyid"];
		        Public.ajaxPost(Public.rootPath() + "/al/editData", "keyid="
							+ vm.keyid, function(json) {
						if (json.status == 001) {
							isData = 1;
							vm.data = json.data;
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
		        window.location = "alarmList.html";
		    });
		    
		    $("#report").click(function () {
		    	if(isData==0){
		    		top.openeasydialog("warn", "请先保存数据！");
		    	}else{
		    		flowNewSend("a_alarm", vm.data.keyid);
		    	}		    	
		    });
		},
		initDom: function () {
		}				
};

function postData() {
	var url = null;
	if (vm.data.keyid != "" && vm.data.keyid != null && vm.data.keyid != undefined) {
		url = Public.rootPath() + "/al/edit";
	} else {
		url = Public.rootPath() + "/al/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(json) {
		if (json.status == "001") {
			vm.data.keyid = json.data;
			isData = 1;//表示有记录
			top.openeasydialog("success", json.msg, function (item, dialog) {
                dialog.close();
                //location.href=htmlRootPath+"/app/appealalarm/alarmManage/alarmList.html";
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

$(document).ready(function() {
	initValidator();
	THISPAGE.init();
	initUpload("alarm", vm.keyid);
	
	
	
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
			flowApprove("a_alarm", vm.data.keyid);
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
