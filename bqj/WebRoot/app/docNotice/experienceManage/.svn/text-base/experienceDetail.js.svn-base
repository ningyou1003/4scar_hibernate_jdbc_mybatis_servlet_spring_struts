var vm = avalon.define({
	data : {
		keyid : "",
		experiencename : "",
		brief : ""
	}
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "experienceList.html";
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/experience/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					$("#div1_title").append(vm.data.experiencename);
					$("#div1_content").append(vm.data.brief);
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
	infoAttList("d_experience", vm.data.keyid);
});
