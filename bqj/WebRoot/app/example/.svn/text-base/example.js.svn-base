var vm = avalon.define({
	$id : "exampleCtrl",
	data : {
		keyid : "",
		title : "",
		randomNum: randomNum
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		//$("#base_form").trigger("validate");
		postData();
	}
});
THISPAGE = {
	init : function() {
		this.initDom();
		this.initData();
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/xxxx/xxxx", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data.act;
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		}
	},
	initDom : function() {
	}
};
function postData() {
	var url= Public.rootPath() + "/example/save";
	Public.ajaxPost(url, vm.data.$model, function(json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
}

$(document).ready(function(e) {
	THISPAGE.init();
	initUpload("example", vm.data.keyid)
});
