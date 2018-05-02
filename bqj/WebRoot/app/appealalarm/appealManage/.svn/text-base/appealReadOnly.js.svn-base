var vm = avalon.define({
    $id: "appealReadOnlyCtrl",
	data : {
		keyid : "",
		title : "",
		feedbacker : "",
		phone : "",
		describe : "",
		randomNum : randomNum,
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
		        Public.ajaxPost(Public.rootPath() + "/ap/editData", "keyid="
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
					window.location = "appealList.html";
				}		        
		    });		    
		    $("#review").click(function() {
		    	flowNewApprove("a_appeal", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("a_appeal", vm.data.keyid);
			});
		},
					
};



$(document).ready(function() {
	THISPAGE.init();
	initUpload("appeal", vm.keyid);
});