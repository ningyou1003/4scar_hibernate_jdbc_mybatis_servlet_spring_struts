var vm = avalon.define({
	$id : "templeDetailCtrl",
	data : {
		keyid : "",
		type: "",
		releasetime : "",
	},
	content : {
		preview : "",
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
				window.location = "templeList.html";
			});
		},
		initData : function() {
			var request = Public.urlRequest();
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.data.keyid = request.queryString["keyid"];
				Public.ajaxPost(Public.rootPath() + "/temple/detailData", "keyId="
						+ vm.data.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data.temple;
						vm.content = json.data.attachment[0];
						$("#title").html(vm.data.type);
						$("#content").html(vm.content.preview);
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
	infoAttList("temple", vm.data.keyid);//获取附件列表
});