var vm = avalon.define({
    $id: "alarmReadOnlyCtrl",
	data : {
		keyid : "",
		title : "",
		afeedbacker : "",
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
		objectadd : ""
	},
    keyid: "",
});

THISPAGE = {
	    
		init: function () {
	        this.initEvent();
	        this.initData();
	    },
	    
	    initData:function(){
			var request = Public.urlRequest();
			var opType = request.queryString["opType"];
			var status  = request.queryString["status"];
			
			if(opType==1 && status!=1){
				$("#approval").show();
			}
	        if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
		            
	        	vm.keyid = request.queryString["keyid"];
		        Public.ajaxPost(Public.rootPath() + "/al/editData", "keyid="
							+ vm.keyid, function(json) {
						if (json.status == 001) {
							vm.data = json.data;
						} else {
							top.openeasydialog("error", "信息获取失败");
						}
					});
		}
		},
		
		initEvent: function () {
			var request = Public.urlRequest();
			opType = request.queryString["opType"];
		    $("#btback").click(function () {
		    	if(opType==1){//返回待办列表
					window.location = "../../flow/flowManage/flowList.html?status=0";
				} else if(opType==2){//返回已办列表
					window.location = "../../flow/flowManage/flowList.html?status=1";
				} else {
					window.location = "alarmList.html";
				}		        
		    });
		    
		    $("#flowApproved").click(function() {
		    	flowNewApprove("a_alarm", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("a_alarm", vm.data.keyid);
			});
		},				
};


$(document).ready(function() {
	THISPAGE.init();
	initUpload("alarm", vm.keyid);
});