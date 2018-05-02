var vm = avalon.define({
    $id: "keyPointsDetailCtrl",
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
    submit: function () {
        $("#base_form").trigger("validate");
    }
});

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
	        	$("#report").show();//id非空就显示上报按钮
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
		        window.location = "keyPointsList.html?type=key";
		    });
		},
		initDom: function () {
		}				
};

function postData() {
	var request = Public.urlRequest();
	vm.data.type = request.queryString["type"];
	var url = null;
	if (vm.keyid != "" && vm.keyid != null && vm.keyid != undefined) {
		url = Public.rootPath() + "/points/edit";
	} else {
		url = Public.rootPath() + "/points/add";
		
	}
	Public.ajaxPost(url, vm.data.$model, function(json) {
		if (json.status == "001") {
			top.openeasydialog("success", json.msg, function (item, dialog) {
                dialog.close();
                location.href=htmlRootPath+"/app/record/keyPointsManage/keyPointsList.html?type=key";
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
	initUpload("key", vm.keyid);
});