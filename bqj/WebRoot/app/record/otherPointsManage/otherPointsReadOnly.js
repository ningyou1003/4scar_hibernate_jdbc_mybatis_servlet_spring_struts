var vm = avalon.define({
    $id: "otherPointsReadOnlyCtrl",
	data : {
		keyid : "",
		title : "",
		time : "",
		type : "",
		creator : "",
		content : "",
		createUserId : "",
		status : "",
		randomNum : randomNum
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
		        Public.ajaxPost(Public.rootPath() + "/points/editData", "keyid="
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
		    $("#btback").click(function () {
		        window.location = "otherPointsList.html?type=other";
		    });
		},
			
};


$(document).ready(function() {
	THISPAGE.init();
	initUpload("other", vm.keyid);
});