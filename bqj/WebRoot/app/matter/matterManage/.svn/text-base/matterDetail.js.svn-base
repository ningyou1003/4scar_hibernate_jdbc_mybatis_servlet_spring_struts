var vm = avalon.define({
	data : {
		keyid : "",
		title : "",
		status : "",
		content : "",
		publishtime : ""
	},
	status : ""
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "matterList.html?status=" + vm.status;
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		vm.status = request.queryString["status"];
		var keyid = request.queryString["keyid"] ;
		if (keyid != null && keyid.length > 0) {
			Public.ajaxPost(Public.rootPath() + "/matter/editData", "keyid="
					+ keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					$("#div1_title").append(vm.data.title);
					$("#div1_content").append(vm.data.content);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		}

	},
	initDom : function() {
	}
};
$(document).ready(function(e) {
	THISPAGE.init();
	infoAttList("d_document", vm.data.keyid);
});
