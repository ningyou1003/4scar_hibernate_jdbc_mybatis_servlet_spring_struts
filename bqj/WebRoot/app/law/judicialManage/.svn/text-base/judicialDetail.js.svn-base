var vm = avalon.define({
	$id : "judicialDetailCtrl",
	data : {
		keyid : "",
		title: "",
		releasetime: "",
		content : "",
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
});
THISPAGE = {
		init : function() {
			this.initDom();
			this.initEvent();
			this.initData();
		},
		initEvent : function() {
			$("#btback").click(function() {
				window.location = "judicialList.html";
			});
		},
		initData : function() {
			var request = Public.urlRequest();
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.data.keyid = request.queryString["keyid"];
				Public.ajaxPost(Public.rootPath() + "/judicial/editData", "keyId="
						+ vm.data.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data;
						$("#title").html(vm.data.title);
						$("#content").html(vm.data.content);
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
	infoAttList("judicial", vm.data.keyid);//获取附件列表
});