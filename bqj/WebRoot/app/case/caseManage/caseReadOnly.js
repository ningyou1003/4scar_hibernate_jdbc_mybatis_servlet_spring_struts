var vm = avalon.define({
    $id: "caseReadOnlyCtrl",
	data : {
		keyid : "",
		title : "",
		type : "",
		content : "",
		randomNum : randomNum
	},
    keyid: "",
    typeid:"",
});

THISPAGE = {
	    
		init: function () {
	        this.initDom();
	        this.initEvent();
	        this.initData();
	    },
	    
	    initData:function(){
			var request = Public.urlRequest();
			var opType = request.queryString["opType"];
			var status  = request.queryString["status"];
			
			if(opType==1 && status!=1){
				$("#approval").show();//在待处理列表显示数据，需要两个参数方可
			}
	        if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
		            
	        	vm.keyid = request.queryString["keyid"];
		        Public.ajaxPost(Public.rootPath() + "/case/editData", "keyid="
							+ vm.keyid, function(json) {
						if (json.status == 001) {
							vm.data = json.data;
							vm.typeid = vm.data.type;
							$("#title").html(vm.data.title);
							$("#content").html(vm.data.content);
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
					window.location = "caseList.html?type="+vm.typeid;
				}		        
		    });		    
		    $("#review").click(function() {
				flowNewApprove("c_case", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("c_case", vm.data.keyid);
			});
		},
		initDom: function () {
		}				
};


$(document).ready(function() {
	THISPAGE.init();
	initUpload("case", vm.keyid);
});